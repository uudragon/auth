package com.uud.auth.service.impl;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;




import org.apache.commons.beanutils.BeanUtils;

import com.alibaba.fastjson.JSON;
import com.uud.auth.entity.User;

import redis.clients.jedis.Jedis;

public class TestRedis {
	public static void main( String args[] ){
		//Jedis jedis = new Jedis("192.168.1.88",6379);
		User user = new User();
		user.setId( 1l );
		Map<String,String> map = new HashMap<String,String>();
		try {
			BeanUtils.populate( user, map );
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println( JSON.toJSONString( map ) );
		/*System.out.println( jedis.get( "test" ) );*/
	}
}
