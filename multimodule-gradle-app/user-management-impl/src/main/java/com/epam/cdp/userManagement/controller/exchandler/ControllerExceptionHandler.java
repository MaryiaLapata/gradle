package com.epam.cdp.userManagement.controller.exchandler;

import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.epam.cdp.userManagement.exception.NoSuchModelException;
import com.epam.cdp.userManagement.exception.model.ResponseErrorMsg;

@RestControllerAdvice
public class ControllerExceptionHandler {

	@ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
	public ResponseErrorMsg processValidationError(MethodArgumentNotValidException ex) {
		ResponseErrorMsg response =  new ResponseErrorMsg(HttpStatus.BAD_REQUEST.toString());
		BindingResult result = ex.getBindingResult();
		
		for (FieldError error : result.getFieldErrors()) {
			response.addMsgToList(error.getField() + " " + error.getDefaultMessage());
		}
		
		return response;
	}
	
	@ExceptionHandler(NoSuchModelException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
	public ResponseErrorMsg processNoSuchModelException(NoSuchModelException ex) {
		ResponseErrorMsg response =  new ResponseErrorMsg(HttpStatus.NOT_FOUND.toString());
		
		response.addMsgToList(ex.getMessage());
		
		return response;
	}
	
	@ExceptionHandler({HttpMessageNotReadableException.class, ServletRequestBindingException.class})
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ResponseErrorMsg processHttpMessageNotReadableException(Exception ex){
		ResponseErrorMsg response =  new ResponseErrorMsg(HttpStatus.BAD_REQUEST.toString());
		response.addMsgToList(ex.getMessage());
		return response;
	}
	
	@ExceptionHandler(MissingServletRequestParameterException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ResponseErrorMsg processHttpMessageNotReadableException(MissingServletRequestParameterException ex){
		ResponseErrorMsg response =  new ResponseErrorMsg(HttpStatus.BAD_REQUEST.toString());
		response.addMsgToList(ex.getMessage());
		return response;
	}
	
	@ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public ResponseErrorMsg processNoSuchModelException(Exception ex) {
		ResponseErrorMsg response =  new ResponseErrorMsg(HttpStatus.INTERNAL_SERVER_ERROR.toString());
		
		response.addMsgToList(ex.getMessage());
		
		return response;
	}
}
