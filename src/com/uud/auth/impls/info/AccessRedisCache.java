package com.uud.auth.impls.info;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.util.StringUtils;

import redis.clients.jedis.Jedis;

import com.alibaba.fastjson.JSON;
import com.uud.auth.entity.Resource;
import com.uud.auth.entity.User;
import com.uud.auth.util.RedisPool;

public class AccessRedisCache extends AbstractAccessCache{
	
	@Override
	public String getPassword(String domain, String account) {
		Jedis jedis = null;
		try{
			jedis = RedisPool.getInstance().getPool().getResource();
			String json = jedis.hget( domain + "_account", account );
			return JSON.parseObject( json, User.class ).getPassword();
		} finally {
			RedisPool.getInstance().getPool().returnResource( jedis );
		}
	}

	@Override
	public User getUser(String domain, String account) {
		Jedis jedis = null;
		try{
			jedis = RedisPool.getInstance().getPool().getResource();
			String json = jedis.hget( domain + "_account", account );
			return JSON.parseObject( json, User.class );
		} finally {
			RedisPool.getInstance().getPool().returnResource( jedis );
		}
	}

	@Override
	public User getUser(String domain, Long userId) {
		Jedis jedis = null;
		try{
			jedis = RedisPool.getInstance().getPool().getResource();
			String json = jedis.hget( domain + "_userid", userId.toString() );
			return JSON.parseObject( json, User.class );
		} finally {
			RedisPool.getInstance().getPool().returnResource( jedis );
		}
	}

	@Override
	public Long[] getRoles(Long userId, String domain) {
		Jedis jedis = null;
		try{
			jedis = RedisPool.getInstance().getPool().getResource();
			String str = jedis.hget( domain + "_ur", userId.toString() );
			String[] ids = StringUtils.commaDelimitedListToStringArray( str );
			Long[] roleIds = new Long[ ids.length ];
			for( int i = 0 ; i < ids.length ; i ++ ){
				roleIds[i] = Long.parseLong( ids[i] );
			}
			return roleIds;
		} finally {
			RedisPool.getInstance().getPool().returnResource( jedis );
		}
	}

	@Override
	public List<Long> getRole(String url, String method, String domain) {
		Jedis jedis = null;
		List<Long> roleIds = new ArrayList<Long>();
		try{
			Resource resource = null; 
			jedis = RedisPool.getInstance().getPool().getResource();
			Map<String, String> map = jedis.hgetAll( domain + "_resource" );
			Set<String> keySet = map.keySet();
			Iterator<String> iterator = keySet.iterator();
			while( iterator.hasNext() ){
				String json = map.get( iterator.next() );
				Resource r = JSON.parseObject( json, Resource.class );
				if( url.equals( resource.getUrl() ) 
						&& method.equals( resource.getMethod() ) ){
					resource = r;
				}
			}
			if( resource != null ){
				Map<String, String> rrmap = jedis.hgetAll( domain + "_rr" );
				Set<String> rrkeys = rrmap.keySet();
				Iterator<String> rrIterator = rrkeys.iterator();
				while( rrIterator.hasNext() ){
					String roleId = rrIterator.next();
					String resourceIdStr = rrmap.get( roleId );
					String[] resourceIds = resourceIdStr.split(",");
					for( String resourceId : resourceIds ){
						if( resource.getId().equals( Long.parseLong( resourceId ) ) ){
							roleIds.add( Long.parseLong( roleId ) );
						}
					}
				}
			}
			
		}finally{
			RedisPool.getInstance().getPool().returnResource( jedis );
		}
		return roleIds;
	}

