package com.uud.auth.service.impl;


import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;

import com.alibaba.fastjson.JSON;
import com.uud.auth.util.MD5;
import com.uud.auth.ws.LoginMessage;


public class RestTest {
	
	public static void main( String[] args ){
		
		RestTest test = new RestTest();
		LoginMessage lm = test.login();
		test.getResource( lm.getToken() );
		test.checkToken( lm.getToken() );
		test.logout( lm.getToken() );
		test.checkToken( lm.getToken() );
	}
	public void logout( String token ){
		Client client = ClientBuilder.newClient();
		WebTarget wt = client.target( "http://127.0.0.1:8086/at/ws/auth/logout" );
		wt.request().header( "token", token ).get( String.class );
	}
	public LoginMessage login(){
		Client client = ClientBuilder.newClient();
		WebTarget wt = client.target( "http://127.0.0.1:8086/at/ws/auth/login" );
		
		String pwd = MD5.getInstance().getMD5Code( "test" );
		String json = wt.queryParam( "account", "test" )
					.queryParam( "password", pwd ).request().get(String.class);
		System.out.println( json );
		return JSON.parseObject( json, LoginMessage.class );
	}
	
	public void getResource( String token ){
		Client client = ClientBuilder.newClient();
		WebTarget wt = client.target( "http://127.0.0.1:8086/at/ws/auth/resourceListByCode" );
		
		String json = wt.request().header( "token", token ).get(String.class);
		System.out.println( json );
	}
	
	public void checkToken( String token ){
		Client client = ClientBuilder.newClient();
		WebTarget wt = client.target( "http://127.0.0.1:8086/at/ws/auth/checkTokenByCode" );
		String json = wt.queryParam("code", "00").request( ).header( "token", token ).get( String.class );
		System.out.println( json );
	}
}
