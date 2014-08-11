package com.uud.auth.util;

import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class RedisPool {
	
	private static RedisPool _instance;
	
	private JedisPool pool;
	
	private RedisPool(){
		init();
	}
	
	public static RedisPool getInstance(){
		if( _instance == null ){
			synchronized( RedisPool.class ){
				if( _instance == null ){
					_instance = new RedisPool();
				}
			}
		}
		return _instance;
	}
	
	private void init(){
		JedisPoolConfig config = initPoolConfig();
		String host = ConfigHelper.getInstance().getString( "redis.connection.host" );
		Integer port = ConfigHelper.getInstance().getInteger( "redis.connection.port" );
		setPool(new JedisPool(config, host, port));
	}
	
	private JedisPoolConfig initPoolConfig() {  
		JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();  
		// 控制一个pool最多有多少个状态为idle的jedis实例  
		jedisPoolConfig.setMaxTotal( ConfigHelper.getInstance().getInteger( "redis.connection.total" ) );   
		// 最大能够保持空闲状态的对象数  
		jedisPoolConfig.setMaxIdle( ConfigHelper.getInstance().getInteger( "redis.connection.idle" ) );  
		// 超时时间  
		jedisPoolConfig.setMaxWaitMillis( ConfigHelper.getInstance().getInteger( "redis.connection.timeout" ) );  
		// 在borrow一个jedis实例时，是否提前进行alidate操作；如果为true，则得到的jedis实例均是可用的；  
		jedisPoolConfig.setTestOnBorrow( ConfigHelper.getInstance().getBoolean( "redis.alidate" ) );   
		// 在还会给pool时，是否提前进行validate操作  
		jedisPoolConfig.setTestOnReturn( ConfigHelper.getInstance().getBoolean( "redis.validate" ) );  
		return jedisPoolConfig;  
	}

	public JedisPool getPool() {
		return pool;
	}

	public void setPool(JedisPool pool) {
		this.pool = pool;
	}
}
