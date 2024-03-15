package com.javaapp.bankingapp.serviceImpl;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import org.instancio.Instancio;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.modelmapper.config.Configuration.AccessLevel;
import org.modelmapper.convention.MatchingStrategies;
import org.modelmapper.convention.NameTokenizers;
import org.modelmapper.convention.NamingConventions;
import org.springframework.beans.factory.annotation.Autowired;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.javaapp.bankingapp.dao.UserDao;
import com.javaapp.bankingapp.dto.UserDto;
import com.javaapp.bankingapp.entity.User;
import com.javaapp.bankingapp.repository.UserRepository;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {


	@Mock
	private UserRepository userRepository;
	@Autowired
	private UserServiceImpl userService;
	@Mock
	private ModelMapper mapper;
	AutoCloseable autoCloseable;
	List<User> users;
	User user;
	
	@BeforeEach
	void setUp() throws Exception {
		this.mapper = new ModelMapper();
		autoCloseable = MockitoAnnotations.openMocks(this);
		userService = new UserServiceImpl(userRepository);
		users = Instancio.ofList(User.class).size(2).create();
		user = Instancio.of(User.class).create();
	}

	@AfterEach
	void tearDown() throws Exception {
		autoCloseable.close();
	}
	
	
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

//	@Test
//	void testUserServiceImpl() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	void testCreateUser() {
//		fail("Not yet implemented");
//	}
//
	@Test
	void testGetUserById() {
		ModelMapper modelMapper = Mockito.mock(ModelMapper.class);
		UserDao dao = new UserDao(user.getId(), user.getName(), user.getAge(), user.getEmail(), user.getRoles());
		Optional<User> optionalUser = Optional.ofNullable(user);
		when(userRepository.findById(any())).thenReturn(optionalUser);
		when(this.mapper.map(any(), any())).thenReturn(dao);
		
		//UserDao result  = userService.getUserById(user.getId());
		
		//assertThat(result).isNotNull();
		assertThat(optionalUser.get().getId()).isEqualTo(userService.getUserById(user.getId()).getId());
	
	}
//
//	@Test
//	void testGetUserByName() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	void testGetUserByEmail() {
//		fail("Not yet implemented");
//	}
//
	@Test
	void testGetAllUsersDetails() {
		UserDao userDao = Instancio.of(UserDao.class).create();
		when(userRepository.findAll()).thenReturn(users);
		//when(modelMapper.map(any(), any())).thenReturn(userDao);
		
		assertThat(2).isEqualTo(userService.getAllUsersDetails().size());
	}

}
