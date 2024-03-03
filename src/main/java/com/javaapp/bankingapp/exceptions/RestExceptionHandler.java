package com.javaapp.bankingapp.exceptions;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@ControllerAdvice
public class RestExceptionHandler {

	@ExceptionHandler(value = NoAccountFoundException.class)
	public ResponseEntity<ApiError> handleNoAccountFoundException(NoAccountFoundException ex) {
		
		ApiError error = new ApiError(404, "No Account Found", new Date(), ex.getLocalizedMessage());
		
		return new ResponseEntity<ApiError>(error ,HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(value = InsufficientAmountException.class)
	public ResponseEntity<ApiError> handleInsufficientAmountException(InsufficientAmountException ex) {
		
		ApiError error = new ApiError(400, "Insufficient Fund In Account", new Date(), ex.getMessage());
		
		return new ResponseEntity<ApiError>(error ,HttpStatus.BAD_REQUEST);
	}
}
