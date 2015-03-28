package com.uud.pay.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.uud.auth.servlet.ServiceBeanContext;
import com.uud.cs.entity.Order;
import com.uud.cs.service.IOrderService;
import com.uud.pay.util.AlipayNotify;

/**
 * Servlet implementation class AlipayReturnServlet
 */
public class AlipayReturnServlet extends HttpServlet {
	private static Logger log = Logger.getLogger( AlipayReturnServlet.class );
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AlipayReturnServlet() {
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
		boolean flag = false;
		Map<String,String> params = new HashMap<String,String>();
		Map requestParams = request.getParameterMap();
		for (Iterator iter = requestParams.keySet().iterator(); iter.hasNext();) {
			String name = (String) iter.next();
			String[] values = (String[]) requestParams.get(name);
			String valueStr = "";
			for (int i = 0; i < values.length; i++) {
				valueStr = (i == values.length - 1) ? valueStr + values[i]
						: valueStr + values[i] + ",";
			}
			params.put(name, valueStr);
		}
		String out_trade_no = new String(request.getParameter("out_trade_no").getBytes("ISO-8859-1"),"UTF-8");
		String trade_status = new String(request.getParameter("trade_status").getBytes("ISO-8859-1"),"UTF-8");
		if(AlipayNotify.verify(params)){//验证成功
			if( trade_status.equals("TRADE_FINISHED") || trade_status.equals("TRADE_SUCCESS") ){
				IOrderService orderService = ServiceBeanContext.getInstance().getBean("orderService");
				Order order = orderService.findByNo( out_trade_no );
				if( order != null && order.getPaid() != 1 ){
					Map<String,Object> map = new HashMap<String,Object>();
					map.put( "id", order.getId()  );
					map.put( "paid", 1 );
					if( orderService.update( map ) > 0 ){
						flag = true;
					} else {
						flag = false;
					}
				} else {
					if ( order == null ){
						log.debug( "not found order :"+ out_trade_no );
					}
					flag = false;
				}
			}
		}else{//验证失败
			flag = false;
		}
		
		String success = "fail";
		if( flag ){
			success = "success";
		}

		PrintWriter pw = null;
		try {
			pw = response.getWriter();
			pw.write( success );
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
