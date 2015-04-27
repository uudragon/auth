package com.uud.pay.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.MediaType;

import org.dom4j.DocumentException;

import com.uud.auth.servlet.ServiceBeanContext;
import com.uud.auth.ws.client.AgencyOrderService;
import com.uud.pay.config.AlipayConfig;
import com.uud.pay.util.AlipaySubmit;

/**
 * Servlet implementation class AlipayServlet
 */
public class AlipayAgencyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AlipayAgencyServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//支付类型
				String payment_type = "1";
				//必填，不能修改
				//服务器异步通知页面路径
				String notify_url = "http://dl.yoyolong.com/agency/orders/payment/completed/";
				//需http://格式的完整路径，不能加?id=123这类自定义参数

				//页面跳转同步通知页面路径
				//String return_url = "http://agency.uudragon.com/atnew/AlipayReturn";
				//需http://格式的完整路径，不能加?id=123这类自定义参数，不能写成http://localhost/
				
				String order_no = request.getParameter("order_no");
				AgencyOrderService agencyOrder = ServiceBeanContext.getInstance().getBean("agencyOrder");
				Map<String,Object> order = agencyOrder.getOrder(order_no);
				
				//商户订单号
				String out_trade_no = order_no;
				//商户网站订单系统中唯一订单号，必填
				List<Map<String,Object>> list = (List<Map<String, Object>>) order.get("details");
				
				//订单名称
				int type = (Integer) order.get("order_type");
				String subject = type == 0 ? "订购首期" : "订购宣传品";
				//必填

				//付款金额
				String total_fee = order.get("amount").toString();
				//必填

				//订单描述
				String body = "";
				for( Map<String,Object> detail : list ){
					body = body + "," + detail.get("name");
				}
				if( body.length() > 1 ){
					body = body.substring( 1 );
				}
				//商品展示地址
				String show_url = "http://www.yoyolong.com";
				//需以http://开头的完整路径，例如：http://www.商户网址.com/myorder.html

				//防钓鱼时间戳
				String anti_phishing_key="";
				try {
					anti_phishing_key = AlipaySubmit.query_timestamp();
				} catch (DocumentException e1) {
					e1.printStackTrace();
				}
				//若要使用请调用类文件submit中的query_timestamp函数

				//客户端的IP地址
				String exter_invoke_ip = "119.255.25.130";
				//非局域网的外网IP地址，如：221.0.0.1
				
				
				//////////////////////////////////////////////////////////////////////////////////
				
				//把请求参数打包成数组
				Map<String, String> sParaTemp = new HashMap<String, String>();
				sParaTemp.put("service", "create_direct_pay_by_user");
		        sParaTemp.put("partner", AlipayConfig.partner);
		        sParaTemp.put("seller_email", AlipayConfig.seller_email);
		        sParaTemp.put("_input_charset", AlipayConfig.input_charset);
				sParaTemp.put("payment_type", payment_type);
				sParaTemp.put("notify_url", notify_url);
				//sParaTemp.put("return_url", return_url);
				sParaTemp.put("out_trade_no", out_trade_no);
				sParaTemp.put("subject", subject);
				sParaTemp.put("total_fee", total_fee);
				sParaTemp.put("body", body);
				sParaTemp.put("show_url", show_url);
				sParaTemp.put("anti_phishing_key", anti_phishing_key);
				sParaTemp.put("exter_invoke_ip", exter_invoke_ip);
				
				//建立请求
				String sHtmlText = AlipaySubmit.buildRequest(sParaTemp,"get","确认");
				
				response.setContentType( MediaType.TEXT_HTML);
				PrintWriter pw = null;
				try {
					pw = response.getWriter();
					pw.write( sHtmlText );
				} catch (IOException e) {
					e.printStackTrace();
					pw=null;
				} finally {
					if( pw != null ){
						pw.close();
					}
				}
	}

}
