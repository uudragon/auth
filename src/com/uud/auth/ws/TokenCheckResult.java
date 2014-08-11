package com.uud.auth.ws;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class TokenCheckResult {
	
	private Boolean result;
	
	private String message;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Boolean getResult() {
		return result;
	}

	public void setResult(Boolean result) {
		this.result = result;
	}
	
	
}
