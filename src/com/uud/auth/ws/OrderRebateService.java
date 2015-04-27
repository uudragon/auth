package com.uud.auth.ws;

import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.uud.auth.entity.Page;
import com.uud.auth.servlet.ServiceBeanContext;
import com.uud.cs.entity.Order;
import com.uud.cs.service.IOrderService;

@Path("rebate")
public class OrderRebateService {
	
	private IOrderService orderService = ServiceBeanContext.getInstance().getBean("orderService");
	
	private String getAgentCode( String token ){
		return token;
	}
	
	private String getProvince( String token ){
		return "山东省";
	}
	private String getCity( String token ){
		return "济南市";
	};
	private String getDistrict( String token ){
		return "历下区";
	};
	@GET
	@Path("orders")
	@Consumes( MediaType.APPLICATION_JSON )
	public Map<String,Object> findByPage( @QueryParam("pageSize") String pageSize,
			   @QueryParam("pageNo") String pageNo,
			   @QueryParam("params") String params,
			   @QueryParam("province") String province,
			   @QueryParam("city") String city,
			   @QueryParam("district") String district
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
		String token =  (String) map.get("token");
		map.put("province", getProvince( token ) );
		map.put("city", getCity( token ) );
		map.put("district", getDistrict( token ) );
		
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
	
}
