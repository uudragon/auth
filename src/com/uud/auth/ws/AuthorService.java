package com.uud.auth.ws;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.uud.auth.entity.Resource;
import com.uud.auth.entity.User;
import com.uud.auth.impls.info.AccessFactory;
import com.uud.auth.util.ErrorCode;
import com.uud.auth.util.token.Token;
import com.uud.auth.util.token.TokenProcessorFactory;

/**
 * 提供系统的登录权限认证功能
 * @author yangl
 *	
 */
@Path("auth")
public class AuthorService {
	
	/**
	 * 用户登录
	 * 访问路径：${authpath}/ws/auth/login  方法：GET
	 * @param account		用户帐号
	 * @param password		用户密码
	 * @param domain		用户系统标识（如果为null，则默认为bam帐号）
	 * @return 用户登录信息。json格式.<br>
	 * 			字段		类型			说明<br>
	 * 			legal 	Boolean		是否合法<br>
	 * 			token	String		令牌<br>
	 * 			message	String		登录失败后的提示信息<br>
	 * 			user	User		用户信息
	 */
	@Path("login")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public LoginMessage login( @QueryParam("account") String account,
								 @QueryParam("password") String password,
								 @HeaderParam("domain") String domain ){
		LoginMessage lm = new LoginMessage();
		if( domain == null ){
			domain = "bam";
		}
		User user = AccessFactory.getAccessInfo().getUser( domain, account );
		if( user != null && user.getIsValid() && user.getPassword().equals( password ) ){
			lm.setLegal( true );
			lm.setUser( user );
			lm.setToken( TokenProcessorFactory.getProcessor().buildToken( user.getId(), domain ) );
		} else if( user != null && !user.getIsValid() ){
			lm.setLegal( false );
			lm.setMessage( ErrorCode.AUTH_ACCOUNT_UNACTIVE + ":the user is unactived!" );
		} else if( user == null ) {
			lm.setLegal( false );
			lm.setMessage( ErrorCode.AUTH_ACCOUNT_ERROR + ":the user not exist!" );
		} else {
			lm.setLegal( false );
			lm.setMessage( ErrorCode.AUTH_PASSWORD_ERROR + ":password wrong!" );
		}
		return lm;
	}
	
	/**
	 * 用户登出
	 * 访问路径：${authpath}/ws/auth/logout  方法：GET
	 * @param token		令牌
	 * @param domain	系统标识
	 * @return	用户登出信息。json格式.<br>
	 * 			字段		类型			说明<br>
	 * 			legal 	Boolean		是否登出<br>
	 * 			message	String		失败后的提示信息<br>
	 */
	@Path("logout")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public LoginMessage logout( @HeaderParam("token") String token,
							 @HeaderParam("domain") String domain ){
		LoginMessage message = new LoginMessage();
		Token t = TokenProcessorFactory.getProcessor().getToken( token );
		if( t != null ){
			message.setLegal( true );
			TokenProcessorFactory.getProcessor().remove( token );
		} else {
			message.setLegal( false );
			message.setMessage( ErrorCode.AUTH_TOKEN_NOT_EXITS + ": token not exits." );
		}
		return message;
	}
	/**
	 * 验证令牌是否具有该资源（url）某中权限（method）
	 * 访问路径：${authpath}/ws/auth/checkToken  方法：GET
	 * @param token		令牌
	 * @param url		资源路径
	 * @param method	操作访问（GET/DELETE/POST/PUT,或者其他自定义类型）
	 * @param domain	系统标识（用户系统标识（如果为null，则默认为bam帐号）
	 * @return	验证结果。json格式<br>
	 * 		字段		类型 	说明<br>
	 * 		result	Boolean	验证是否通过<br>
	 * 		message	String	验证失败提示信息<br>
	 */
	@Path("checkToken")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public TokenCheckResult checkToken( @HeaderParam("token") String token, 
							  @QueryParam("url") String url,
							  @QueryParam("method") String method,
							  @HeaderParam("domain") String domain ){
		if( domain == null ){
			domain = "bam";
		}
		TokenCheckResult result = new TokenCheckResult();
		boolean flag = false;
		Token t = TokenProcessorFactory.getProcessor().getToken( token );
		if( t != null ){
			Long userId = t.getUserId();
			Long[] roleIds = AccessFactory.getAccessInfo().getRoles( userId, domain );
			List<Long> rList = AccessFactory.getAccessInfo().getRole(url, method, domain);
			for( Long roleId : roleIds ){
				for( Long rId : rList ){
					if( rId.equals( roleId ) ){
						flag = true ;
					}
				}
			}
		} else {
			result.setMessage( ErrorCode.AUTH_TOKEN_NOT_EXITS + ": token not exits." );
		}
		result.setResult( flag );
		
		return result;
	}
	
