package com.wipro.batch19.common;

public class NotFoundException extends Throwable {
	
	String errorMsg;
	
	public String getErrorMsg() {
		return errorMsg;
	}

	public NotFoundException(String msg) {
		
		this.errorMsg = msg;
		
	}

}
