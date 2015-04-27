package com.uud.auth.ws.client;

import java.util.Map;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

import org.glassfish.jersey.client.ClientConfig;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.uud.auth.util.ConfigHelper;

@Service("agencyOrder")
public class AgencyOrderService {
	
	private static WebTarget wt;
	
	public AgencyOrderService(){
		ClientConfig config = new ClientConfig();
		config.register( MyJacksonJsonProvider.class );
		Client client = ClientBuilder.newClient( config ); 
		wt = client.target( ConfigHelper.getInstance().getString( "agencyorder.restURL" ) );
	}
	
	public Map<String,Object> getOrder( String order_no ){
		String json = wt.path("query_orders/"+order_no+"/")
				.request( MediaType.APPLICATION_JSON )
				.get( String.class );
		 return JSON.parseObject( json, new TypeReference<Map<String,Object>>(){} );
	}
	
	public static void main( String[] args ){
		AgencyOrderService service = new AgencyOrderService();
		service.getOrder( "c68c99b8c27000017abe10a015e31e0b" );
	}
}
