package com.uud.auth.ws;

import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.alibaba.fastjson.JSON;
import com.uud.auth.entity.Page;
import com.uud.auth.servlet.ServiceBeanContext;
import com.uud.cs.entity.KnowledgeBase;
import com.uud.cs.service.IKnowledgeBaseService;

/**
 * 
 * 知识库<br>
 * 字段说明<br>
 * 
 * <table>
 * <tr><td>主键</td><td>id</td></tr>
 * <tr><td>标题</td><td>title</td></tr>
 * <tr><td>内容</td><td>content</td></tr>
 * <tr><td>创建人</td><td>creater</td></tr>
 * <tr><td>创建时间</td><td>update_time</td></tr>
 * <tr><td>类型</td><td>type</td></tr>
 * <tr><td>浏览次数</td><td>browse_number</td></tr>
 * </table>
 * 
 * @author yangl
 *
 */
@Path("knowledgeBase")
public class KnowledgeBaseService {
	
	private IKnowledgeBaseService knowledgeBaseService = (IKnowledgeBaseService)ServiceBeanContext
												.getInstance().getBean("knowledgeBaseService");
	
	/**
	 * 新增知识
	 * 路径：/atnew/ws/knowledgeBase 方法：post
	 * @param map 包换字段title、content、creater、update_time、type、browse_number
	 * @return true/false
	 */
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public String save( Map<String,Object> map ){
		if ( knowledgeBaseService.save( map ) != null ){
			return "true";
		}
		return "false";
	}
	
	/**
	 * 删除知识库
	 * 路径：/atnew/ws/knowledgeBase/{id} 方法：delete
	 * @param id 知识的主键
	 * @return true/false
	 */
	@DELETE
	@Path("{id}")
	public String delete( @PathParam("id") Long id ){
		if( knowledgeBaseService.deleteById( id ) > 0 ){
			return "true";
		}
		return "false";
	}
	
	/**
	 * 浏览某一个知识点
	 * 路径：/atnew/ws/knowledgeBase/{id} 方法：get
	 * @param id 知识点主键
	 * @return	json格式字符串，包换字段id、title、content、creater、update_time、type、browse_number
	 */
	@GET
	@Path("{id}")
	public String findById( @PathParam("id") Long id ){
		return JSON.toJSONString( knowledgeBaseService.findById( id ) );
	}
	
	/**
	 * 根据条件查询知识点
	 * 路径：/atnew/ws/knowledgeBase 方法：get
	 * @param title		标题
	 * @param content	内容
	 * @param type		类型
	 * @param pageNo	当前页
	 * @param pageSize	每页现实条数
	 * @return	pageSize	页显示条数<br>
	 * 			pageNo		当前页<br>
	 * 			recordsCount	总条数<br>
	 * 			pageNumber		总页数<br>
	 * 			records			内容,包含字段id、title、content、creater、update_time、type、browse_number
	 */
	@GET
	public String findByPage( @QueryParam("title") String title,
							  @QueryParam("content") String content,
							  @QueryParam("type") String type,
							  @QueryParam("pageNo") String pageNo,
							  @QueryParam("pageSize") String pageSize ){
		Map<String,Object> map = new HashMap<String,Object>();
		map.put( "title", title );
		map.put( "content", content );
		map.put( "type", type );
		Page<KnowledgeBase> page = knowledgeBaseService.findByPage( map,
				pageNo==null ? null : Integer.parseInt( pageNo ), 
				pageSize==null ? null :	Integer.parseInt( pageSize ) );
		return JSON.toJSONString( page );
	}
	
	/**
	 * 更新知识点
	 * 路径：/atnew/ws/knowledgeBase 方法：delete
	 * @param map 包含字段id、title、content、creater、update_time、type、browse_number
	 * @return
	 */
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	public String update( Map<String,Object> map ){
		
		if( knowledgeBaseService.updateKnowledgeBase( map ) > 0 ){
			return "true";
		}
		return "false";
	}
}
