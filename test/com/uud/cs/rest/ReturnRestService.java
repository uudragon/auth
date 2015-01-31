package com.uud.cs.rest;

import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Form;
import javax.ws.rs.core.MediaType;

import org.codehaus.jackson.jaxrs.JacksonJsonProvider;
import org.glassfish.jersey.client.ClientConfig;

public class ReturnRestService {
	public static void main(String args[]){
		ReturnRestService rest = new ReturnRestService();
		rest.save("1", "1");
	}
	
	public void save( String id, String content ){
		ClientConfig config = new ClientConfig();
		config.register( JacksonJsonProvider.class );
		Client client = ClientBuilder.newClient( config ); 
		
		WebTarget wt = client.target("http://127.0.0.1:8088/atnew/ws/return");
        Form form = new Form();
        form.param("order_no", "1234");
        form.param("type", "0");
        form.param("reason", "test");
        form.param("issn", "0");
        form.param("number", "1");
        form.param("is_enter", "0");
        form.param("refund", "0");
        form.param("holder", "1");
        form.param("commission_charge", "11");
        form.param("payee", "11");
        form.param("accounts", "012");
        form.param("bank", "120");
        /*
         "order_no":"1234","type":"0","reason":"test","issn":"0","number":"1","is_enter":"0","refund":"0"
         "holder":"1","commission_charge":"11","payee":"11","accounts":"012","bank":"120"
         
         
         
         {"order_no":"1234","type":"0","reason":"test","issn":"0","number":"1",
         "is_enter":"0","refund":"0","form_no":"123","create_user":"test","holder":"1",
         "commission_charge":"11","payee":"11","accounts":"012","bank":"120"}
         */
        /*ObjectNode payload =  ObjectMapperProvider.getObjectMapper().createObjectNode();*/
        Map<String,Object> payload = new HashMap<String,Object>();
        payload.put("order_no", "1234");
        payload.put("type", "0");
        payload.put("reason", "test");
        payload.put("issn", "0");
        payload.put("number", "1");
        payload.put("is_enter", "0");
        payload.put("refund", "0");
        payload.put("holder", "1");
        payload.put("commission_charge", "11");
        payload.put("payee", "11");
        payload.put("accounts", "012");
        payload.put("bank", "120");
        wt.request().post( Entity.entity( payload, MediaType.APPLICATION_JSON ), String.class );
	}
}
