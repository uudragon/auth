package com.uud.auth.ws.client;

import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

import org.glassfish.jersey.client.ClientConfig;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.springframework.stereotype.Service;

import com.uud.auth.util.ConfigHelper;
import com.uud.cs.entity.Customer;
import com.uud.cs.entity.Order;
import com.uud.cs.service.IOrderService;

@Service("orderSplit")
public class OrderSplitService {
	
	private static WebTarget wt;
	
	public OrderSplitService(){
		ClientConfig config = new ClientConfig();
		config.register( MyJacksonJsonProvider.class );
		Client client = ClientBuilder.newClient( config ); 
		wt = client.target( ConfigHelper.getInstance().getString( "wms.restURL" ) );
	}
	
	public String getSplitDetails( Order order ){
		Map<String,Object> req = new HashMap<String,Object>();
		Customer customer = order.getCustomer();
		req.put("orders_no", order.getOrder_no() );
		req.put("customer_code", customer.getCode() );
		req.put("customer_name", customer.getName() );
/*		Date date = ;
		SimpleDateFormat df = new SimpleDateFormat("");*/
		req.put("effective_date", order.getEffective() );
		String address = customer.getProvince() + customer.getCity() + customer.getAddress();
		req.put("address", address );
		req.put("customer_tel", customer.getPhone() );
		req.put("amount", order.getAmount() );
		req.put("has_invoice", order.getHas_invoice()?1:0 );
		req.put("creator", order.getCreator() );
		req.put("updater", order.getUpdater() );
		req.put("package_code", order.getOrder_type() );
		req.put("status", 5);
		/*List<OrdersDetail> details = order.getDetails();
		List<Map<String,Object>> ods = new ArrayList<Map<String,Object>>();
		for( OrdersDetail od : details ){
			Map<String,Object> map = new HashMap<String,Object>();
			ods.add( map );
		}
		req.put("details", ods );*/
		
		/*ObjectMapper mapper = new ObjectMapper();
		try {
			System.out.println( mapper.writeValueAsString( req ) );
		} catch (JsonGenerationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		return wt.path("outbound/split/")
				.request( MediaType.APPLICATION_JSON )
				.post( Entity.entity( req, MediaType.APPLICATION_JSON ), String.class );
	}
	
	public static void main( String args[] ){
		/*Date d = new Date();
		SimpleDateFormat df = new SimpleDateFormat();

		System.out.print( );*/
		ApplicationContext ctx=new FileSystemXmlApplicationContext("classpath:applicationContext.xml");
		IOrderService oservice = (IOrderService) ctx.getBean( "orderService" );
		OrderSplitService service = new OrderSplitService();
		System.out.print( service.getSplitDetails( oservice.findById( 1l ) ) );
	}
}
