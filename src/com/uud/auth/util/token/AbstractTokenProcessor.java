package com.uud.auth.util.token;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public abstract class AbstractTokenProcessor implements ITokenProcessor {

	public String buildToken( Long userId, String domain ){
		try {
			Long current = System.currentTimeMillis();
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update( ( userId.toString() + domain + current.toString() ).getBytes() );
			String token = toHex( md.digest() );
			Token t = new Token( current, userId, token, domain );
			putToken( token, t );
			return token;
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	private String toHex(byte buffer[]) {
		StringBuffer sb = new StringBuffer(buffer.length * 2);
		for (int i = 0; i < buffer.length; i++) {
			sb.append(Character.forDigit((buffer[i] & 240) >> 4, 16));
			sb.append(Character.forDigit(buffer[i] & 15, 16));
		}

		return sb.toString();
	}
	
	public abstract void putToken( String token, Token t );
}
