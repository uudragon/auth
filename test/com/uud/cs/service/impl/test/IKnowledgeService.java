package com.uud.cs.service.impl.test;

import java.util.HashMap;
import java.util.Map;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import com.alibaba.fastjson.JSON;
import com.uud.auth.entity.Page;
import com.uud.cs.entity.KnowledgeBase;
import com.uud.cs.service.IKnowledgeBaseService;

public class IKnowledgeService {
	public static IKnowledgeBaseService service;
	static{
		ApplicationContext ctx=new FileSystemXmlApplicationContext("classpath:applicationContext.xml");
		service = (IKnowledgeBaseService) ctx.getBean( "knowledgeBaseService" );
	}
	
	public static void save( ){
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("title", "test" );
		map.put("content", "test" );
		map.put("creater", "testadf" );
		map.put("type", 1);
		service.save( map );
	}
	
	public static void findPage(){
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("title", "test" );
		Page<KnowledgeBase> page = service.findByPage(map, 1, 10);
		System.out.println( JSON.toJSONString( page ) );
	}
	
	public static void update(){
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("title", "tes12t" );
		map.put("id", 1);
		service.updateKnowledgeBase(map);
	}
	
	public static void updateBN(){
		service.updateBN( 1l );
	}
	
	public static void findById(){
		
		System.out.println( JSON.toJSONString( service.findById( 1l ) ) );
	}
	
	public static void main( String[] args ){
		service.deleteById( 2l );
	}
}
