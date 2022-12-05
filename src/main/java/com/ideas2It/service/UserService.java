package com.ideas2It.service;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.ideas2It.model.User;

public interface UserService extends UserDetailsService {
	
    /**
     * This method gets the user object from controller 
     * and return the same user object if it's stored into database
     * 
     * @param user
     * @return
     */
	public User saveUser(User user);
}
 