package com.javaapp.bankingapp.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.instancio.Instancio;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Answers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.javaapp.bankingapp.dto.AccountDto;
import com.javaapp.bankingapp.entity.Account;
import com.javaapp.bankingapp.mapper.AccountMapper;
import com.javaapp.bankingapp.repository.AccountRepository;
import com.javaapp.bankingapp.serviceImpl.AccountServiceImpl;

@ExtendWith(MockitoExtension.class)
class AccountServiceTest {

	@Mock
	private AccountRepository accountRepository;
	private AccountServiceImpl accountService;
	Account account;
	AccountDto accountDto;
	
	
	
	
	@BeforeEach
	void setUp() throws Exception {
		accountService = new AccountServiceImpl(accountRepository);
		account = Instancio.of(Account.class).create();
		accountDto = new AccountDto(account.getId(), account.getAccountHolderName(), account.getBalance());
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void testCreateAccount() {	
		when(accountRepository.save(account)).thenReturn(account);
		assertThat(accountService.createAccount(accountDto)).isEqualTo(accountDto);
		
	}

	@Test
	void testGetAccountById() {
		when(accountRepository.findById(account.getId())).thenReturn(Optional.ofNullable(account));
		
		assertThat(accountService.getAccountById(account.getId())).isEqualTo(accountDto);
	}

	@Test
	void testDeposit() {
		when(accountRepository.save(account)).thenReturn(account);
		when(accountRepository.findById(account.getId())).thenReturn(Optional.ofNullable(account));
		
		assertThat(accountService.deposit(account.getId(), 0)).isEqualTo(accountDto);
		
	}

	@Test
	void testWithdraw() {
		when(accountRepository.save(account)).thenReturn(account);
		when(accountRepository.findById(account.getId())).thenReturn(Optional.ofNullable(account));
		
		assertThat(accountService.withdraw(account.getId(), 0)).isEqualTo(accountDto);
		
	}

	@Test
	void testGetAllAccounts() {
		List<Account> accounts = Instancio.ofList(Account.class).size(2).create();
		List<AccountDto> dtos = accounts.stream()
				.map((account) -> AccountMapper.mapToAccountDto(account))
				.collect(Collectors.toList());
		
		when(accountRepository.findAll()).thenReturn(accounts);
		assertThat(accountService.getAllAccounts()).isEqualTo(dtos);
	}

	@Test
	void testDeletAccount() {
		mock(AccountRepository.class, Mockito.CALLS_REAL_METHODS);
		when(accountRepository.findById(account.getId())).thenReturn(Optional.ofNullable(account));
		doAnswer(Answers.CALLS_REAL_METHODS)
		.when(accountRepository)
		.deleteById(any());
		
		assertThat(accountService.deletAccount(account.getId())).isEqualTo("Success");
		
		
	}

	@Test
	void testGetAccountsByName() {
		List<Account> accounts = Instancio.ofList(Account.class).size(2).create();
		List<AccountDto> dtos = accounts.stream()
				.map((account) -> AccountMapper.mapToAccountDto(account))
				.collect(Collectors.toList());
		
		when(accountRepository.findByaccountHolderName(accounts.get(0).getAccountHolderName())).thenReturn(accounts);
		assertThat(accountService.getAccountsByName(accounts.get(0).getAccountHolderName())).isEqualTo(dtos);
	}

}
