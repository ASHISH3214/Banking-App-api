package com.javaapp.bankingapp.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.instancio.Instancio;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.javaapp.bankingapp.entity.User;

@DataJpaTest
class UserRepositoryTest {

	@Autowired
	private UserRepository userRepository;
	User user;
	// test case success
	
	@BeforeEach
	void setUp() throws Exception {
		user = Instancio.of(User.class).create();
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
	

}
