package com.javaapp.bankingapp.exceptions;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;

@RestController
@ControllerAdvice
public class ValidationExceptionHandler {

	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	@ExceptionHandler(ConstraintViolationException.class)
	  ResponseEntity<ViolationReport> handleConstraintViolationException(ConstraintViolationException e) {
		ViolationReport violationReport;
		Map<String, String> errorMap = new HashMap<>();
		for (ConstraintViolation<?> cv : e.getConstraintViolations()) {
	
		         errorMap.put(cv.getPropertyPath().toString(),cv.getMessage());
		    }
		
		
	    return new ResponseEntity<>(new ViolationReport(400, "User data is not valid", new Date(), errorMap ), HttpStatus.BAD_REQUEST);
	  }
	
	@ResponseStatus(code = HttpStatus.CONFLICT)
	@ExceptionHandler(UserAlreadyExistException.class)
	ResponseEntity<ViolationReport> handleUserAlredyExistException(UserAlreadyExistException ex)
	{
		ViolationReport violationReport = new ViolationReport(409, ex.getMessage(), new Date(), new HashMap<>());
		return new ResponseEntity<>(violationReport,HttpStatus.CONFLICT);
	}
	
	@ResponseStatus(code = HttpStatus.NOT_FOUND)
	@ExceptionHandler(UserNotFoundException.class)
	ResponseEntity<ViolationReport> handleUserNotFoundException(UserNotFoundException ex)
	{
		return new ResponseEntity<>( new ViolationReport(404, ex.getMessage(), new Date(), new HashMap<>()),
				HttpStatus.NOT_FOUND); 
	}
	
}
