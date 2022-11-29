package com.ideas2It.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.ideas2It.dao.UserDao;
import com.ideas2It.model.User;
import com.ideas2It.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao dao;

	public User saveUser(User user) {
	    BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
	    user.setPassword(encoder.encode(user.getPassword()));
		return dao.save(user);
	}

}
