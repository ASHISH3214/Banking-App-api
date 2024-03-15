package com.javaapp.bankingapp.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.javaapp.bankingapp.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{
	
	 Optional<User> findByEmail(String email);
	 List<User> findByName(String name);
	 
	 

}
