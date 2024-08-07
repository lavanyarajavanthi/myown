package com.oneux.iam.exceptions;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.ServletWebRequest;

@ControllerAdvice
public class CustomErrorHandler {

	@Autowired
	MessageSource messageSource;

	@Autowired
	LocaleResolver localeResolver;

	@ExceptionHandler(IAMException.class)
	public ResponseEntity<Object> hanldeCDBExpcetion(IAMException ex, ServletWebRequest request) {

		
		String msg = messageSource.getMessage(ex.getCode(),ex.getArgs(),localeResolver.resolveLocaleWebReq(request));

		// pass ex.getErrors() in place of new ArrayList<>() for CBS errors
		ApiError apiError = ApiError.builder().code(ex.getCode()).message(msg).errors(ex.getErrors()).build();
//		ApiError apiError = ApiError.builder().code(ex.getCode()).message(msg).errors(new ArrayList<>()).build();
		if (StringUtils.isEmpty(apiError.getMessage())) {
			apiError.setMessage(messageSource.getMessage(ex.getCode(), ex.getArgs(), request.getLocale()));
		}

		return new ResponseEntity<>(apiError, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(Exception.class)
	public void hanldeExpcetion(Exception exception, ServletWebRequest webRequest) throws IOException {
		exception.printStackTrace();
		webRequest.getResponse().sendError(HttpStatus.INTERNAL_SERVER_ERROR.value(), exception.getMessage());
	}
}