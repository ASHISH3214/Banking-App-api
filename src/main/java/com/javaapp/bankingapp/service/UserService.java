package com.javaapp.bankingapp.service;

import java.util.List;

import com.javaapp.bankingapp.dao.UserDao;
import com.javaapp.bankingapp.dto.UserDto;

public interface UserService {

	UserDao createUser(UserDto userDto);
	UserDao getUserById(Long id);
	List<UserDao> getUserByName(String name);
	UserDao getUserByEmail(String email);
	List<UserDao> getAllUsersDetails();
	
}
