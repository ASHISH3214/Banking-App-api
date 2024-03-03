package com.javaapp.bankingapp.exceptions;

public class NoAccountFoundException extends RuntimeException{

	public NoAccountFoundException(String msg) {
		super(msg);
		
	}
}
