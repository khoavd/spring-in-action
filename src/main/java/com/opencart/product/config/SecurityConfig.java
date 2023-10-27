package com.opencart.product.config;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
public class SecurityConfig {
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public UserDetailsService userDetailService(PasswordEncoder encode) {
		List<UserDetails> userList = new ArrayList<>();
		
		User buzz = new User("buzz", passwordEncoder().encode("123456") , Arrays.asList(new SimpleGrantedAuthority("ROLE_USER")));
		User woody = new User("woody", passwordEncoder().encode("123456"), Arrays.asList(new SimpleGrantedAuthority("ROLE_USER")));
		
		userList.add(buzz);
		userList.add(woody);
		
		return new InMemoryUserDetailsManager(userList);
		
	}
	
}
