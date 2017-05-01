package com.epam.cdp.userManagement.exception.model;

import java.util.ArrayList;
import java.util.List;

public class ResponseErrorMsg {

	private String status;
	private List<String> errorMessages = new ArrayList<>();
	
	public ResponseErrorMsg(){}
	
	public ResponseErrorMsg(String status) {
		this.status = status;
	}
	
	public ResponseErrorMsg(String status, List<String> messages) {
		this.status = status;
		this.errorMessages = messages;
	}
	
	public void addMsgToList(String message) {
		errorMessages.add(message);
	}

	public List<String> getErrorMessages() {
		return errorMessages;
	}

	public void setErrorMessages(List<String> errorMsgs) {
		this.errorMessages = errorMsgs;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
}
