package com.javaapp.bankingapp.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.javaapp.bankingapp.filter.JwtAuthFilter;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
@EnableWebMvc
public class SecurityConfig {
	
	@Autowired
	private JwtAuthFilter authFilter;
	
	@Autowired
	private JwtAuthenticationEntryPoint authenticationEntryPoint;
	
	@Bean
	//authentication
	UserDetailsService userDetailsService() {
//		UserDetails admin = User.withUsername("ashish")
//				.password(encoder.encode("ashish"))
//				.roles("ADMIN")
//				.build();
//		UserDetails user = User.withUsername("ravi")
//				.password(encoder.encode("ravi"))
//				.roles("USER")
//				.build();
//		
//		return new InMemoryUserDetailsManager(admin,user);
		return new UserInfoDetailsService();
	}
	
	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
		httpSecurity
		.cors(cors -> cors.disable())
		        .csrf(csrf -> csrf.disable())
		        .authorizeHttpRequests(auth -> 
					
						auth
						    .requestMatchers("/api/users/welcome","/api/users/new","/api/users/authenticate").permitAll()
						    .requestMatchers("/swagger-ui.html", "/swagger-ui/**", "/swagger-resources/**", "/swagger-resources", "/v3/api-docs/**", "/proxy/**").permitAll()
						    .anyRequest().authenticated()
						  )
						
						    .authenticationProvider(authenticationProvider())
						    .exceptionHandling(ex -> ex.authenticationEntryPoint(authenticationEntryPoint))
						    .sessionManagement((session) -> session
						            .sessionCreationPolicy(SessionCreationPolicy.STATELESS));
		
						    httpSecurity.addFilterBefore(authFilter, UsernamePasswordAuthenticationFilter.class)
						    .formLogin(form -> form.disable())
						    .httpBasic(basic -> basic.disable());
				
		       return httpSecurity.build();
		
	}
	
	
	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();	}
	
	@Bean
    AuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider authenticationProvider=new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetailsService());
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        return authenticationProvider;
    }
	
	@Bean
	AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
		return config.getAuthenticationManager();
	}
	
}
