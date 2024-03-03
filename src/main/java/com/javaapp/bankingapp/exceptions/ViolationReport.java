package com.javaapp.bankingapp.exceptions;

import java.util.Date;
import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class ViolationReport {

	private Integer errorCode;
	private String errordesc;
	private Date date;
	private Map<String, String> errorMap;
}
