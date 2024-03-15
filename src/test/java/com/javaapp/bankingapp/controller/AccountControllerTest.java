package com.javaapp.bankingapp.controller;

import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import org.instancio.Instancio;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import com.javaapp.bankingapp.dto.AccountDto;
import com.javaapp.bankingapp.entity.Account;
import com.javaapp.bankingapp.filter.JwtAuthFilter;
import com.javaapp.bankingapp.repository.AccountRepository;
import com.javaapp.bankingapp.serviceImpl.AccountServiceImpl;

@WebMvcTest(AccountController.class)
@WebAppConfiguration
class AccountControllerTest {

	@Autowired
	private MockMvc mockMvc;
	@MockBean
	private AccountServiceImpl service;
	@MockBean
	private JwtAuthFilter authFilter;
	@Mock
	private AccountRepository accountRepository;
	Account account;
	AccountDto accountDto;
	List<AccountDto> dtos;
	
	
	@BeforeEach
	void setUp() throws Exception {
		account = Instancio.of(Account.class).create();
		accountDto = new AccountDto(account.getId(), account.getAccountHolderName(), account.getBalance());
		dtos = Instancio.ofList(AccountDto.class).size(2).create();
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void testAddAccount() {
		fail("Not yet implemented");
	}

	@Test
	void testGetAccountById() throws Exception {
		when(service.getAccountById(account.getId())).thenReturn(accountDto);
		
		this.mockMvc.perform(get("/api/accounts/"+account.getId()))
		.andDo(print()).andExpect(status().isOk());
	}

	@Test
	void testDeposite() {
		fail("Not yet implemented");
	}

	@Test
	void testWithdraw() {
		fail("Not yet implemented");
	}

	@Test
	void testGetAllAccount() {
		fail("Not yet implemented");
	}

	@Test
	void testDeleteAccount() {
		fail("Not yet implemented");
	}

	@Test
	void testGetAllAccountByName() {
		fail("Not yet implemented");
	}

}
