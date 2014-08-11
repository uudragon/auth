package com.uud.auth.interfaces.load;

public class LoadFailedException extends Exception{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public LoadFailedException() {
		super();
	}

	public LoadFailedException(String message) {
		super(message);
	}

	public LoadFailedException(String message, Throwable e) {
		super(message, e);
	}
}
