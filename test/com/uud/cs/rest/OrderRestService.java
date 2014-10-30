package com.uud.cs.rest;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Form;
import javax.ws.rs.core.MediaType;

public class OrderRestService {
	public static void main(String args[]){
		OrderRestService rest = new OrderRestService();
		//rest.save( "123", "1", "1", "2013-09-12 12:00:00", "1");
		rest.save("1", "1");
		//rest.page();
	}
	
	public void save( String id, String status ){
		Client client = ClientBuilder.newClient(); 
		WebTarget wt = client.target("http://127.0.0.1:8088/atnew/ws/order");
        Form form = new Form();
        form.param("id", id);
        form.param("status", status);
        wt.request(MediaType.APPLICATION_JSON).put( Entity.entity( form, MediaType.APPLICATION_JSON ), String.class );
	}
}
