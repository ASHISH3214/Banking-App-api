package com.javaapp.bankingapp.dao;

import java.util.List;

import com.javaapp.bankingapp.entity.Role;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDao {
	private Long id;
	private String name;
	private String age;
	private String email;
	private List<Role> roles; 

}
