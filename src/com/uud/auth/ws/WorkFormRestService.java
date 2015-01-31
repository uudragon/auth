package com.uud.auth.ws;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.uud.auth.entity.Page;
import com.uud.auth.servlet.ServiceBeanContext;
import com.uud.cs.entity.WorkForm;
import com.uud.cs.service.IOrderService;
import com.uud.cs.service.IWorkFormService;

/**
 * 工单
 * <table>
 * <tr><td>id</td><td>主键</td></tr>
 * <tr><td>code</td><td>编码</td></tr>
 * <tr><td>order_no</td><td>订单编码</td></tr>
 * <tr><td>type</td><td>类型 1、咨询 2、查询 3、投诉 4、建议</td></tr>
 * <tr><td>subtype</td><td>子类型：11、产品 12、售后服务 13、订购 14、支付 15、物流</td></tr>
 * <tr><td>level</td><td>紧急程1、一般 2、优先 3、紧急</td></tr>
 * <tr><td>phone</td><td>来电号码</td></tr>
 * <tr><td>theme</td><td>主题</td></tr>
 * <tr><td>content</td><td>内容</td></tr>
 * <tr><td>status</td><td>状态</td></tr>
 * <tr><td>consumer_code</td><td>客户编码</td></tr>
 * <tr><td>consumer_name</td><td>客户姓名</td></tr>
 * <tr><td>create_time</td><td>创建时间</td></tr>
 * <tr><td>user</td><td>客服编码</td></tr>
 * <tr><td>next_time</td><td>下次联系时间</td></tr>
 * </table>
 * 
 * @author yangl
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
		if( Integer.parseInt( ( (String)map.get("type") ) ) != 3 ){
			map.put("update_time", new Date() );
		}
		if( workFormService.save( map ) != null ){
			return "true";
		}
		return "false";
	}
	
	/**
	 * 投诉分页查询
	 * 路径：atnew/ws/workform/complain 方法：get
	 * @param pageSize	每页现实条数
	 * @param pageNo	当前页码	
	 * @param status	状态 1、未处理 2、处理中 3、关闭
	 * @return	json格式字符串，
	 * 			pageSize	页显示条数<br>
	 * 			pageNo		当前页<br>
	 * 			recordsCount	总条数<br>
	 * 			pageNumber		总页数<br>
	 * 			records			订单信息，参考（OrderRestService）
	 */
	@GET
	@Path("complain")
	@Produces(MediaType.APPLICATION_JSON)
	public Page<WorkForm> findConsulationByPage(@QueryParam("pageSize") String pageSize,
								  @QueryParam("pageNo") String pageNo,
								  @QueryParam("status") String status ){
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("status", status);
		map.put("type", 3);
		return workFormService.findByPage( map,
				pageSize == null ? 10 : Integer.parseInt( pageSize ),
				pageNo == null ? 1 : Integer.parseInt( pageNo ) );
	}
	
	@GET
	@Path("check")
	@Produces(MediaType.APPLICATION_JSON)
	public Page<WorkForm> findCheckByPage(@QueryParam("pageSize") String pageSize,
								  @QueryParam("pageNo") String pageNo,
								  @QueryParam("status") String status ){
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("status", status);
		map.put("type", 2);
		return workFormService.findByPage( map,
				pageSize == null ? 10 : Integer.parseInt( pageSize ),
				pageNo == null ? 1 : Integer.parseInt( pageNo ) );
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Page<WorkForm> findByPage(@QueryParam("pageSize") String pageSize,
								  @QueryParam("pageNo") String pageNo,
								  @QueryParam("status") String status ){
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("status", status);
		return workFormService.findByPage( map,
				pageSize == null ? 10 : Integer.parseInt( pageSize ),
				pageNo == null ? 1 : Integer.parseInt( pageNo ) );
	}
	
	@Path("{id}")
	@PUT
	public String updateStatus( @QueryParam("id") Long id,
								@FormParam("status") Integer status ){
		if( workFormService.updateStatus( status, id ) > 0 ){
			return "true";
		}
		return "false";
	}
	
}
