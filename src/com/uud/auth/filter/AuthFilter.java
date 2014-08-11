package com.uud.auth.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.uud.auth.entity.User;

/**
 * Servlet Filter implementation class AuthFilter
 */
public class AuthFilter implements Filter {

    /**
     * Default constructor. 
     */
    public AuthFilter() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		String url = ((HttpServletRequest)request).getRequestURI();
		String qs = ((HttpServletRequest)request).getQueryString();
		String context = ((HttpServletRequest)request).getContextPath();
		if( !( ( qs != null && qs.contains( "loginAction" ) ) || url.contains( ".css" ) ||
				url.contains( ".js" ) || url.contains( ".jpg" ) ||
				url.contains(".woff") || url.contains( ".ttf" ) || 
				url.contains( ".svg" ) || url.contains( "login.html" ) || 
				url.contains("ws/auth") ) ){
			HttpSession session = ((HttpServletRequest)request).getSession();
			User user = (User) session.getAttribute( "user" );
			if( user == null ){
				((HttpServletResponse)response).sendRedirect( context + "/login.html" );
				//((HttpServletRequest)request).getRequestDispatcher("./login.html").forward( request, response );
			} else {
				chain.doFilter(request, response);
			}
		} else if ( url.contains( "login.html" ) /*|| 
				( qs != null && qs.contains( "className=loginAction&methodName=login" ) ) */){
			HttpSession session = ((HttpServletRequest)request).getSession();
			User user = (User) session.getAttribute( "user" );
			if( user != null ){
				((HttpServletResponse)response).sendRedirect( context );
			}
		}
		chain.doFilter(request, response);
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
