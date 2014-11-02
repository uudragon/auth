package com.uud.auth.ws;

import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.uud.auth.entity.Page;
import com.uud.auth.servlet.ServiceBeanContext;
import com.uud.cs.entity.Order;
import com.uud.cs.service.IOrderService;
import com.uud.cs.service.IWorkFormService;

/**
 * 工单
 * @author yangl
 *
 */
@Path("workform")
public class WorkFormRestService {
	
	private IWorkFormService workFormService = ServiceBeanContext.getInstance().getBean("workFormService");
	
	private IOrderService orderService = ServiceBeanContext.getInstance().getBean("orderService");
	
	/**
	 * 保存工单<br>
	 * 路径：atnew/ws/workform 方法：post
	 * @param map json格式字符串<br>
	 * <table>
	 * <tr><td>content</td><td>内容</td></tr>
	 * <tr><td>next_time</td><td>下次联系时间</td></tr>
	 * <tr><td>level</td><td>紧急程度	1、一般 2、优先 	3、紧急</td></tr>
	 * <tr><td>status</td><td>处理结果 1、待处理，2、处理中，3、处理完 </td></tr>
	 * <tr><td>type</td><td>类型 1、咨询  2、查询  3、投诉  4、建议</td></tr>
	 * <tr><td>subtype</td><td>子类型：11、产品 	12、售后服务	13、订购	14、支付	15、物流<br>
	 * 							31、发票抬头错误 	32、未开发票	33、开票时间长	34、发票丢失  	35、客服态度不好
	 * 							36、客服不专业 	37、客服电话难打	38、物流慢	39、货物丢失		310、物品破损
	 * 							311、快递态度
	 * 						</td></tr>
	 * <tr><td>theme</td><td>主题</td></tr>
	 * <tr><td>start_time</td><td>时间</td></tr>
	 * <tr><td>tel_status</td><td>电话状态：1、无人接听 2、占线 3、接听 4、停机 5、空号 6、错号</td></tr>
	 * <tr><td>user</td><td>客服编码</td></tr>
	 * <tr><td>order_no</td><td>订单号（如果顾客没有下单，此项为null）</td></tr>
	 * <tr><td>phone</td><td>来电号码</td></tr>
	 * </table>
	 * @return true/false
	 */
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public String save( Map<String,Object> map ){
		if( workFormService.save( map ) != null ){
			return "true";
		}
		return "false";
	}
	
	/**
	 * 
	 * @param pageSize
	 * @param pageNo
	 * @param paid
	 * @param customer_name
	 * @param status
	 * @return
	 */
	@GET
	@Path("consulation")
	@Produces(MediaType.APPLICATION_JSON)
	public Page<Order> findByPage(@QueryParam("pageSize") String pageSize,
								  @QueryParam("pageNo") String pageNo,
								  @QueryParam("status") String status ){
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("status", status);
		return orderService.findConsultation( map,
				pageSize == null ? 10 : Integer.parseInt( pageSize ),
				pageNo == null ? 1 : Integer.parseInt( pageNo ) );
	}
}
