package com.uud.cs.rest;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Form;
import javax.ws.rs.core.MediaType;

public class KnowledgeRestService {
	public static void main(String args[]){
		KnowledgeRestService rest = new KnowledgeRestService();
		rest.save("1", "1");
	}
	
	public void save( String id, String content ){
		Client client = ClientBuilder.newClient(); 
		WebTarget wt = client.target("http://127.0.0.1:8088/atnew/ws/knowledgeBase");
        Form form = new Form();
        form.param("id", id);
        form.param("content", content);
        wt.request(MediaType.APPLICATION_JSON).put( Entity.entity( form, MediaType.APPLICATION_JSON ), String.class );
	}
}
