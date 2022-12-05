package com.ideas2It.model;

/**
 * The UserRequest class have username and password attributes 
 * This class contain getter and setter method for user request attributes 
 *
 * @version 1.0
 * @author arunkumar
 */
public class UserRequest {
	
	private String username;
	private String password;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
