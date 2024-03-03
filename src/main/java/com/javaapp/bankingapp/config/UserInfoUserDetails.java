package com.javaapp.bankingapp.config;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.javaapp.bankingapp.entity.Role;
import com.javaapp.bankingapp.entity.User;

public class UserInfoUserDetails implements UserDetails{

	private String email;
	private String password;
	private List<GrantedAuthority> authorities;
	
	
	
	public UserInfoUserDetails(User user) {
		this.email = user.getEmail();
		this.password = user.getPassword();
		List<Role> roles = user.getRoles();
		this.authorities = roles.stream()
		.map((role) -> new SimpleGrantedAuthority(role.getName()))
		.collect(Collectors.toList());
		
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return authorities;
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return password;
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return email;
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}

}
