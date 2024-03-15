package com.javaapp.bankingapp.serviceImpl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaapp.bankingapp.dto.AccountDto;
import com.javaapp.bankingapp.entity.Account;
import com.javaapp.bankingapp.exceptions.InsufficientAmountException;
import com.javaapp.bankingapp.exceptions.NoAccountFoundException;
import com.javaapp.bankingapp.mapper.AccountMapper;
import com.javaapp.bankingapp.repository.AccountRepository;
import com.javaapp.bankingapp.service.AccountService;

import jakarta.persistence.EntityManagerFactory;

@Service
public class AccountServiceImpl implements AccountService{

	@Autowired
	private AccountRepository accountRepository;
	
	@Autowired
	private EntityManagerFactory entityManagerFactory;
	public AccountServiceImpl(AccountRepository accountRepository) {
		super();
		this.accountRepository = accountRepository;
	}


	@Override
	public AccountDto createAccount(AccountDto accountDto) {
		Account account = AccountMapper.mapToAccount(accountDto); 
		Account savedAccount = accountRepository.save(account);		
		return AccountMapper.mapToAccountDto(savedAccount);
	}


	@Override
	public AccountDto getAccountById(Long id) {
		
	Account account = accountRepository
			.findById(id)
			.orElseThrow(() -> new NoAccountFoundException("Account does not exist with id "+id));
	return AccountMapper.mapToAccountDto(account);
	
	}

	@Override
	public AccountDto deposit(Long id, double amount) {
		Account account = accountRepository
				.findById(id)
				.orElseThrow(() -> new RuntimeException("Account does not exist with id "+id));
		
		double total = account.getBalance() + amount;
		account.setBalance(total);
		Account savedAccount = accountRepository.save(account);
		return AccountMapper.mapToAccountDto(savedAccount);
	}


	@Override
	public AccountDto withdraw(Long id, double amount) {
		
		Account account = accountRepository
				.findById(id)
				.orElseThrow(() -> new NoAccountFoundException("Account does not exist with id "+id));
		
		if(account.getBalance()< amount)
			throw new InsufficientAmountException("Low balance ***");
		
		double rest = account.getBalance() - amount;
		account.setBalance(rest);
		Account savedAccount = accountRepository.save(account);
		
		return AccountMapper.mapToAccountDto(savedAccount);
		
		
	}


	@Override
	public List<AccountDto> getAllAccounts() {
		List<Account> accounts = accountRepository.findAll();
		
		if(accounts.isEmpty())
			throw new NoAccountFoundException("No Account exist in DB");
		
		
		return accounts.stream()
		.map((account) -> AccountMapper.mapToAccountDto(account))
		.collect(Collectors.toList());
	}


	@Override
	public String deletAccount(Long id) {
		Account account = accountRepository
				.findById(id)
				.orElseThrow(() -> new NoAccountFoundException("Account does not exist with id "+id));
		accountRepository.deleteById(id);
		
		return "Success";
		
	}


	@Override
	public List<AccountDto> getAccountsByName(String name) {
		
		List<Account> accounts = accountRepository.findByaccountHolderName(name);
		if(accounts.isEmpty())
			throw new NoAccountFoundException("No Account with name "+ name +" exist");
		
		return accounts.stream()
				.map((account) -> AccountMapper.mapToAccountDto(account))
				.collect(Collectors.toList());
	}
	
	
}
