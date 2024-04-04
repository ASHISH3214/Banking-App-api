package com.javaapp.bankingapp.service;

import java.util.List;

import com.javaapp.bankingapp.dto.UserDto;

public interface UserService {

	UserDto createUser(UserDto userDto);
	UserDto getUserById(Long id);
	List<UserDto> getUserByName(String name);
	UserDto getUserByEmail(String email);
	List<UserDto> getAllUsersDetails();
	
}
