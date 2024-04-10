package com.dolethanhtuan.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ResponseSuccess<T> {
	private final int status;
	private final String message;
	@JsonInclude(value = Include.NON_NULL)
	private T data;
	public ResponseSuccess(int status, String message) {
		this.status = status;
		this.message = message;
	}
	
	
	
}