	@Override
	public List<Long> getRole(String code, String domain) {
		Jedis jedis = null;
		List<Long> roleIds = new ArrayList<Long>();
		try{
			Resource resource = null; 
			jedis = RedisPool.getInstance().getPool().getResource();
			Map<String, String> map = jedis.hgetAll( domain + "_resource" );
			Set<String> keySet = map.keySet();
			Iterator<String> iterator = keySet.iterator();
			while( iterator.hasNext() ){
				String json = map.get( iterator.next() );
				Resource r = JSON.parseObject( json, Resource.class );
				if( code.equals( r.getCode() ) ){
					resource = r;
				}
			}
			if( resource != null ){
				Map<String, String> rrmap = jedis.hgetAll( domain + "_rr" );
				Set<String> rrkeys = rrmap.keySet();
				Iterator<String> rrIterator = rrkeys.iterator();
				while( rrIterator.hasNext() ){
					String roleId = rrIterator.next();
					String resourceIdStr = rrmap.get( roleId );
					String[] resourceIds = resourceIdStr.split(",");
					for( String resourceId : resourceIds ){
						if( resource.getId().equals( Long.parseLong( resourceId ) ) ){
							roleIds.add( Long.parseLong( roleId ) );
						}
					}
				}
			}
			
		}finally{
			RedisPool.getInstance().getPool().returnResource( jedis );
		}
		return roleIds;
	}

	@Override
	public List<Resource> getResources(String url, String method,
			String domain, Long userId) {
		List<Resource> resources = new ArrayList<Resource>();
		Jedis jedis = null;
		try{
			jedis = RedisPool.getInstance().getPool().getResource();
			Map<String, String> map = jedis.hgetAll( domain + "__resource" );
			Set<String> keySet = map.keySet();
			Iterator<String> iterator = keySet.iterator();
			Long parentId = null;
			if( url == null ){
				parentId = 0l;
			} else {
				while( iterator.hasNext() ){
					String key = iterator.next();
					String json = map.get( key );
					Resource resource = JSON.parseObject( json, Resource.class );
					if( url.equals( resource.getUrl() ) 
							&& method.equals( resource.getMethod() ) ){
						parentId = resource.getId();
					}
				}
			}
			if( parentId != null ){
				String roleIdStr = jedis.hget( domain + "_ur", userId.toString() );
				if( roleIdStr != null ){
					String[] roleIds = roleIdStr.split(",");
					for( String roleId : roleIds ){
						String resourceIdStr = jedis.hget( domain + "_rr", roleId );
						if( resourceIdStr != null ){
							String[] resourceIds = resourceIdStr.split( "," );
							for( String resourceId : resourceIds ){
								if( resourceId.equals( parentId.toString() ) ){
									String rStr = jedis.hget( domain + "_resource", parentId.toString() );
									resources.add( JSON.parseObject( rStr, Resource.class ) );
								}
							}
						}
					}
				}
			}
		} finally {
			RedisPool.getInstance().getPool().returnResource( jedis );
		}
		return resources;
	}

	@Override
	public List<Resource> getResources(String code, String domain, Long userId) {
		List<Resource> resources = new ArrayList<Resource>();
		Jedis jedis = null;
		try{
			jedis = RedisPool.getInstance().getPool().getResource();
			Map<String, String> map = jedis.hgetAll( domain + "_resource" );
			Set<String> keySet = map.keySet();
			Iterator<String> iterator = keySet.iterator();
			Long parentId = null;
			if( code == null ){
				parentId = 0l;
			} else {
				while( iterator.hasNext() ){
					String key = iterator.next();
					String json = map.get( key );
					Resource resource = JSON.parseObject( json, Resource.class );
					if( code.equals( resource.getCode() ) ){
						parentId = resource.getId();
					}
				}
			}
			if( parentId != null ){
				String roleIdStr = jedis.hget( domain + "_ur", userId.toString() );
				if( roleIdStr != null ){
					String[] roleIds = roleIdStr.split(",");
					for( String roleId : roleIds ){
						String resourceIdStr = jedis.hget( domain + "_rr", roleId );
						if( resourceIdStr != null ){
							String[] resourceIds = resourceIdStr.split( "," );
							for( String resourceId : resourceIds ){
								String rStr = jedis.hget( domain + "_resource", resourceId );
								Resource r = JSON.parseObject( rStr, Resource.class );
								if( r.getParent().equals( parentId ) ){
									resources.add( r );
								}
							}
						}
					}
				}
			}
		} finally {
			RedisPool.getInstance().getPool().returnResource( jedis );
		}
		return resources;
	}

