package com.uud.auth.util.token;

import com.uud.auth.util.ConfigHelper;

public class TokenProcessorFactory {
	
	private static ITokenProcessor processor;
	
	public static ITokenProcessor getProcessor(){
		if( processor == null ){
			synchronized( TokenProcessorFactory.class ){
				if( processor == null ){
					if( ConfigHelper.getInstance().getString( "cache.type" ).equals( "local" ) ){
						processor = new TokenLocalProcessor();
					} else if( ConfigHelper.getInstance().getString( "cache.type" ).equals( "redis" ) ){
						processor = new TokenRedisProcessor();
					}
				}
			}
		}
		return processor;
	}
}
