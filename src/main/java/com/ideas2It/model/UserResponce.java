package com.ideas2It.model;

/**
 * The User response class have token attribute
 * This class contain getter and setter method for user response attributes 
 *
 * @version 1.0
 * @author arunkumar
 */
public class UserResponce {
	
    public UserResponce(String token) {
		super();
		this.token = token;
	}

	private String token;

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}
}
