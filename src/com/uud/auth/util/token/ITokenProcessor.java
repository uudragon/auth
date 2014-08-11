package com.uud.auth.util.token;

public interface ITokenProcessor {
	public Token getToken( String token );
	public void remove( String token );
	public String buildToken( Long userId, String domain );
	public String updateToken( String token, String domain );
}
