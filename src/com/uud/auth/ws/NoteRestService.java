package com.uud.auth.ws;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import com.uud.auth.entity.Page;
import com.uud.auth.servlet.ServiceBeanContext;
import com.uud.cs.entity.Note;
import com.uud.cs.service.INoteService;


/**
 * 站内信
 * @author yangl
 *
 */
@Path("notes")
public class NoteRestService {
	
	private INoteService noteService = ServiceBeanContext.getInstance().getBean("noteService");
	
	/**
	 * 保存站内信<br>
	 * 访问地址: http://ip:port/atnew/ws/notes<br>
	 * 方法：POST
	 * @param map <br>
	 * 		receiver 接收人
	 * 		sender 	  发送人
	 * 		content	 内容
	 * @return true
	 */
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public String save( Map<String,Object> map ){
		noteService.save( map );
		return "true";
	}
	
	/**
	 * 发站内信
	 * 访问地址: http://ip:port/atnew/ws/notes<br>
	 * 方法：PUT
	 * @param map <br>
	 * 		id		 站内信ID 如果直接发送的话，此参数可以为null
	 * 		boxId	 发件箱ID 如果直接发送的话，此参数可以为null
	 * 		receiver 接收人
	 * 		sender 	  发送人
	 * 		content	 内容
	 * @return true
	 */
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	public String send( Map<String,Object> map ){
		noteService.saveOrUpdate(map);
		return "true";
	}
	/**
	 * 发件箱<br>
	 * 访问地址：http://ip:port/atnew/ws/notes/outBox<br>
	 * method :get
	 * @param user		用户编码
	 * @param content	内容
	 * @param status	状态
	 * @param pageSize	页显示条数
	 * @param pageNo	当前页
	 * @return	json格式字符串，
	 * 			pageSize	页显示条数<br>
	 * 			pageNo		当前页<br>
	 * 			recordsCount	总条数<br>
	 * 			pageNumber		总页数<br>
	 * 			records			内容,同样也为json格式 ;id，receiver，sender，content，sendTime，boxId
	 */
	@Path("outBox")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Page<Note> outBox( @QueryParam("userNo") String user,
						  @QueryParam("content") String content,
						  @QueryParam("status") String status,
						  @QueryParam("pageSize") String pageSize,
						  @QueryParam("pageNo") String pageNo,
						  @Context HttpServletRequest request,
						  @Context HttpServletResponse response ){
		if( pageSize == null ){
			pageSize = "10";
		}
		if( pageNo == null ){
			pageNo = "1";
		}
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("user", user);
		map.put("content", content);
		map.put("status", status);
		Page<Note> page = noteService.findOutNotes(map, Integer.parseInt( pageSize ), Integer.parseInt( pageNo ) );
		return page;
	}
	
	/**
	 * 收件箱<br>
	 * 访问地址：http://ip:port/atnew/ws/notes/inBox<br>
	 * method :get	
	 * @param user		用户编码
	 * @param content	内容
	 * @param status	状态
	 * @param pageSize	页显示条数
	 * @param pageNo	当前页
	 * @return	json格式字符串，
	 * 			pageSize	页显示条数<br>
	 * 			pageNo		当前页<br>
	 * 			recordsCount	总条数<br>
	 * 			pageNumber		总页数<br>
	 * 			records			内容,同样也为json格式 ;id，receiver，sender，content，sendTime，boxId
	 */
	@Path("inBox")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Page<Note> inBox( @QueryParam("userNo") String user,
						  @QueryParam("content") String content,
						  @QueryParam("status") String status,
						  @QueryParam("pageSize") String pageSize,
						  @QueryParam("pageNo") String pageNo,
						  @Context HttpServletRequest request,
						  @Context HttpServletResponse response ){
		if( pageSize == null ){
			pageSize = "10";
		}
		if( pageNo == null ){
			pageNo = "1";
		}
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("user", user);
		map.put("content", content);
		map.put("status", status);
		Page<Note> page = noteService.findInNotes(map, Integer.parseInt( pageSize ), Integer.parseInt( pageNo ) );
		return page;
	}
	
