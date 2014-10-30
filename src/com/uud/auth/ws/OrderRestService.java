package com.uud.auth.ws;

import java.util.HashMap;
import java.util.Map;

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
import com.uud.cs.entity.Order;
import com.uud.cs.service.ICustomerService;
import com.uud.cs.service.IOrderService;

/**
 * 订单接口
 * @author yangl
 */
@Path("order")
public class OrderRestService {
	
	private IOrderService orderService = ServiceBeanContext.getInstance().getBean("orderService");
	
	@SuppressWarnings("unused")
	private ICustomerService consumerService = ServiceBeanContext.getInstance().getBean("customerService");
	
	/**
	 * 分页查询订单<br>
	 * 
	 * 路径：/atnew/ws/order 方法：get<br>
	 * 
	 * @param pageSize 每页显示条数
	 * @param pageNo	当前页码
	 * @param paid		是否付费，true/false
	 * @param customer_name 客户名称
	 * @return	json格式字符串，
	 * 			pageSize	页显示条数<br>
	 * 			pageNo		当前页<br>
	 * 			recordsCount	总条数<br>
	 * 			pageNumber		总页数<br>
	 * 			records			内容,同样也为json格式 ,和数据库中的字段一样，参考数据库（全部为小写）
	 */
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Page<Order> findByPage( @QueryParam("pageSize") String pageSize,
								  @QueryParam("pageNo") String pageNo,
								  @QueryParam("paid") String paid,
								  @QueryParam("customer_name") String customer_name){
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("paid", paid);
		map.put("customer_name", customer_name);
		return orderService.findAudit(map, 
						pageSize == null ? 10 : Integer.parseInt( pageSize ),
						pageNo == null ? 0 : Integer.parseInt( pageNo ) );
	}
	
	/**
	 * 查询订单详细信息<br>
	 * 
	 * 路径：/atnew/ws/order/{id} 方法：get<br>
	 * 
	 * @param id 订单主键
	 * @return json格式字符串，包含订单详情，参考数据库
	 * 			order 对应 订单
	 * 			consumer 对应客户信息
	 * 			
	 * 			
	 */
	@GET
	@Path("{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Order findById( @PathParam("id") String id ){
		//Map<String,Object> map = new HashMap<String,Object>();
		Order order = orderService.findById( Long.parseLong( id ) );
		/*map.put("order", order);
		Customer consumer = consumerService.findByCode( order.getCustomer_code() );
		map.put("consumer", consumer);*/
		return order;
	}
	
	/**
	 * 更新订单状态
	 * 
	 *  路径：/atnew/ws/order/{id} 方法：put<br>
	 * 
	 * @param id 		主键
	 * @param status	状态  1：待审核、2：审核中、3：无效、4：审核通过
	 * @return true/false 
	 */
	@PUT
	@Path("{id}")
	public String updateStatus( @PathParam("id") String id,
								@QueryParam("status") String status ){
		int i = orderService.updateStatus( Short.parseShort( status ), Long.parseLong( id ));
		return i > 0 ? "true" : "false";
	}
	
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	public String update( Map<String,Object> map ){
		int i = orderService.updateStatus( Short.parseShort( (String) map.get("status") ),
											Long.parseLong( (String) map.get("id") ) );
		return i > 0 ? "true" : "false";
	}
	/**
	 * 保存订单状态
	 * 
	 *  路径：/atnew/ws/order 方法：post<br>
	 * 
	 * @param map 参考orders_orders数据表中的字段，完全按照数据库中的字段（小写）
	 * 
	 * @return true/false
	 */
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public String save( Map<String,Object> map ){
		Long id = orderService.save( map );
		return id != null ? "true" : "false";
	}
}
