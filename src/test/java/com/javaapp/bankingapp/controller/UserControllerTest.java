package com.javaapp.bankingapp.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
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
import com.javaapp.bankingapp.dto.AuthRequest;
import com.javaapp.bankingapp.dto.UserDto;
import com.javaapp.bankingapp.service.UserService;

@SpringBootTest
class UserControllerTest {

private MockMvc mockMvc;
	
	@Autowired
	private WebApplicationContext context;
	
	@Autowired
	private ObjectMapper objectMapper;
	
	@Mock
	private UserService userService;
	
	@BeforeEach
	void setUp() throws Exception {
		mockMvc = MockMvcBuilders.webAppContextSetup(context).apply(springSecurity()).build();	
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void testCheckServer() throws Exception {
		MvcResult mvcResult = mockMvc.perform(get("/api/users/welcome").contentType(MediaType.APPLICATION_JSON)).andDo(print())
		.andExpect(status().isOk()).andReturn();
		
		assertEquals(200, mvcResult.getResponse().getStatus());
	}

	@Test
	void testCreateUser() throws Exception {
		UserDto user = new UserDto(1L, "user1", 25, "user1@gmail.com", "12345", new ArrayList<>());
		String jsonReq = objectMapper.writeValueAsString(user);
			
	MvcResult mvcResult =	mockMvc.perform(post("/api/users/new")
			.content(jsonReq).contentType(MediaType.APPLICATION_JSON)).andDo(print())
			.andExpect(status().isCreated()).andReturn();
	
	assertEquals(201, mvcResult.getResponse().getStatus());
	}

	@WithMockUser(username = "testuser", roles = {"USER"})
	@Test
	void testGetUserById() throws Exception {
		MvcResult mvcResult = mockMvc.perform(get("/api/users/15")
				.contentType(MediaType.APPLICATION_JSON)).andDo(print())
				.andExpect(status().isOk()).andReturn();
		
		assertEquals(200, mvcResult.getResponse().getStatus());
	}

	@WithMockUser(username = "testuser", roles = {"ADMIN"})
	@Test
	void testListOfUsersByName() throws Exception {
		MvcResult mvcResult = mockMvc.perform(get("/api/users/name?name=ashish")
				.contentType(MediaType.APPLICATION_JSON)).andDo(print())
				.andExpect(status().isOk()).andReturn();
		
		assertEquals(200, mvcResult.getResponse().getStatus());
	}

	@WithMockUser(username = "testuser", roles = {"ADMIN"})
	@Test
	void testGetUserByEmail() throws Exception {
		MvcResult mvcResult = mockMvc.perform(get("/api/users/email?email=ashish@gmail.com")
				.contentType(MediaType.APPLICATION_JSON)).andDo(print())
				.andExpect(status().isOk()).andReturn();
		
		assertEquals(200, mvcResult.getResponse().getStatus());
	}

	@WithMockUser(username = "testuser", roles = {"USER", "ADMIN"})
	@Test
	void testGetAllUsers() throws Exception {
		MvcResult mvcResult =	mockMvc.perform(get("/api/users/all")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andReturn();
		
		System.out.println(mvcResult.getResponse());
		
		assertEquals(200, mvcResult.getResponse().getStatus());
	}

	@Test
	void testAuthenticateAndGetToken() throws Exception {
		AuthRequest authRequest = new AuthRequest("qwer@gmail.com", "1234");
		String jsonReq = objectMapper.writeValueAsString(authRequest);
		
		MvcResult mvcResult =	mockMvc.perform(post("/api/users/authenticate")
				.content(jsonReq)
				.contentType(MediaType.APPLICATION_JSON)).andDo(print())
				.andExpect(status().isOk()).andReturn();
		
		assertEquals(200, mvcResult.getResponse().getStatus());
	}
	
	@Test
	void testAuthenticateAndGetTokenUserNotFound() throws Exception {
		AuthRequest authRequest = new AuthRequest("lpko@gmail.com", "1234");
		String jsonReq = objectMapper.writeValueAsString(authRequest);
		
		MvcResult mvcResult =	mockMvc.perform(post("/api/users/authenticate")
				.content(jsonReq)
				.contentType(MediaType.APPLICATION_JSON)).andDo(print())
				.andExpect(status().isUnauthorized()).andReturn();
		
		assertEquals(401, mvcResult.getResponse().getStatus());
	}

}
