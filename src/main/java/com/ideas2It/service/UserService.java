package com.ideas2It.service;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.ideas2It.model.User;

public interface UserService extends UserDetailsService {
 
	public User saveUser(User user);
}
 