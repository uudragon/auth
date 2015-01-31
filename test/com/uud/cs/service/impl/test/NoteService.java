package com.uud.cs.service.impl.test;

import java.util.HashMap;
import java.util.Map;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import com.uud.cs.service.INoteService;

public class NoteService {
	
	public static INoteService service;
	
	static{
		ApplicationContext ctx=new FileSystemXmlApplicationContext("classpath:applicationContext.xml");
		service = (INoteService) ctx.getBean( "noteService" );
	}
	
	public static void save( int i ){
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("receiver", "test" );
		map.put("sender", "test"+1 );
		map.put("content", "testadf" + i );
		service.save( map );
	}
	
	public static void main( String args[] ){
		
		for( int i = 0 ; i < 20 ; i++ ){
			save( i );
		}
		/*Map<String,Object> map = new HashMap<String,Object>();
		map.put( "sender", "test" );
		map.put( "content", "test" );
		
		Page<Note> list = service.findNotes( map, 10, 1 );
		System.out.println( JSON.toJSONString( list ) );*/
	}
}
