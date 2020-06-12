package com.tejas.springcloud.exceptions;

public class ApiRequestException extends RuntimeException{

	private static final long serialVersionUID = 5351353169658348196L;
	
	public ApiRequestException(String message) {
		super();
	}
	
	public ApiRequestException(String message, Throwable cause) {
		super(message, cause);
	}
}
