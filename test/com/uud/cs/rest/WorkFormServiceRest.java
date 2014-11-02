package com.uud.cs.rest;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Form;
import javax.ws.rs.core.MediaType;

public class WorkFormServiceRest {
	public static void main(String args[]){
		WorkFormServiceRest rest = new WorkFormServiceRest();
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("theme", "test");
		map.put("status", 1);
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		map.put("start_time", df.format( new Date() )  );
		map.put("user", 1);
		map.put("tel_status", 1);
		map.put("type", 1);
		map.put("subtype", 1);
		map.put("level", 1);
		map.put("next_time", df.format( new Date() ) );
		map.put("content", "testawerew");
		map.put("order_no", "12323");
		map.put("phone", "123213");
		System.out.println(map);
		rest.save( map );
		//{"content":"testawerew","next_time":"2014-11-01", 
		//"level":1, "status":1, "subtype":1, "theme":"test",
		//"start_time":"2014-11-01", "tel_status":1, "type":1,
		//"user":"1","order_no":"test123","phone":"123213"} 测试数据
	}
	
	public void save( Map<String,Object> map ){
		Client client = ClientBuilder.newClient(); 
		WebTarget wt = client.target("http://127.0.0.1:8088/atnew/ws/order");
        Form form = new Form();
        Iterator<String> iterator = map.keySet().iterator();
        while( iterator.hasNext() ){
        	String key = iterator.next();
        	form.param( key, map.get( key ).toString() );
        }
        wt.request(MediaType.APPLICATION_JSON).put( Entity.entity( form, MediaType.APPLICATION_JSON ), String.class );
	}
}
