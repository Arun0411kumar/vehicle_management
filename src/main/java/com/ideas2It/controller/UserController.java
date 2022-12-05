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
import com.ideas2It.util.customException.VehicleManagementException;
import com.ideas2It.util.jwtUtil.JwtUtil;

/**
 * The user controller class that implements an application that Simply
 * create, login these operations passed service from
 * controller class
 *
 * @version 1.0
 * @author arunkumar
 */
@RestController
public class UserController {

	@Autowired
	private UserService service;

    @Autowired 
    private AuthenticationManager authentication;
	 

	/**
	 * Gets the manufacturer object and put it into database
	 * 
	 * @param user object
	 * @return ResponceEntity
	 */
	@PostMapping("/register")
	public User createUser(@RequestBody @Valid User user) {
		return service.saveUser(user);
	}

	/**
	 * Gets the UserRequest object and verify the given userName, password
	 * then it will generate a token
	 * 
	 * @param user object
	 * @return ResponceEntity
	 * @throws VehicleManagementException 
	 */
	@PostMapping("/loginUser")
	public UserResponce login(@RequestBody UserRequest user) throws VehicleManagementException {
		try {
            authentication.authenticate(new UsernamePasswordAuthenticationToken(
		    user.getUsername(), user.getPassword()));    
		} catch (Exception e) {
			throw new VehicleManagementException("invalid username or password");
		}
		return new UserResponce(JwtUtil.generateToken(user.getUsername()));
	}
}
