package com.uud.auth.impls.info;

import com.uud.auth.interfaces.info.IAccessInfo;
import com.uud.auth.util.ConfigHelper;

public class AccessFactory {
	
	private static final String CACHE_TYPE = "cache.type";
	
	private static final String CACHE_TYPE_LOCAL = "local";
	
	private static final String CACHE_TYPE_REDIS = "redis";
	
	private static IAccessInfo info;
	
	public static IAccessInfo getAccessInfo(){
		if( info == null ){
			synchronized( AccessFactory.class ){
				if( info == null ){
					String cacheType = ConfigHelper.getInstance().getString( CACHE_TYPE );
					if( CACHE_TYPE_LOCAL.equals( cacheType ) ){
						info = new AccessLocalCache();
					} else if( CACHE_TYPE_REDIS.equals( cacheType ) ) {
						info = new AccessRedisCache();
					}
				}
			}
		}
		return info;
	}

}
