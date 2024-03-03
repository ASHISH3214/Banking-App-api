package com.javaapp.bankingapp.dto;

import java.util.List;

import com.javaapp.bankingapp.entity.Role;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
	private Long id;
	private String name;
	private Integer age;
	private String email;
	private String password;
	private List<Role> roles;
//	private String address;
//	private String phoneNo;
}
