package com.uud.auth.ws;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
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
import com.uud.cs.entity.Order;
import com.uud.cs.service.ICustomerService;
import com.uud.cs.service.IOrderService;

/**
 * 订单接口
 * 数据表字段说明
 * 	 <br>
	 * <b>订单详情（orders_ordersdetail）</b>
	 * <table>
	 * <tr><td>id</td><td>主键</td></tr>
	 * <tr><td>orders_no</td><td>订单号，非空</td></tr>
	 * <tr><td>product_no</td><td>产品编号，非空</td></tr>
	 * <tr><td>effective</td><td>有效日期，应发货日期</td></tr>
	 * <tr><td>qty</td><td>应发数量</td></tr>
	 * <tr><td>bulk</td><td>产品包商品总体积</td></tr>
	 * <tr><td>weight</td><td>产品包商品总重量</td></tr>
	 * <tr><td>status</td><td>状态（-1：异常；0：未发货；1：已发货）  </td></tr>
	 * <tr><td>yn</td><td>是否有效</td></tr>
	 * </table>
	 * 
	 * <br>
	 * <b>订单（orders_orders）</b>
	 * <table>
	 * <tr><td>id</td><td>主键</td></tr>
	 * <tr><td>orders_no</td><td>订单号，非空</td></tr>
	 * <tr><td>order_type</td><td>订单类型。0：单品订单；1：多品订单 </td></tr>
	 * <tr><td>effective</td><td>订单生效日期</td></tr>
	 * <tr><td>deadline</td><td> 订单截止日期 </td></tr>
	 * <tr><td>customer_code</td><td> 客户编号 </td></tr>
	 * <tr><td>has_invoice</td><td>是否有发票。0：无；1：有</td></tr>
	 * <tr><td>source</td><td>订单来源。1：网站；2：电话  </td></tr>
	 * <tr><td>agent_code</td><td>所属代理商编号</td></tr>
	 * <tr><td>coupon_code</td><td>优惠码</td></tr>
	 * <tr><td>discount_amount</td><td>折扣金额</td></tr>
	 * <tr><td>amount</td><td>付款金额</td></tr>
	 * <tr><td>paid</td><td>是否付款 1 付款 0 未付款</td></tr>
	 * <tr><td>payment</td><td>付款方式。1银行;2支付宝;3货到付款</td></tr>
	 * <tr><td>status</td><td>订单状态：1：待审核、2：审核中、3：无效、4：审核通过</td></tr>
	 * <tr><td>validity</td><td>订单有效期</td></tr>
	 * <tr><td>order_time</td><td>下单时间,非空 </td></tr>
	 * <tr><td>creator</td><td>受理人</td></tr>
	 * <tr><td>create_time</td><td>创建时间，非空</td></tr>
	 * <tr><td>updater</td><td>更新人</td></tr>
	 * <tr><td>update_time</td><td>更新时间</td></tr>
	 * <tr><td>contact_count</td><td>联系次数</td></tr>
	 * <tr><td>yn</td><td>失效标识：0：有效；1：无效</td></tr>
	 * </table>
	 * 
	 * 	 <br>
	 * <b>客户信息（customer_customer）</b>
	 * <table>
	 * <tr><td>id</td><td>主键</td></tr>
	 * <tr><td>code</td><td>客户编码，唯一</td></tr>
	 * <tr><td>type</td><td>客户类型</td></tr>
	 * <tr><td>name</td><td>客户姓名</td></tr>
	 * <tr><td>sex</td><td>客户性别，1：男；2：女</td></tr>
	 * <tr><td>birthday</td><td>客户生日</td></tr>
	 * <tr><td>child</td><td>孩子姓名</td></tr>
	 * <tr><td>c_sex</td><td>孩子性别  </td></tr>
	 * <tr><td>email</td><td>电子邮件地址</td></tr>
	 * <tr><td>province</td><td>省</td></tr>
	 * <tr><td>city</td><td>市</td></tr>
	 * <tr><td>district</td><td>区</td></tr>
	 * <tr><td>street</td><td>街道</td></tr>
	 * <tr><td>address</td><td>详细地址</td></tr>
	 * <tr><td>post</td><td>邮编</td></tr>
	 * <tr><td>phone</td><td>电话</td></tr>
	 * <tr><td>main_phone</td><td></td></tr>
	 * <tr><td>fax</td><td>传真</td></tr>
	 * <tr><td>status</td><td>状态</td></tr>
	 * <tr><td>creator</td><td>创建人</td></tr>
	 * <tr><td>create_time</td><td>创建时间</td></tr>
	 * <tr><td>updater</td><td>更新人</td></tr>
	 * <tr><td>update_time</td><td>更新时间</td></tr>
	 * <tr><td>yn</td><td>是否有效</td></tr>
	 * </table>
 * 
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
	 * @param status 	审核状态：1：待审核、2：审核中、3：无效、4：审核通过<br>
	 * 					（核实订单为待审核订单，拆分订单为审核通过订单）
	 * @return	json格式字符串，
	 * 			pageSize	页显示条数<br>
	 * 			pageNo		当前页<br>
	 * 			recordsCount	总条数<br>
	 * 			pageNumber		总页数<br>
	 * 			records			内容,同样也为json格式 ,和数据库中的字段一样，参考数据库（全部为小写）<br>
	 * 							另外包含顾客信息对应的字段为customer，订单详情details<br>
	 * 							客户信息的字段参考customer_customer表的字段<br>
	 * 							订单详情的字段参考orders_ordersdetail表的字段
	 */
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Page<Order> findByPage( @QueryParam("pageSize") String pageSize,
								   @QueryParam("pageNo") String pageNo,
								   @QueryParam("paid") String paid,
								   @QueryParam("customer_name") String customer_name,
								   @QueryParam("status") String status){
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("paid", paid);
		map.put("customer_name", customer_name);
		map.put("status", 1);
		return orderService.findAudit(map, 
						pageSize == null ? 10 : Integer.parseInt( pageSize ),
						pageNo == null ? 1 : Integer.parseInt( pageNo ) );
	}
	
	/**
	 * 查询订单详细信息<br>
	 * 
	 * 路径：/atnew/ws/order/{id} 方法：get<br>
	 * 
	 * @param id 订单主键
	 * @return json格式字符串，包含订单详情，字段参考orders_orders表的字段
	 * 							另外包含顾客信息对应的字段为customer，订单详情details<br>
	 * 							客户信息的字段参考customer_customer表的字段<br>
	 * 							订单详情的字段参考orders_ordersdetail表的字段
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
	@Consumes(MediaType.APPLICATION_JSON)
	public String updateStatus( @PathParam("id") String id,
								Map<String,Object> map ){
		String status = (String) map.get("status");
		int i = orderService.updateStatus( Short.parseShort( status ), Long.parseLong( id ));
		return i > 0 ? "true" : "false";
	}
	
	/**
	 * 更新订单内容
	 * 路径：/atnew/ws/order 方法：put<br>
	 * 
	 * @param map	更新订单 json格式的字符串
	 * 				字段参考orders_orders表的字段<br>
	 * 				另外顾客信息对应的字段为customer，订单详情对应的字段为details<br>
	 * 				客户信息的字段参考customer_customer表的字段<br>
	 * 				订单详情的字段参考orders_ordersdetail表的字段
	 * @return true/false
	 */
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
	 * @param map	字段参考orders_orders表的字段<br>
	 * 				另外顾客信息对应的字段为customer，订单详情对应的字段为details<br>
	 * 				客户信息的字段参考customer_customer表的字段<br>
	 * 				订单详情的字段参考orders_ordersdetail表的字段
	 * @return true/false
	 */
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public String save( Map<String,Object> map ){
		Long id = orderService.save( map );
		return id != null ? "true" : "false";
	}
}
