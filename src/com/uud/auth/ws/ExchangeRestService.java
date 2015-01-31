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
import com.uud.cs.entity.ExchageGoodsForm;
import com.uud.cs.service.IExchangeService;

@Path("exchange")
public class ExchangeRestService {

	private IExchangeService exchangeService = ServiceBeanContext.getInstance().getBean("exchangeService");
	
	@POST
	@Consumes( MediaType.APPLICATION_JSON )
	public String save( Map<String,Object> map ){
		if( !map.containsKey( "form_no" ) || map.get( "form_no" ) == null ){
			map.put("form_no", UUID.randomUUID().toString() );
		}
		try{
			exchangeService.save( map );	
		} catch ( Exception e ){
			e.printStackTrace();
			return "false";
		}
		return "true";
	}
	
	@GET
	@Produces( MediaType.APPLICATION_JSON )
	public Page<ExchageGoodsForm> findByPage(@QueryParam("pageSize") String pageSize,
				  @QueryParam("pageNo") String pageNo,
				  @QueryParam("create_time") String create_time,
				  @QueryParam("status") String status,
				  @QueryParam("result") String result){
		Map<String,Object> map = new HashMap<String,Object>();
		map.put( "create_time", create_time );
		map.put( "status", status );
		map.put( "result", result );
		return exchangeService.findByPage( map, 
				pageSize == null ? 10 : Integer.parseInt( pageSize ),
				pageNo == null ? 1 : Integer.parseInt( pageNo ) );
	}
	
	@PUT
	@Path("{form_no}")
	@Consumes( MediaType.APPLICATION_JSON )
	public void update( @PathParam("form_no") String form_no,
						Map<String,Object> map ){
		map.put("form_no", form_no);
		exchangeService.updateStatus( map );
	}
}
