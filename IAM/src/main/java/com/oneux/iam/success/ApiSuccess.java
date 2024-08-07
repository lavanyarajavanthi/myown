package com.oneux.iam.success;

import org.springframework.http.HttpStatus;

public class ApiSuccess {
	private HttpStatus status;
	private String message;
	private String code;

	public ApiSuccess() {
		super();
	}

	public ApiSuccess(final HttpStatus status, final String message, final String code) {
		super();
		this.status = status;
		this.message = message;
		this.code = code;

	}

	public HttpStatus getStatus() {
		return status;
	}

	public void setStatus(final HttpStatus status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(final String message) {
		this.message = message;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public ApiSuccess(final HttpStatus status, final String message) {
		super();
		this.status = status;
		this.message = message;

	}

}
