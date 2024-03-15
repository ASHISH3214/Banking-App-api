package com.javaapp.bankingapp.service;

import java.util.List;

import com.javaapp.bankingapp.dto.AccountDto;

public interface AccountService {
	
	AccountDto createAccount(AccountDto accountDto);
	
	AccountDto getAccountById(Long id);
	
	AccountDto deposit(Long id, double amount);
	
	AccountDto  withdraw(Long id, double amount);
	
	List<AccountDto> getAllAccounts();
	
	String deletAccount(Long id); 
	
	List<AccountDto> getAccountsByName(String name);
	
}