	@Override
	public void clear() {
		Jedis jedis = null;
		try{
			jedis = RedisPool.getInstance().getPool().getResource();
			jedis.flushAll();
		} finally {
			RedisPool.getInstance().getPool().returnResource( jedis );
		}
	}

	@Override
	public void putAuthMessage(String domain, AuthMessage auth) {
		Jedis jedis = null;
		try{
			jedis = RedisPool.getInstance().getPool().getResource();
			if( auth.getUser_info() != null ){
				putUser( auth.getUser_info(), domain, jedis ) ;
			}
			if( auth.getResources() != null ){
				putResource( auth.getResources(), domain, jedis );
			}
			if( auth.getRole_resource() != null ){
				putRR( auth.getRole_resource(), domain, jedis );
			}
			if( auth.getUser_role() != null ){
				putUR( auth.getUser_role(), domain, jedis );
			}
		} finally {
			RedisPool.getInstance().getPool().returnResource( jedis );
		}
	}
	
	private void putUR( Map<Long, Long[]> map, String domain, Jedis jedis ){
		Set<Long> keySet = map.keySet();
		Iterator<Long> iterator = keySet.iterator();
		while( iterator.hasNext() ){
			Long userId = iterator.next();
			Long[] rids = map.get( userId );
			String ridStr = "";
			for( Long rid : rids ){
				ridStr = ridStr + "," + rid;
			}
			if( ridStr.length() > 0 ){
				ridStr = ridStr.substring( 1 );
			}
			jedis.hset( domain + "_ur", userId.toString(), ridStr );
		}
	}
	
	private void putRR( Map<Long, Long[]> map, String domain, Jedis jedis ){
		Set<Long> keySet = map.keySet();
		Iterator<Long> iterator = keySet.iterator();
		while( iterator.hasNext() ){
			Long roleId = iterator.next();
			Long[] rids = map.get( roleId );
			String ridStr = StringUtils.arrayToCommaDelimitedString( rids );
			jedis.hset( domain + "_rr", roleId.toString(), ridStr );
		}
	}
	
	private void putResource( Map<Long, Resource> map, String domain, Jedis jedis ){
		Set<Long> keySet = map.keySet();
		Iterator<Long> iterator = keySet.iterator();
		while( iterator.hasNext() ){
			Long key = iterator.next();
			Resource resource = map.get( key );
			jedis.hset( domain+"_resource", resource.getId().toString(), JSON.toJSONString( resource ) );
		}
	}
	
	private void putUser( Map<Long,User> map, String domain, Jedis jedis ){
		Set<Long> keySet = map.keySet();
		Iterator<Long> iterator = keySet.iterator();
		while( iterator.hasNext() ){
			Long key = iterator.next();
			User user = map.get(key);
			jedis.hset( domain+"_userid", user.getId().toString(), JSON.toJSONString( user ) );
			jedis.hset( domain+"_account", user.getAccount(), JSON.toJSONString( user ) );
		}
	}

	@Override
	public List<Resource> getResources(String domain, Long userId) {
		
		List<Resource> resources = new ArrayList<Resource>();
		Jedis jedis = null;
		try{
			jedis = RedisPool.getInstance().getPool().getResource();
			String roleIdStr = jedis.hget( domain + "_ur", userId.toString() );
			if( roleIdStr != null ){
				String[] roleIds = roleIdStr.split(",");
				String resourceIdStr = "";
				for( String roleId : roleIds ){
					resourceIdStr = resourceIdStr + "," + jedis.hget( domain + "_rr", roleId );
				}
				if( resourceIdStr.length() > 0  ){
					resourceIdStr = resourceIdStr.substring( 1 );
					String resourceIds[] = resourceIdStr.split( "," );
					for( String resourceId : resourceIds ){
						String rStr = jedis.hget( domain + "_resource", resourceId );
						Resource r = JSON.parseObject( rStr, Resource.class );
						resources.add( r );
					}
				}
			}
		} catch ( Exception e ){
			e.printStackTrace();
		} finally {
			RedisPool.getInstance().getPool().returnResource( jedis );
		}
		return resources;
	}

}
