package com.uud.auth.util.token;

import redis.clients.jedis.Jedis;

import com.alibaba.fastjson.JSON;
import com.uud.auth.util.RedisPool;

public class TokenRedisProcessor extends AbstractTokenProcessor{

	@Override
	public Token getToken(String token) {
		Jedis jedis = null;
		try{
			jedis = RedisPool.getInstance().getPool().getResource();
			String json = jedis.get( token );
			return JSON.parseObject( json, Token.class );
		} finally {
			RedisPool.getInstance().getPool().returnResource( jedis );
		}
	}

	@Override
	public void remove(String token) {
		Jedis jedis = null;
		try{
			jedis = RedisPool.getInstance().getPool().getResource();
			jedis.del( token );
		} finally {
			RedisPool.getInstance().getPool().returnResource( jedis );
		}
	}

	@Override
	public String updateToken(String token, String domain) {
		Jedis jedis = null;
		try{
			jedis = RedisPool.getInstance().getPool().getResource();
			if( jedis.exists( token ) ){
				String json = jedis.get( token );
				Token t = JSON.parseObject( json, Token.class );
				Long userId = t.getUserId();
				return buildToken( userId, domain );
			}
			return null;
		} finally {
			RedisPool.getInstance().getPool().returnResource( jedis );
		}
	}

	@Override
	public void putToken(String token, Token t) {
		Jedis jedis = null;
		try{
			jedis = RedisPool.getInstance().getPool().getResource();
			jedis.set( token, JSON.toJSONString( t ) );
		} finally {
			RedisPool.getInstance().getPool().returnResource( jedis );
		}
	}

}
