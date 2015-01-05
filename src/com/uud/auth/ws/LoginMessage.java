package com.uud.auth.ws;

import javax.xml.bind.annotation.XmlRootElement;

import com.uud.auth.entity.User;

@XmlRootElement
public class LoginMessage {
	
	private Boolean legal;
	
	private String token;
	
	private String message;
	
	private User user;

	private String resources;
	
	public Boolean getLegal() {
		return legal;
	}

	public void setLegal(Boolean legal) {
		this.legal = legal;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getResources() {
		return resources;
	}

	public void setResources(String resources) {
		this.resources = resources;
	}
	
}