	/**
	 * 获取某资源（url）下边的具有访问权限的资源（url表示资源的下级资源）
	 * 访问路径：${authpath}/ws/auth/resourceList  方法：GET
	 * @param url		资源描述
	 * @param method	资源方法
	 * @param token		令牌
	 * @param domain	系统表示
	 * @return	验证结果。list的json格式<br>
	 * 		字段		类型 	说明<br>
	 * 		id		long	验证是否通过<br>
	 * 		name	String	验证失败提示信息<br>
	 * 		url		String	资源表示的url<br>
	 * 		method	String	资源的操作方法<br>
	 * 		parent	long	资源父节点<br>
	 * 		active	Boolean	是否有效
	 */
	@Path("resourceList")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Resource> getResource( @QueryParam("url") String url,
									   @QueryParam("method") String method,
									   @HeaderParam("token") String token,
									   @HeaderParam("domain") String domain ){
		if( domain == null){
			domain = "bam";
		}
		if( token != null ){
			Token t = TokenProcessorFactory.getProcessor().getToken( token );
			if( t != null ){
				return AccessFactory.getAccessInfo().getResources( url, method, domain, t.getUserId() );
			}
		}
		return new ArrayList<Resource>();
	}
	
	/**
	  * 获取某资源（code）下边的具有访问权限的资源（url表示资源的下级资源）
	 * 访问路径：${authpath}/ws/auth/resourceListByCode  方法：GET
	 * @param code		资源编码
	 * @param token		令牌
	 * @param domain	系统表示
	 * @return	验证结果。list的json格式<br>
	 * 		字段		类型 	说明<br>
	 * 		id		long	验证是否通过<br>
	 * 		name	String	验证失败提示信息<br>
	 * 		url		String	资源表示的url<br>
	 * 		method	String	资源的操作方法<br>
	 * 		parent	long	资源父节点<br>
	 * 		active	Boolean	是否有效
	 */
	@Path("resourceListByCode")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Resource> getResource( @QueryParam("code") String code, 
									   @HeaderParam("token") String token,
									   @HeaderParam("domain") String domain ){
		if( domain == null){
			domain = "bam";
		}
		if( token != null ){
			Token t = TokenProcessorFactory.getProcessor().getToken( token );
			if( t != null ){
				return AccessFactory.getAccessInfo().getResources( code, domain, t.getUserId() );
			}
		}
		return null;
	}
	
	/**
	 * 验证令牌是否具有该资源（code）某中权限（method）
	 * 访问路径：${authpath}/ws/auth/checkTokenByCode  方法：GET
	 * @param token		令牌
	 * @param code		资源编码
	 * @param domain	系统标识（用户系统标识（如果为null，则默认为bam帐号）
	 * @return	验证结果。json格式<br>
	 * 		字段		类型 	说明<br>
	 * 		result	Boolean	验证是否通过<br>
	 * 		message	String	验证失败提示信息<br>
	 */
	@Path("checkTokenByCode")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public TokenCheckResult checkToken( @QueryParam("code") String code,
									   @HeaderParam("token") String token,
									   @HeaderParam("domain") String domain ){
		if( domain == null ){
			domain = "bam";
		}
		TokenCheckResult result = new TokenCheckResult();
		boolean flag = false;
		Token t = TokenProcessorFactory.getProcessor().getToken( token );
		if( t != null ){
			Long userId = t.getUserId();
			Long[] roleIds = AccessFactory.getAccessInfo().getRoles( userId, domain );
			List<Long> rList = AccessFactory.getAccessInfo().getRole( code , domain );
			for( Long roleId : roleIds ){
				for( Long rId : rList ){
					if( rId.equals( roleId ) ){
						flag = true ;
					}
				}
			}
		}
		result.setResult( flag );
		if( !flag ){
			result.setMessage( "" );
		}
		return result;
	}
}
