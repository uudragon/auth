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
import com.uud.cs.entity.Customer;
import com.uud.cs.service.ICustomerService;

/**
 * 客户信息
 * <table>
 * <tr><td>id</td><td>主键</td></tr>
 * <tr><td>code</td><td>客户编码，唯一</td></tr>
 * <tr><td>type</td><td>客户类型</td></tr>
 * <tr><td>name</td><td>客户姓名</td></tr>
 * <tr><td>sex</td><td>客户性别，1：男；2：女</td></tr>
 * <tr><td>birthday</td><td>客户生日</td></tr>
 * <tr><td>child</td><td>孩子姓名</td></tr>
 * <tr><td>c_sex</td><td>孩子性别</td></tr>
 * <tr><td>email</td><td>电子邮件地址</td></tr>
 * <tr><td>province</td><td>省</td></tr>
 * <tr><td>city</td><td>市</td></tr>
 * <tr><td>district</td><td>区</td></tr>
 * <tr><td>street</td><td街道</td></tr>
 * <tr><td>address</td><td>详细地址</td></tr>
 * <tr><td>post</td><td>邮编</td></tr>
 * <tr><td>phone</td><td>电话</td></tr>
 * <tr><td>main_phone</td><td>联系电话</td></tr>
 * <tr><td>fax</td><td>传真</td></tr>
 * <tr><td>paid</td><td>是否付费</td></tr>
 * <tr><td>status</td><td>状态</td></tr>
 * <tr><td>creator</td><td>创建人</td></tr>
 * <tr><td>create_time</td><td>创建时间</td></tr>
 * <tr><td>updater</td><td>更新人</td></tr>
 * <tr><td>update_time</td><td>更新时间</td></tr>
 * <tr><td>yn</td><td>是否有效</td></tr>
 * </table>
 * 
 * 
 * @author yangl
 *
 */
@Path("customer")
public class CustomerRestService {
	
	private ICustomerService customerService = ServiceBeanContext.getInstance().getBean( "customerService" );
	
	/**
	 * 分页查询客户
	 * 路径： atnew/ws/customer 方法：get
	 * @param pageSize 每页显示条数
	 * @param pageNo 当前页码
	 * @return	page的json格式字符串
	 * 			pageSize	页显示条数<br>
	 * 			pageNo		当前页<br>
	 * 			recordsCount	总条数<br>
	 * 			pageNumber		总页数<br>
	 * 			records			顾客信息对应的字段为customer，参考customer表的字段
	 */
	@GET
	@Consumes(MediaType.APPLICATION_JSON)
	public Page<Customer> findByPage( @QueryParam("pageSize") String pageSize,
									  @QueryParam("pageNo") String pageNo){
		Map<String,Object> map = new HashMap<String,Object>();
		
		return customerService.findByPage(map,
				pageNo != null ? Integer.parseInt( pageNo ) : 1, 
				pageSize != null ? Integer.parseInt( pageSize ) : 10 );
	}
}
