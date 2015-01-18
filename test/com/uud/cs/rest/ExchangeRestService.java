package com.uud.cs.rest;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Form;
import javax.ws.rs.core.MediaType;

public class ExchangeRestService {
	
	public void save( String id, String content ){
		Client client = ClientBuilder.newClient(); 
		WebTarget wt = client.target("http://127.0.0.1:8088/atnew/ws/exchange");
        Form form = new Form();
        form.param("order_no", "1234");
        form.param("type", "1");
        form.param("issn", "0");
        form.param("exchange_subject", "1");
        form.param("number", "1");
        form.param("reason", "test");
        form.param("is_enter", "0");
        form.param("send_status", "0");
        form.param("send_type", "0");
        form.param("fare", "0");
        form.param("holder", "1");
        
        /*
         "order_no":"1234","type":"1","issn":"0","exchange_subject":"1"
         "number":"1","reason":"test","is_enter":"0","send_status":"0","send_type":"0"
         "fare":"0","holder":"1"
         
         
        {"order_no":"1234","type":"1","issn":"0","exchange_subject":"1",
        "number":"1","reason":"test","form_no":"123","create_user":"123",
	"is_enter":"0","send_status":"0","fare":"0","holder":"1","send_type":"0"}
	
	
	
	{"update_user":"test","result":"1","status":"1","is_enter":1,"send_status":1}
         */
        wt.request(MediaType.APPLICATION_JSON).post( Entity.entity( form, MediaType.APPLICATION_JSON ), String.class );
	}
}
