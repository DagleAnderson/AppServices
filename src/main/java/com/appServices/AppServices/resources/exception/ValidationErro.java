package com.appServices.AppServices.resources.exception;

import java.util.ArrayList;
import java.util.List;

public class ValidationErro extends StandardError {
	private static final long serialVersionUID = 1L;
	
	private List<FieldMessage> errors= new ArrayList<>();
	
	public ValidationErro(Long timestamp, Integer status, String error, String messager, String path) {
		super(timestamp, status, error, messager, path);
	}

	public List<FieldMessage> getErrors() {
		return errors;
	}

	public void addErro(String fieldName, String message) {
		
		errors.add(new FieldMessage(fieldName,message));
	}
	
	
	
	

}
