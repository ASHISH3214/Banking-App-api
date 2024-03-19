package com.javaapp.bankingapp.repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.javaapp.bankingapp.entity.User;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class UserRepositoryTest {

	@Autowired
	private UserRepository userRepository;
	User user;
	// test case success
	
	@BeforeEach
	void setUp() throws Exception {
		userRepository.deleteAll();
		user = new User(any(), "user1", 25, "user1@gmail.com", "12345", new ArrayList<>());
		userRepository.save(user);
		
	}

	@AfterEach
	void tearDown() throws Exception {
		user = null ;
		userRepository.deleteAll();
	}
	
	@Test
	void testFindByName_Found() {
		List<User> users = userRepository.findByName(user.getName());
		
		assertThat(users).isNotNull();
		assertThat(users.get(0).getId()).isEqualTo(user.getId());
	}
	
	@Test
	void testFindByName_NotFound() {
		List<User> users = userRepository.findByName(any());
		
		assertThat(users).isNotNull();
		assertThat(users.size()).isEqualTo(0);
	}
	
	@Test
	void testFindByEmail_Found() {
		Optional<User> user1 = userRepository.findByEmail(user.getEmail());
		
		assertThat(user1).isNotNull();
		assertThat(user1.get().getEmail()).isEqualTo(user.getEmail());
	}
	
	@Test
	void testFindByEmail_NotFound() {
		Optional<User> user1 = userRepository.findByEmail(any());
		
		assertThat(user1).isNotNull();
		assertThat(user1.isEmpty());
	}
	

}
