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

@Path("knowledgeBase")
public class KnowledgeBaseService {
	
	private IKnowledgeBaseService knowledgeBaseService = (IKnowledgeBaseService)ServiceBeanContext
												.getInstance().getBean("knowledgeBaseService");
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public String save( Map<String,Object> map ){
		if ( knowledgeBaseService.save( map ) != null ){
			return "true";
		}
		return "false";
	}
	
	@DELETE
	@Path("{id}")
	public String delete( @PathParam("id") Long id ){
		if( knowledgeBaseService.deleteById( id ) > 0 ){
			return "true";
		}
		return "false";
	}
	
	@GET
	@Path("{id}")
	public String findById( @PathParam("id") Long id ){
		return JSON.toJSONString( knowledgeBaseService.findById( id ) );
	}
	
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
	
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	public String update( Map<String,Object> map ){
		
		if( knowledgeBaseService.updateKnowledgeBase( map ) > 0 ){
			return "true";
		}
		return "false";
	}
}
