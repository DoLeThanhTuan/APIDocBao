package com.dolethanhtuan.response;

public class ResponseError extends ResponseSuccess{

	public ResponseError(int status, String message) {
		super(status, message);
	}
	
}
