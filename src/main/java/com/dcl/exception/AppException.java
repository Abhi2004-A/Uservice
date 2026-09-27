package com.dcl.exception;

import org.springframework.http.HttpStatus;

import com.dcl.dto.UserDto;

public class AppException extends RuntimeException{
	
	private HttpStatus httpStatus;
	
	public HttpStatus getHttpStatus() {
		return httpStatus;
	}
	
	public AppException(String message,HttpStatus httpStatus) {
		super(message);
		this.httpStatus=httpStatus;
	}

}