	/**
	 * 删除收件箱内容<br>
	 * 访问地址：http://ip:port/atnew/ws/notes/inBox/{boxId}<br>
	 * method :delete
	 * @param boxId	删除内容的boxId
	 * @return	
	 */
	@Path("inbox/{id}")
	@DELETE
	@Produces(MediaType.APPLICATION_JSON)
	public Integer deleteIn( @PathParam("id") String boxId ){
		String[] boxIds = boxId.split(",");
		int i = 0;
		for( String id : boxIds ){
			i = i + noteService.deleteIn( Long.parseLong( id ) );
		}
		return i;
	}

	/**
	 * 删除收件箱内容<br>
	 * 访问地址：http://ip:port/atnew/ws/notes/outbox/{boxId}<br>
	 * method :delete
	 * @param boxId	删除内容的boxId
	 * @return	
	 */
	@Path("outbox/{id}")
	@DELETE
	@Produces(MediaType.APPLICATION_JSON)
	public Integer deleteOut( @PathParam("id") String boxId ){
		String[] boxIds = boxId.split(",");
		int i = 0;
		for( String id : boxIds ){
			i = i + noteService.deleteOut( Long.parseLong( id ) );
		}
		return i;
	}
	
	/**
	 * 清空收件箱<br>
	 * 访问地址：http://ip:port/atnew/ws/notes/inBox<br>
	 * method :delete
	 * @param user	用户编码
	 * @return
	 */
	@Path("inBox")
	@DELETE
	@Produces(MediaType.APPLICATION_JSON)
	public Integer deleteAllIn( @QueryParam("user") String user ){
		return noteService.deleteAllIn( user );
	}
	
	/**
	 * 清空发件箱<br>
	 * 访问地址：http://ip:port/atnew/ws/notes/outBox<br>
	 * method :delete
	 * @param user	用户编码
	 * @return
	 */
	@Path("outBox")
	@DELETE
	@Produces(MediaType.APPLICATION_JSON)
	public Integer deleteAllOut( @QueryParam("user") String user ){
		return noteService.deleteAllOut( user );
	}
	
	/**
	 * 查看邮件内容<br>
	 * 访问地址：http://ip:port/atnew/ws/notes/inBox/{boxid}<br>
	 * method :get
	 * @param id	查看邮件的boxId
	 * @return json格式字符串	<br>	
	 * 			id 主键<br>
	 * 			receiver 接受人<br>	
	 * 			sender	  发送人<br>	
	 * 			content	 内容<br>	
	 * 			sendTime发送时间<br>	
	 *			boxId	boxId
	 */
	@Path("inBox/{id}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Note readIn( @PathParam("id") String id ){
		return noteService.readIn( Long.parseLong( id ) );
	}
	
	/**
	 * 查看邮件内容<br>
	 * 访问地址：http://ip:port/atnew/ws/notes/outBox/{boxid}<br>
	 * method :get
	 * @param id	查看邮件的boxId
	 * @return json格式字符串<br>	
	 * 			id 主键<br>
	 * 			receiver 接受人<br>	
	 * 			sender	  发送人<br>	
	 * 			content	 内容<br>	
	 * 			sendTime发送时间<br>	
	 *			boxId	boxId
	 */
	@Path("outBox/{id}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Note readOut( @PathParam("id") String id ){
		return noteService.readOut( Long.parseLong( id ) );
	}
	
	/**
	 * 获取未读邮件
	 * @param user 用户编码
	 * @return	json格式字符串（数组）
	 *			id 主键<br>
	 * 			receiver 接受人<br>	
	 * 			sender	  发送人<br>	
	 * 			content	 内容<br>	
	 * 			sendTime发送时间<br>	
	 *			boxId	boxId
	 */
	@Path("inbox/notread")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Note> notRead( @QueryParam("user") String user ){
		return noteService.findNotRead( user );
		
	}
}
