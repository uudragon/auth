package com.uud.auth.util;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import com.uud.auth.servlet.ServiceBeanContext;
import com.uud.auth.ws.client.OrderSplitService;

public class PackagesSchedule {
	
	private static PackagesSchedule ps;
	
	private static ScheduledExecutorService service = Executors.newSingleThreadScheduledExecutor();
	
	private Map<String,Map<String,Object>> map = new HashMap<String,Map<String,Object>>();
	
	private PackagesSchedule(){
		
	}
	
	public static Map<String,Object> getPackage( String packageCode ){
		return ps.findPackage( packageCode );
	}
	
	public static void init(){
		if( ps == null ){
			synchronized ( PackagesSchedule.class ) {
				if( ps == null ){
					ps = new PackagesSchedule();
				}
			}
		}
		service.scheduleAtFixedRate( new Runnable() {
			public void run() {
				try{
					ps.getPackages();
				} catch ( Exception e ){
					e.printStackTrace();
				}
			}
		}, 0,15, TimeUnit.SECONDS );
	}
	
	private void getPackages(){
		OrderSplitService orderSplit = ServiceBeanContext.getInstance().getBean("orderSplit");
		List<Map<String,Object>> list = orderSplit.getPackages();
		if( list != null && list.size() > 0 ){
			for( Map<String,Object> p : list ){
				String packageCode = (String) p.get( "package_code" );
				map.put( packageCode, p );
			}
		}
	}
	
	public Map<String,Object> findPackage( String packageCode ){
		return map.get( packageCode );
	}
	
	public static void main( String[] args ){
		PackagesSchedule.init();
	}
}
