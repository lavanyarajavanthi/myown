/**
 * 
 */
package com.oneux.iam.exceptions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Component;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 
 *
 */
@Data

public class IAMException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8086998660062867513L;

	private final String code;
	private String errorType;
	private String message;

	public IAMException(String code, String errorType) {
		this.code = code;
		this.errorType = errorType;

	}

	public IAMException(String code, String errorType, String message) {
		this.code = code;
		this.errorType = errorType;
		this.message = message;
	}

}
