package com.uud.cs.rest;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Form;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedHashMap;
import javax.ws.rs.core.MultivaluedMap;

public class MessageRestService {
	
	public static void main(String args[]){
		MessageRestService rest = new MessageRestService();
		//rest.save( "123", "1", "1", "2013-09-12 12:00:00", "1");
		rest.save("test", "test");
		//rest.page();
	}
	
	public void save( String subject, String content ){
		Client client = ClientBuilder.newClient(); 
		WebTarget wt = client.target("http://127.0.0.1:8088/at/ws/message/template");
        Form form = new Form();
        form.param("subject", subject);
        form.param("content", content);
        wt.request(MediaType.APPLICATION_JSON).post( Entity.entity( form, MediaType.APPLICATION_JSON ), String.class );
	}
	
	public void page(){
		Client client = ClientBuilder.newClient(); 
		WebTarget wt = client.target("http://127.0.0.1:8088/at/ws/message");
		System.out.println( wt.request().get( String.class ) );
	}
	
	public void save( String target,
			 String template,
			 String sendType,
			 String sendTime,
			 String opUser ){
		
		/*ClientConfig config = new DefaultClientConfig();  
        Client client = Client.create(config); 
        WebResource service = client.resource( "http://127.0.0.1:8086/at/ws" );
        MultivaluedMap<String, String> param =  new MultivaluedHashMap<String, String>();
        param.putSingle("target", target);
        param.putSingle("template", template);
        param.putSingle("sendType", sendType);
        param.putSingle("sendTime", sendTime);
        param.putSingle("opUser", opUser);
        service.path("message").post(String.class, param );*/
		Client client = ClientBuilder.newClient(); 
		WebTarget wt = client.target("http://127.0.0.1:8088/at/ws/message");
		/*Map<String,String> map = new HashMap<String,String>();
		map.put("template", template);
		map.put("target", target);
		map.put("sendType", sendType);
		map.put("sendTime", sendTime);
		map.put("opUser", opUser);*/
		Form entity = new Form();
		entity.param("target", target);
		entity.param("template", template);
		entity.param("sendType", sendType);
		entity.param("sendTime", sendTime);
		entity.param("opUser", opUser);
	        
	        
		wt.request().post( Entity.entity(entity, MediaType.APPLICATION_JSON), String.class );
		
	}
}
