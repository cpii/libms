package com.hurontg.common.exception;


public class AppException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 * @param s
	 */
	public AppException(String s) {
		super(s);
	}
	
	public AppException(String s, Throwable root) {
		super(s, root);
	}
	
	public AppException(Throwable root) {
		super(root);
	}

}
