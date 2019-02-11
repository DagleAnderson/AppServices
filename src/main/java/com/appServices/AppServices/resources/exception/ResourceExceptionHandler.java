package com.appServices.AppServices.resources.exception;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.appServices.AppServices.Service.exception.AuthorizationException;
import com.appServices.AppServices.Service.exception.DataIntegrityException;
import com.appServices.AppServices.Service.exception.ObjectNotFoundException;

@ControllerAdvice
public class ResourceExceptionHandler {

	@ExceptionHandler(ObjectNotFoundException.class)
	public ResponseEntity<StandardError>objectNotFound(ObjectNotFoundException e, HttpServletRequest request){
		
		StandardError error = new StandardError(HttpStatus.NOT_FOUND.value(),e.getMessage(), System.currentTimeMillis());
	
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
	}

	@ExceptionHandler(DataIntegrityException.class)
	public ResponseEntity<StandardError>dateIntegrity(DataIntegrityException e, HttpServletRequest request){
		
		StandardError error = new StandardError(HttpStatus.BAD_REQUEST.value(),e.getMessage(), System.currentTimeMillis());
	
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
	}
	
	

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<StandardError>validation(MethodArgumentNotValidException e, HttpServletRequest request){
		
		ValidationErro error = new ValidationErro(HttpStatus.BAD_REQUEST.value(),"Erro de validação", System.currentTimeMillis());
	
		for(FieldError x : e.getBindingResult().getFieldErrors()) {
			error.addErro(x.getField(), x.getDefaultMessage());
		}
		
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
	}
	
	@ExceptionHandler(AuthorizationException.class)
	public ResponseEntity<StandardError>authorization(AuthorizationException e, HttpServletRequest request){
		
		StandardError error = new StandardError(HttpStatus.FORBIDDEN.value(),e.getMessage(), System.currentTimeMillis());
	
		return ResponseEntity.status(HttpStatus.FORBIDDEN).body(error);
	}

	
}
