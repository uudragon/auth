package com.uud.auth.ws.client;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

import org.glassfish.jersey.client.ClientConfig;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.uud.auth.util.ConfigHelper;

@Service("agencyMessage")
public class AgencyMessageService {
	
	private static WebTarget wt;
	
	public AgencyMessageService(){
		ClientConfig config = new ClientConfig();
		config.register( MyJacksonJsonProvider.class );
		Client client = ClientBuilder.newClient( config ); 
		wt = client.target( ConfigHelper.getInstance().getString( "agencybusiness.restURL" ) );
	}
	
	@SuppressWarnings("unchecked")
	public List<Map<String,Object>> getAddress( String agentCode ){
		Map<String,Object> map = new HashMap<String,Object>();
		map.put( "agencyNo", agentCode );
		String json = wt.path("queryAgencyAreaList")
				.request( MediaType.APPLICATION_JSON )
				.post( Entity.entity(map, MediaType.APPLICATION_JSON), String.class );
		map = JSON.parseObject( json, new TypeReference<Map<String,Object>>(){} );
		System.out.println(json);
		 return (List<Map<String, Object>>) map.get("records");
	}
	
	public static void main( String[] args ){
		AgencyMessageService service = new AgencyMessageService();
		service.getAddress( "dab555c8-67e0-e234" );
	}
}
