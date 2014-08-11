package com.uud.auth.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;

import com.alibaba.fastjson.JSON;
import com.uud.auth.entity.User;
import com.uud.auth.impls.info.AccessFactory;
import com.uud.auth.util.ErrorCode;
import com.uud.auth.ws.LoginMessage;

@Controller("loginAction")
public class LoginAction {
	
	public void login( HttpServletRequest request,HttpServletResponse response ){
		String account = request.getParameter( "account" );
		String pwd = request.getParameter( "password" );
		String domain = "bam";
		boolean flag = false;
		String message = null;
		User user = AccessFactory.getAccessInfo().getUser( domain, account );
		if( user != null && user.getIsValid() && user.getPassword().equals( pwd ) ){
			Long userId = user.getId();
			Long[] roleIds = AccessFactory.getAccessInfo().getRoles( userId, domain );
			List<Long> rList = AccessFactory.getAccessInfo().getRole( "00" , domain );
			for( Long roleId : roleIds ){
				for( Long rId : rList ){
					if( rId.equals( roleId ) ){
						flag = true ;
					}
				}
			}
			if( flag == false ){
				message = ErrorCode.AUTH_ACCOUNT_ACCESS + ": no permission to access";
			}
		} else {
			message = ErrorCode.AUTH_PASSWORD_ERROR + ": account or password is wrong";
		}
		/*if( flag == true ){
			HttpSession session = request.getSession();
			session.setAttribute( "user", user );
			String context = request.getContextPath();
			try {
				request.getRequestDispatcher( "./index.html").forward( request, response );
			} catch (ServletException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			try {
				//((HttpServletResponse)response).addHeader("If-Modified-Since", "0");;
				//response.setHeader("If-Modified-Since", "0");
				response.sendRedirect( context );
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {*/
		if( flag == true ){
			HttpSession session = request.getSession();
			session.setAttribute( "user", user );
		}
			LoginMessage lm = new LoginMessage();
			lm.setLegal( flag );
			lm.setMessage( message );
			PrintWriter writer = null;
			try {
				writer = response.getWriter();
				writer.write( JSON.toJSONString( lm ) );
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				if( writer != null ){
					writer.close();
				}
			}
		//}
	}
	
	public void logout( HttpServletRequest request,HttpServletResponse response ){
		HttpSession session = request.getSession();
		session.removeAttribute( "user" );
		try {
			request.getRequestDispatcher("").forward( request, response );
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
