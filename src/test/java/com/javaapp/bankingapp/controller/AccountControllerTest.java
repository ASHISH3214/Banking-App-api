package com.javaapp.bankingapp.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.HashMap;
import java.util.Map;

import org.instancio.Instancio;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.javaapp.bankingapp.dto.AccountDto;
import com.javaapp.bankingapp.service.AccountService;

@SpringBootTest
class AccountControllerTest {

	private MockMvc mockMvc;
	@Autowired
	private WebApplicationContext context;
	@Autowired
	private ObjectMapper objectMapper;
	@Mock
	private AccountService accountService; 
	
	@BeforeEach
	void setUp() throws Exception {
		mockMvc = MockMvcBuilders.webAppContextSetup(context).apply(springSecurity()).build();
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Order(1)
	@Test
	@WithMockUser(username = "usertest", roles = {"ADMIN"})
	void testAddAccount() throws Exception {
		AccountDto accountDto = Instancio.of(AccountDto.class).create();
		String jsonReq = objectMapper.writeValueAsString(accountDto);
		//when(accountService.createAccount(accountDto)).thenReturn(accountDto);
		
		MvcResult mvcResult = mockMvc.perform(post("/api/accounts").content(jsonReq).contentType(MediaType.APPLICATION_JSON)).andDo(print())
		.andExpect(status().isCreated()).andReturn();
		
		assertEquals(201, mvcResult.getResponse().getStatus());
	}

	@Order(2)
	@WithMockUser(username = "usertest", roles = {"ADMIN"})
	@Test
	void testGetAccountById() throws Exception {
		MvcResult mvcResult = mockMvc.perform(get("/api/accounts/4").contentType(MediaType.APPLICATION_JSON)).andDo(print())
				.andExpect(status().isOk()).andReturn();
				
				assertEquals(200, mvcResult.getResponse().getStatus());
	}

	@Order(3)
	@WithMockUser(username = "usertest", roles = {"ADMIN"})
	@Test
	void testDeposite() throws Exception {
		Map<String, Double> req = new HashMap<String, Double>();
		req.put("amount", 1000d);
		String jsonReq = objectMapper.writeValueAsString(req);
		
		MvcResult mvcResult = mockMvc.perform(put("/api/accounts/4/deposit").content(jsonReq).contentType(MediaType.APPLICATION_JSON)).andDo(print())
				.andExpect(status().isOk()).andReturn();
				
				assertEquals(200, mvcResult.getResponse().getStatus());

	}

	@Order(4)
	@WithMockUser(username = "usertest", roles = {"ADMIN"})
	@Test
	void testWithdraw() throws Exception {
		Map<String, Double> req = new HashMap<String, Double>();
		req.put("amount", 1000d);
		String jsonReq = objectMapper.writeValueAsString(req);
		
		MvcResult mvcResult = mockMvc.perform(put("/api/accounts/4/withdraw").content(jsonReq).contentType(MediaType.APPLICATION_JSON)).andDo(print())
				.andExpect(status().isOk()).andReturn();
				
				assertEquals(200, mvcResult.getResponse().getStatus());

	}

	@Order(5)
	@WithMockUser(username = "usertest", roles = {"ADMIN"})
	@Test
	void testGetAllAccount() throws Exception {
		MvcResult mvcResult = mockMvc.perform(get("/api/accounts").contentType(MediaType.APPLICATION_JSON)).andDo(print())
				.andExpect(status().isOk()).andReturn();
				
				assertEquals(200, mvcResult.getResponse().getStatus());
	}

	@Order(7)
	@WithMockUser(username = "usertest", roles = {"ADMIN"})
	@Test
	void testDeleteAccount() throws Exception {
		MvcResult mvcResult = mockMvc.perform(delete("/api/accounts/7").contentType(MediaType.APPLICATION_JSON)).andDo(print())
				.andExpect(status().isOk()).andReturn();
				
				assertEquals(200, mvcResult.getResponse().getStatus());
	}

	@Order(6)
	@WithMockUser(username = "usertest", roles = {"ADMIN"})
	@Test
	void testGetAllAccountByName() throws Exception {
		MvcResult mvcResult = mockMvc.perform(get("/api/accounts/name?name=kamlesh").contentType(MediaType.APPLICATION_JSON)).andDo(print())
				.andExpect(status().isOk()).andReturn();
				
				assertEquals(200, mvcResult.getResponse().getStatus());
	}

}
