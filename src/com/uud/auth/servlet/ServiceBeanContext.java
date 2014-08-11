package com.uud.auth.servlet;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

public class ServiceBeanContext {
	
	private ApplicationContext ctx = null;
	
	private static ServiceBeanContext _instance;
	
	public static ServiceBeanContext getInstance(){
		if( _instance == null ){
			synchronized( ServiceBeanContext.class ){
				if( _instance == null ){
					_instance = new ServiceBeanContext();
				}
			}
		}
		return _instance;
	}
	
	@SuppressWarnings("unchecked")
	public <T> T getBean( String bean ){
		return (T) ctx.getBean( bean );
	}
	
	private ServiceBeanContext(){
		init();
	}
	
	private void init(){
		ctx=new FileSystemXmlApplicationContext("classpath:applicationContext.xml");
	}
}
