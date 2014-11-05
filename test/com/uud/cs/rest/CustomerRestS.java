package com.uud.cs.rest;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Form;
import javax.ws.rs.core.MediaType;

public class CustomerRestS {
	public static void main(String args[]){
		Client client = ClientBuilder.newClient(); 
		WebTarget wt = client.target("http://127.0.0.1:8088/atnew/ws/customer");
        Form form = new Form();
        form.param("manager", "1");
        form.param("ids", "1,2");
        wt.request(MediaType.APPLICATION_JSON).put( Entity.entity( form, MediaType.APPLICATION_JSON ), String.class );
	}
}
