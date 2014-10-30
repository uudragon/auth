package com.uud.auth.ws;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.uud.auth.entity.Page;
import com.uud.auth.servlet.ServiceBeanContext;
import com.uud.cs.entity.Message;
import com.uud.cs.entity.MessageTemplate;
import com.uud.cs.service.IMessageService;

/**
 * 短信和邮件接口
 * 
 * @author yangl
 */
@Path("message")
public class MessageRestService {

	private IMessageService messageService = ServiceBeanContext.getInstance().getBean("messageService");
	
	/**
	 * 新建信息<br>
	 * 访问地址：http://ip:port/atnew/ws/message<br>
	 * method : post
	 * @param target	发送目标
	 * @param template	模版ID
	 * @param sendType	发送方式（1、短信 2、邮件）
	 * @param sendTime	发送时间
	 * @param opUser	操作人 userNO
	 * @return	true
	 * @throws ParseException
	 */
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public String save( Map<String,Object> map ) throws ParseException{
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String sendTime = (String) map.get("sendTime");
		map.put("sendTime", df.parse( sendTime )  );
		/*Map<String,Object> map = new HashMap<String,Object>();
		map.put("target", target);
		map.put("template", template);
		map.put("sendType", sendType);
		
		
		map.put("opUser", opUser);*/
		messageService.save( map );
		return "true";
	}
	
	/**
	 * 新建模版	<br>
	 * 访问地址：http://ip:port/atnew/ws/message/template<br>
	 * method : post
	 * @param subject	主题	
	 * @param content	内容
	 * @return true
	 */
	@Path("template")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public String saveTemplate(	Map<String, Object> map ){
		/*Map<String,Object> map = new HashMap<String,Object>();
		map.put("subject", subject);
		map.put("content", content);*/
		//System.out.println("=============\n\n\n\n"+content+"=============\n\n\n\n");
		messageService.saveTemplate( map );
		return "true";
	}
	
	/**
	 * 分页查询<br>
	 * 访问地址：http://ip:port/atnew/ws/message<br>
	 * method : get
	 * @param target	发送目标
	 * @param status	状态 1、等待发送，2、发送中 3、发送成功 4、发送失
	 * @param startTime	开始时间（查询条件发送时间）
	 * @param endTime	结束时间（查询条件发送时间）
	 * @param pageSize	页显示条数
	 * @param pageNo	当前页
	 * @return json格式的字符串<br>
	 * 			pageSize	页显示条数<br>
	 * 			pageNo		当前页<br>
	 * 			recordsCount	总条数<br>
	 * 			pageNumber		总页数<br>
	 * 			records			内容,同样也为json格式 ;id,target,template,sendType,sendTime,status,message,opUser,content <br>
	 * 			
	 * 							
	 */
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Page<Message> listByPage( @QueryParam("target") String target,
									 @QueryParam("status") String status,
									 @QueryParam("startTime") String startTime,
								     @QueryParam("endTime") String endTime,
								     @QueryParam("pageSize") String pageSize,
								     @QueryParam("pageNo") String pageNo){
		if( pageNo == null ){
			pageNo = "1";
		}
		if( pageSize == null ){
			pageSize = "10";
		}
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("target", target);
		map.put("status", status);
		map.put("startTime", startTime);
		map.put("endTime", endTime);
		return messageService.findByParams(map,
				Integer.parseInt( pageSize ), Integer.parseInt( pageNo ) );
	}
	
	/**
	 * 查询模版
	 * 访问地址：http://ip:port/atnew/ws/message/template
	 * 方法：get
	 * @return List<MessageTemplate>的json字符串<br>
	 * 			MessageTemplate的主要字段为：<br>
	 * 			id			主键
	 * 			subject		主题
	 * 			content		内容
	 */
	@GET
	@Path("template")
	@Produces( MediaType.APPLICATION_JSON )
	public List<MessageTemplate> findTemplate(){
		return messageService.findTemplate();
	}
	
}
