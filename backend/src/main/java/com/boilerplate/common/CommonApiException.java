package com.boilerplate.common;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public class CommonApiException extends RuntimeException {

	private HttpStatus httpStatus;
	private int code;
	private String message;
	private Object data;

	public CommonApiException(ErrorCode errorCode) {
		this.httpStatus = errorCode.getStatus();
		this.message = errorCode.getMessage();
		this.data = null;
	}
}
