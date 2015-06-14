package com.uud.auth.ws;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.apache.log4j.Logger;

import com.alibaba.fastjson.JSON;
import com.uud.auth.servlet.ServiceBeanContext;
import com.uud.cs.entity.Order;
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
		map.put("audit", 1);
		Long id = orderService.save( map );
		Map<String,Object> resmap = new HashMap<String,Object>();
		resmap.put( "result", id != null ? "true" : "false" );
		resmap.put( "reason", "" );
		return JSON.toJSONString( resmap );
	}
	
	@GET
	@Path("amount")
	public String getAmount( @QueryParam("startDate") String startDate,
											@QueryParam("endDate") String endDate,
											@QueryParam("pageSize") Integer pageSize,
											@QueryParam("pageNo") Integer pageNo) throws ParseException{
		if( pageSize == null ){
			pageSize =10;
		}
		if( pageNo == null ){
			pageNo = 1;
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		List<Order> list = orderService.findByDate(sdf.parse(startDate), sdf.parse(endDate), pageSize, pageNo);
		Integer total = orderService.countByDate(sdf.parse(startDate), sdf.parse(endDate));
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("recordsCount", total);
		map.put("records", list);
		map.put("pageSize", pageSize);
		map.put("pageNo", pageNo);
		return JSON.toJSONString( map );
	}
}
