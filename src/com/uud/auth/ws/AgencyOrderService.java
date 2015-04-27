package com.uud.auth.ws;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
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
import com.uud.auth.ws.client.AgencyMessageService;
import com.uud.auth.ws.client.OrderSplitService;
import com.uud.cs.entity.Order;
import com.uud.cs.service.ICustomerService;
import com.uud.cs.service.IOrderService;

@Path("agencyOrder")
public class AgencyOrderService {

	private IOrderService orderService = ServiceBeanContext.getInstance().getBean("orderService");

	private OrderSplitService orderSplit = ServiceBeanContext.getInstance().getBean("orderSplit");
	
	private ICustomerService consumerService = ServiceBeanContext.getInstance().getBean("customerService");
	
	private AgencyMessageService agencyMessage = ServiceBeanContext.getInstance().getBean("agencyMessage");
	
	private String getAgentCode( String token ){
		return token;
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Map<String,Object> findByPage( @QueryParam("pageSize") String pageSize,
			   @QueryParam("pageNo") String pageNo,
			   @QueryParam("params") String params,
			   @Context HttpServletRequest request
			   ){
		Map<String,Object> map = new HashMap<String,Object>();
		if( params != null ){
			String[] paramStrings = params.split("&");
			for( String paramStr : paramStrings ){
				String[] param = paramStr.split("=",2);
				if( param.length == 2 ){
					map.put( param[0], param[1] );
				}
			}
		}
		Cookie[] cookies = request.getCookies();
		String agentCode = "";
		for( Cookie cookie : cookies ){
			if( "no".equals( cookie.getName() ) ) {
				agentCode = cookie.getValue();
				break;
			}
		}
		List<Map<String,Object>> address = agencyMessage.getAddress( agentCode );
		String provinces="",citys="",districts="";
		if( address != null ){
			for( Map<String,Object> add : address ){
				if( add.get( "province" ) !=null && !"".equals( add.get( "province" ) ) ){
					provinces = provinces + ",\'" +  add.get( "province" ) +"\'";
				}
				if( add.get( "city" ) !=null && !"".equals( add.get( "city" ) ) ){
					citys = citys + ",\'" +  add.get( "city" )+"\'";
				}
				if( add.get( "district" ) !=null && !"".equals( add.get( "district" ) ) ){
					districts = districts + ",\'" +  add.get( "district" )+"\'";
				}
			}
			if( provinces.length() > 0 ){
				provinces = provinces.substring( 1 );
				provinces = "(" + provinces + ")";
			}
			if( citys.length() > 0 ){
				citys = citys.substring( 1 );
				citys = "(" + citys + ")";
			}
			if( districts.length() > 0 ){
				districts = districts.substring( 1 );
				districts = "(" + districts + ")";
			}
			map.put( "provinces", provinces);
			map.put( "citys", citys);
			map.put( "districts", districts);
		}
		
		if( !"".equals( agentCode ) ){
			map.put("agent_code", agentCode);
		}
		map.put("fromagent", 1);
/*		
		map.put("paid", paid);
		map.put("customer_name", customer_name);
		map.put("phone", phone);
		map.put("payment", payment);
		map.put("audit", audit);
		map.put("start_time", start_time);
		map.put("end_time", end_time);
		map.put("status", status);
		map.put("workflow", workflow);*/
		Page<Order> page = orderService.findAudit(map, 
				pageSize == null ? 10 : Integer.parseInt( pageSize ),
				pageNo == null ? 1 : Integer.parseInt( pageNo ) );
		Map<String,Object> resmap = new HashMap<String,Object>();
		resmap.put( "pageSize", page.getPageSize() );
		resmap.put( "pageNumber", page.getPageNo() );
		resmap.put( "pages", page.getPageNumber() );
		resmap.put( "from", ( page.getPageNo() - 1 ) * page.getPageSize() + 1 );
		resmap.put( "to", page.getPageNo() * page.getPageSize() );
		resmap.put( "total", page.getRecordsCount() );
		resmap.put( "rows", page.getRecords() );
		return resmap;
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public String save( Map<String,Object> map ) throws Exception{
		map.put( "has_invoice", 0 );
		map.put( "paid", 0 );
		map.put( "audit", 1 );
		Long id = orderService.save( map );
		return id != null ? "true" : "false";
	}
	
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	public String update( Map<String,Object> map ) throws Exception{
		int i = orderService.update( map );
		return i > 0 ? "true" : "false";
	}
	
	@GET
	@Path("{id}")
	@Produces( MediaType.APPLICATION_JSON )
	public Order findById( @PathParam("id") Long id ) throws Exception{
		return orderService.findById( id );
	}
	
	@GET
	@Path("{order_no}/split")
	@Produces( MediaType.APPLICATION_JSON )
	public String getSplitForm( @PathParam("order_no") String order_no ){
		return orderSplit.getSplitForm( order_no );
	}
}
