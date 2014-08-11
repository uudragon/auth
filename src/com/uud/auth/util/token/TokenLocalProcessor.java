package com.uud.auth.util.token;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.uud.auth.util.ConfigHelper;

public class TokenLocalProcessor extends AbstractTokenProcessor{
	
	private static Map<String,Token> tokens= new ConcurrentHashMap<String,Token>();
	
	public Token getToken( String token ){
		if( tokens.containsKey( token ) ){
			Token t = tokens.get( token );
			Long timeout = ConfigHelper.getInstance().getLong("token.timeout");
			Long now = System.currentTimeMillis();
			
			if( now - t.getPrevious() > timeout ){
				tokens.remove( token );
				return null;
			} else {
				t.setPrevious( now );
				return t;
			}
		} else {
			return null;
		}
	}
	
	public void remove( String token ){
		tokens.remove( token );
	}
	
	public String updateToken( String token, String domain ){
		if( tokens.containsKey( token ) ){
			Token t = tokens.get( token );
			Long userId = t.getUserId();
			return buildToken( userId, domain );
		}
		return null;
	}

	@Override
	public void putToken(String token, Token t) {
		tokens.put( token, t );
	}
}
