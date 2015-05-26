package com.uud.auth.ws;

import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;

import org.apache.log4j.Logger;

import com.alibaba.fastjson.JSON;
import com.uud.auth.servlet.ServiceBeanContext;
import com.uud.cs.service.IOrderService;

@Path("orders")
public class Order4WebRestService {
	
	private static final Logger LOG = Logger.getLogger( OrderRestService.class );
	
	private IOrderService orderService = ServiceBeanContext.getInstance().getBean("orderService");
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public String save( Map<String,Object> map ) throws Exception{
		if( LOG.isDebugEnabled() ){
			LOG.debug( "receive:" + JSON.toJSONString( map ) );
		}
		Long id = orderService.save( map );
		Map<String,Object> resmap = new HashMap<String,Object>();
		resmap.put( "result", id != null ? "true" : "false" );
		resmap.put( "reason", "" );
		return JSON.toJSONString( resmap );
	}
}
