package com.javaapp.bankingapp.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import org.instancio.Instancio;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.javaapp.bankingapp.dao.UserDao;
import com.javaapp.bankingapp.dto.UserDto;
import com.javaapp.bankingapp.entity.User;
import com.javaapp.bankingapp.repository.UserRepository;
import com.javaapp.bankingapp.serviceImpl.UserServiceImpl;

@SpringBootTest
class UserServiceTest {
	
	@Test
	void contextLoads() {
	}
	
	@MockBean
	private UserRepository userRepository;
	
	@Autowired
	private UserServiceImpl userService;
	
	List<User> users = Instancio.ofList(User.class).size(2).create();
	User user = Instancio.of(User.class).create();
	
	@Test
	public void getAllUsersTest() {
		when(userRepository.findAll()).thenReturn(users);
		
		assertThat(2).isEqualTo(userService.getAllUsersDetails().size());
	}
	
	@Test
	public void getUsersByEmailTest() {
		Optional<User> optionalUser = Optional.ofNullable(user);
		when(userRepository.findByEmail(user.getEmail())).thenReturn(optionalUser);
		
		assertThat(optionalUser.get().getEmail()).isEqualTo(userService.getUserByEmail(user.getEmail()).getEmail());
	}
	
	@Test
	public void getUsersByNameTest() {
		when(userRepository.findByName(users.get(0).getName())).thenReturn(users);
		
		assertThat(2).isEqualTo(userService.getUserByName(users.get(0).getName()).size());
	}
	
	@Test
	public void getUsersByIdTest() {
		Optional<User> optionalUser = Optional.ofNullable(user);
		when(userRepository.findById(user.getId())).thenReturn(optionalUser);
		
		assertThat(optionalUser.get().getId()).isEqualTo(userService.getUserById(user.getId()).getId());
	}
	
	@Test
	public void createUserTest() {
		UserDto dto = new UserDto(user.getId(), user.getName(), user.getAge(), user.getEmail(), user.getPassword(), user.getRoles());
		
		when(userRepository.save(any())).thenReturn(user);
		UserDao dao = userService.createUser(dto);
		
		assertThat(dao).isNotNull();
		assertThat(dao.getEmail()).isEqualTo(user.getEmail());
	}

}
