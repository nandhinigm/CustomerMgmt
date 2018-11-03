package com.nab.customer.customermgmt.common;

public class RecordNotFoundException extends Exception {
	
	private static final long serialVersionUID = 1L;
	private String errorMessage;
	 
	public String getErrorMessage() {
		return errorMessage;
	}
	public RecordNotFoundException(String errorMessage) {
		super(errorMessage);
		this.errorMessage = errorMessage;
	}
	public RecordNotFoundException() {
		super();
	}
}
