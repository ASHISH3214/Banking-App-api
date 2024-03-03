package com.javaapp.bankingapp.serviceImpl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.javaapp.bankingapp.dao.UserDao;
import com.javaapp.bankingapp.dto.UserDto;
import com.javaapp.bankingapp.entity.User;
import com.javaapp.bankingapp.exceptions.UserAlreadyExistException;
import com.javaapp.bankingapp.exceptions.UserNotFoundException;
import com.javaapp.bankingapp.repository.UserRepository;
import com.javaapp.bankingapp.service.UserService;

@Service
public class UserServiceImpl implements UserService{

	private UserRepository userRepository;
	
	@Autowired
	private ModelMapper mapper;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	public UserServiceImpl(UserRepository userRepository) {
		super();
		this.userRepository = userRepository;
	}

	@Override
	public UserDao createUser(UserDto userDto) {
		User user = mapper.map(userDto, User.class);
		String userEmail = user.getEmail();
		Optional<User> check = userRepository.findByEmail(userEmail);
		if(check.isEmpty()) {
			//user.setAccountType(user.getAge()<18 ? "Student" : "General");
			user.setPassword(passwordEncoder.encode(user.getPassword()));
			User savedUser = userRepository.save(user);
			return mapper.map(savedUser, UserDao.class);
		}
		else {
			throw new UserAlreadyExistException("User already exist with email "+ userEmail);
		}
	}

	@Override
	public UserDao getUserById(Long id) {
		User user = userRepository
				.findById(id)
				.orElseThrow(() -> new UserNotFoundException("Account does not exist with id "+id));
		
		return mapper.map(user, UserDao.class);
	}

	@Override
	public List<UserDao> getUserByName(String name) {
		List<User> users = userRepository
				.findByName(name);
		if(users.isEmpty())
			throw new UserNotFoundException("No user found with name "+name);
		
		return users
		  .stream()
		  .map(u -> mapper.map(u, UserDao.class))
		  .collect(Collectors.toList());
		//System.out.println(dtos);
	}

	@Override
	public UserDao getUserByEmail(String email) {
		Optional<User> user = userRepository
				.findByEmail(email);
		if(user==null)
			throw new UserNotFoundException("no user found with email "+email);
		
		return mapper.map(user,UserDao.class);
	}

	@Override
	public List<UserDao> getAllUsersDetails() {
		List<User> users = userRepository
				.findAll();
		if(users.isEmpty())
			throw new UserNotFoundException("users not found");
		
		return users
		  .stream()
		  .map(u -> mapper.map(u, UserDao.class))
		  .collect(Collectors.toList());
		//System.out.println(dtos);
		
	}

}

