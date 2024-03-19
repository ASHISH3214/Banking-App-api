package com.javaapp.bankingapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.javaapp.bankingapp.dao.UserDao;
import com.javaapp.bankingapp.dto.AuthRequest;
import com.javaapp.bankingapp.dto.UserDto;
import com.javaapp.bankingapp.exceptions.UserNotFoundException;
import com.javaapp.bankingapp.service.UserService;
import com.javaapp.bankingapp.serviceImpl.JwtService;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/users")
//enable only specific controller authorization in swagger
//@SecurityRequirement(
//		name = "Bearer Authentication")
public class UserController {

	@Autowired
	private UserService userService;
	
	@Autowired
	private JwtService jwtService;
	
	@Autowired
	private AuthenticationManager authenticationManager;

	public UserController(UserService userService) {
		super();
		this.userService = userService;
	}
	
	@GetMapping("/welcome")
	public String checkServer() {
		return "Hello from backend!";
	}
	
	//create new user REST API
	@PostMapping("/new")
	public ResponseEntity<UserDao> createUser(@Valid @RequestBody UserDto userDto){
		return new ResponseEntity<>(userService.createUser(userDto), HttpStatus.CREATED);
	}
	
	//Get user by Id
	@PreAuthorize("hasAuthority('ROLE_USER')")
	@GetMapping("/{id}")
	public ResponseEntity<UserDao> getUserById(@PathVariable long id){
		UserDao userDao = userService.getUserById(id);
		return new ResponseEntity<UserDao>(userDao, HttpStatus.OK);
	}
	
	//get users by name
	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	@GetMapping("/name")
	public ResponseEntity<List<UserDao>> ListOfUsersByName(@RequestParam String name){
		return new ResponseEntity<List<UserDao>>(userService.getUserByName(name), HttpStatus.OK);
	}
	
	//get user by email
	@GetMapping("/email")
	public ResponseEntity<UserDao> getUserByEmail(@RequestParam String email){
		return new ResponseEntity<UserDao>(userService.getUserByEmail(email), HttpStatus.OK);
	}
	
	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	@GetMapping("/all")
	public ResponseEntity<List<UserDao>> getAllUsers(){
		return new ResponseEntity<List<UserDao>>(userService.getAllUsersDetails(), HttpStatus.OK);
	}
	
	@PostMapping("/authenticate")
	public String authenticateAndGetToken(@RequestBody AuthRequest authRequest ){
		Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
		if(authentication.isAuthenticated())
			return jwtService.generateToken(authRequest.getUsername());
		else
			throw new UserNotFoundException("invald username");
	}

}