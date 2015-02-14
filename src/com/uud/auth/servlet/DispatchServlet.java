package com.uud.auth.servlet;


import java.io.IOException;
import java.lang.reflect.Method;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.uud.auth.impls.info.AccessFactory;
import com.uud.auth.util.ConfigHelper;
import com.uud.auth.util.PackagesSchedule;


public class DispatchServlet extends HttpServlet{
	
	
	 /**
	 * serialVersionUID:TODO（用一句话描述这个变量表示什么）
	 *
	 * @since 1.0.0
	 */
	
	private static final long serialVersionUID = -2871793876774691057L;
	
	

	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		ServiceBeanContext.getInstance();
		AccessFactory.getAccessInfo().load();
		ConfigHelper.getInstance();
		PackagesSchedule.init();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String className=req.getParameter("className");
		String methodName=req.getParameter("methodName");
		if( className != null && methodName != null ){
			try {
				Object obj=ServiceBeanContext.getInstance().getBean(className);
				Method invokeMethod=obj.getClass().getMethod(methodName, 
						HttpServletRequest.class, HttpServletResponse.class );
				
				if(invokeMethod!=null){
					resp.setCharacterEncoding("UTF-8");
					invokeMethod.invoke(obj,req,resp);
				}
				else{
				   throw new Exception("the invokeMethod is null");
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

}
