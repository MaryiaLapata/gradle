package com.epam.cdp.userManagement.exception;

import com.epam.cdp.userManagement.model.Address;

public class NoSuchModelException extends Exception {

	private static final long serialVersionUID = 1L;
	private String message;
	
	public NoSuchModelException(Class<? extends Object> class1, String id){
		this.message = "There is no the " + class1.getSimpleName() + " entity with id=" + id;
	}
	
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
