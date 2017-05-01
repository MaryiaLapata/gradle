package com.epam.cdp.userManagement.exception;

public class NoSuchModelException extends Exception {

	private static final long serialVersionUID = 1L;
	private String message;
	
	public NoSuchModelException(){}
	
	public NoSuchModelException(Class<? extends Object> modelType, long modelId){
		this.message = "There is no the " + modelType.getSimpleName() + " entity with id=" + modelId;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
