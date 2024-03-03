package com.javaapp.bankingapp.exceptions;

public class InsufficientAmountException extends RuntimeException{

	public InsufficientAmountException(String msg) {
		super(msg);
		
	}
}
