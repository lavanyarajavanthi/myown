package com.oneux.iam.exceptions;

import java.util.List;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ApiError {

	
	private String code;
	private String message;
	private List<String> errors;

	public ApiError(String code) {
		this.code = code;
	}

	public ApiError(String code, String message) {
		this.code = code;
		this.message = message;
	}

	public ApiError(String code, String message, List<String> errors) {
		this.code = code;
		this.message = message;
		this.errors = errors;
	}

}