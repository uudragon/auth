package com.uud.auth.ws.client;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.jaxrs.JacksonJsonProvider;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;
import org.glassfish.jersey.client.ClientConfig;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.uud.auth.util.ConfigHelper;

@Service("productService")
public class ProductService {
	
	private static WebTarget wt;
	
	public ProductService(){
		ClientConfig config = new ClientConfig();
		config.register( JacksonJsonProvider.class );
		Client client = ClientBuilder.newClient( config ); 
		wt = client.target( ConfigHelper.getInstance().getString( "wms.restURL" ) );
	}
	
	@SuppressWarnings("unchecked")
	public List<Map<String,Object>> getProductByOrderType( String orderType ) throws JsonParseException, JsonMappingException, IOException{
		List<Map<String,Object>> ods = new ArrayList<Map<String,Object>>();
		String json = wt.path("baseinfo").path("package").path( orderType )
										.request( MediaType.APPLICATION_JSON ).get( String.class );
		ObjectMapper mapper = new ObjectMapper();
		Map<String,Object> packages = mapper.readValue( json,
									new TypeReference<Map<String,Object>>(){});
		List<Map<String,Object>> list = (List<Map<String, Object>>) packages.get("details");
		for( Map<String,Object> map : list ){
			Map<String,Object> od = new HashMap<String,Object>();
			od.put("product_no", map.get("product_code"));
			od.put("product_name", map.get("product_name"));
			od.put("qty", map.get("qty"));
			ods.add( od );
		}
		return ods;	
	}
	
	public static void main( String[] args ){
		ProductService service = new ProductService();
		try {
			List<Map<String, Object>> list = service.getProductByOrderType( "25bd7cdb-c883-ae4c" );
			System.out.println( JSON.toJSONString( list ) );
		} catch (JsonParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
