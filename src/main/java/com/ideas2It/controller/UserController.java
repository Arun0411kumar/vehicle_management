package com.ideas2It.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ideas2It.model.User;
import com.ideas2It.model.UserRequest;
import com.ideas2It.model.UserResponce;
import com.ideas2It.service.UserService;
import com.ideas2It.util.jwtUtil.JwtUtil;

@RestController
public class UserController {

	@Autowired
	private UserService service;

	
    @Autowired 
    private AuthenticationManager authentication;
	 

	@PostMapping("/register")
	public User createUser(@RequestBody @Valid User user) {
		return service.saveUser(user);
	}

	@PostMapping("/loginUser")
	public UserResponce login(@RequestBody UserRequest user) {
        authentication.authenticate(new UsernamePasswordAuthenticationToken(
		user.getUsername(), user.getPassword()));	 
		return new UserResponce(JwtUtil.generateToken(user.getUsername()));
	}
}
