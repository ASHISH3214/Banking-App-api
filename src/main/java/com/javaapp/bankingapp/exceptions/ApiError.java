package com.javaapp.bankingapp.exceptions;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ApiError {

	private Integer errorCode;
	private String errordesc;
	private Date date;
	private String errosMessage;
}
