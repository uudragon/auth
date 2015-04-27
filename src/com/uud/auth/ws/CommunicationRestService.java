package com.uud.auth.ws;

import java.util.List;
import java.util.Map;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.uud.auth.servlet.ServiceBeanContext;
import com.uud.cs.service.ICommunicationService;

/**
 * 联系记录
 * @author yangl
 *
 */
@Path("communication")
public class CommunicationRestService {
	
	private ICommunicationService communicationService = ServiceBeanContext.getInstance().getBean("communicationService");
	
	/**
	 * 查询联系详情
	 * @param code 订单编号
	 * @return	json格式的字符串（list）<br>
	 * 				<table>
	 * 					<tr><td>tel_no</td><td>来电号码</td></tr>
	 *  				<tr><td>form_no</td><td>工单号</td></tr>
	 *   				<tr><td>status</td><td>处理结果：1、未处理 2、处理中 3、关闭</td></tr>
	 *    				<tr><td>theme</td><td>主题</td></tr>
	 *     				<tr><td>user_no</td><td>责任人</td></tr>
	 *      			<tr><td>id</td><td>主键</td></tr>
	 *       			<tr><td>content</td><td>内容</td></tr>
	 *        			<tr><td>next_time</td><td>下次联系时间</td></tr>
	 *       			<tr><td>level</td><td>紧急程1、一般 2、优先 3、紧急</td></tr>
	 *    			    <tr><td>subtype</td><td>子类型：11、产品 12、售后服务 13、订购 14、支付 15、物流
	 *    						31、发票抬头错误 	32、未开发票	33、开票时间长	34、发票丢失  	35、客服态度不好
	 * 							36、客服不专业 	37、客服电话难打	38、物流慢	39、货物丢失		310、物品破损
	 * 							311、快递态度		
	 *    						</td></tr>
	 *        			<tr><td>start_time</td><td>联系时间</td></tr>
	 *        			<tr><td>tel_status</td><td>电话状态：1、无人接听 2、占线 3、接听 4、停机 5、空号 6、错号</td></tr>
	 * 				</table>
	 */
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Map<String,Object>> findCommunication( @QueryParam("code") String code ){
		return communicationService.findCommnication( code );
	}
	
	/**
	 * 
	 * @param map
	 * @return
	 */
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public String save( Map<String,Object> map ){
		Long id = communicationService.save( map );
		return id != null ? "true" : "false";
	}
}
