package com.javaapp.bankingapp.entity;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "user", uniqueConstraints = @UniqueConstraint(columnNames = "email"))
@Entity
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotNull(message = "user's name can not be null")
	@NotBlank(message = "user's name required")
	private String name;
	
	@Min(value = 15, message = "age must be greater then 14") 
	@NotNull(message = "age can not be null")
	private Integer age;
	
	@Email(message = "invalid email")
	@NotBlank(message = "email required")
	@NotNull(message = "user emali can not be null")
	private String email;
	//private String address;
	private String password;
	
	//@Pattern(regexp = "^\\d{10}$", message = "phone no. not valid")
	//private String phoneNo;
	//private String accountType;
	
	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinTable(
			name = "user_roles",
			joinColumns = @JoinColumn(
					name = "user_id", referencedColumnName = "id"),
			inverseJoinColumns = @JoinColumn(
					name = "role_id", referencedColumnName = "id"))
	private List<Role> roles;
	
	
}
