package com.uud.cs.service.impl.test;

import java.util.HashMap;
import java.util.Map;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import com.alibaba.fastjson.JSON;
import com.uud.cs.service.IOrderService;

public class OrderService {
	
	private static IOrderService service;
	static{
		ApplicationContext ctx=new FileSystemXmlApplicationContext("classpath:applicationContext.xml");
		service = (IOrderService) ctx.getBean( "orderService" );
	}
	
	public static void main( String args[] ){
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("customer_name", "2");
		map.put("paid", false);
		System.out.println( JSON.toJSONString( service.findAudit(map, 10, 1 ) ) );
	}
}
