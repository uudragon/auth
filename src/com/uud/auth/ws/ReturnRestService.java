package com.uud.auth.ws;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.uud.auth.entity.Page;
import com.uud.auth.servlet.ServiceBeanContext;
import com.uud.cs.entity.ReturnGoodsForm;
import com.uud.cs.service.IReturnService;

@Path("return")
public class ReturnRestService {
	
	private IReturnService returnService = ServiceBeanContext.getInstance().getBean("returnService");
	
	@POST
	@Consumes( MediaType.APPLICATION_JSON )
	public String save( Map<String,Object> map ){
		if( !map.containsKey( "form_no" ) || map.get( "form_no" ) == null ){
			map.put( "form_no", UUID.randomUUID().toString() );
		}
		try{
			returnService.save( map );
		} catch ( Exception e ){
			e.printStackTrace();
			return "false";
		}
		return "true";
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Page<ReturnGoodsForm> findByPage( @QueryParam("pageSize") String pageSize,
			   				  @QueryParam("pageNo") String pageNo,
			   				  @QueryParam("create_time") String create_time,
			   				  @QueryParam("order_no") String order_no,
			   				  @QueryParam("result") String result ){
		Map<String,Object> map = new HashMap<String,Object>();
		map.put( "create_time", create_time );
		map.put( "order_no", order_no );
		map.put( "result", result );
		Page<ReturnGoodsForm> page = returnService.findByPage(map, 
						pageNo == null ? 1 : Integer.parseInt( pageNo ),
						pageSize == null ? 10 : Integer.parseInt( pageSize ) );
		return page;
	}

	
	@PUT
	@Path("{form_no}")
	@Consumes( MediaType.APPLICATION_JSON )
	public String save( @PathParam("form_no") String form_no,
					Map<String,Object> map ){
		map.put( "form_no", form_no );
		returnService.update( map );
		return "true";
	}
}
