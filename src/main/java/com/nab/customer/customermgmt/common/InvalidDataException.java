package com.nab.customer.customermgmt.common;

public class InvalidDataException extends Exception {
	private static final long serialVersionUID = 1L;
	private String errorMessage;
 
	public String getErrorMessage() {
		return errorMessage;
	}
	public InvalidDataException(String errorMessage) {
		super(errorMessage);
		this.errorMessage = errorMessage;
	}
	public InvalidDataException() {
		super();
	}
}
