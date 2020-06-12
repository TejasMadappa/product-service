package com.tejas.springcloud.exceptions;

public class ProductNotFoundException extends Exception{
	
	private static final long serialVersionUID = 1L;

	public ProductNotFoundException(String message) {
		super();
	}
	
	public ProductNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}
}
