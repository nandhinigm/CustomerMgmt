package com.nab.customer.customermgmt.controller;

import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.nab.customer.customermgmt.common.Messages;
import com.nab.customer.customermgmt.common.ErrorResponse;

@RestControllerAdvice
public class ExceptionControllerAdvice {

	private static final Logger logger = Logger.getLogger(ExceptionControllerAdvice.class);
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorResponse> exceptionHandler(Exception ex) {
		ErrorResponse error = new ErrorResponse();
		error.setErrorCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
		error.setMessage(Messages.GENERIC_ERROR_MESSAGE);		
		logger.error(new String("Exception occurred during request processing " + ex.getStackTrace().toString()));
		ex.printStackTrace();
		return new ResponseEntity<ErrorResponse>(error, HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
