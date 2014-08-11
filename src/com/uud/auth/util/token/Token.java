package com.uud.auth.util.token;


public class Token{
	
	private Long previous;
	
	private Long userId;
	
	private String token;

	private String domain;
	
	public Token(){
		
	}
	
	public Token(Long previous, Long userId, String token, String domain ) {
		super();
		this.previous = previous;
		this.userId = userId;
		this.token = token;
		this.domain = domain;
	}

	public Long getPrevious() {
		return previous;
	}

	public void setPrevious(Long previous) {
		this.previous = previous;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getDomain() {
		return domain;
	}

	public void setDomain(String domain) {
		this.domain = domain;
	}
	
	
}