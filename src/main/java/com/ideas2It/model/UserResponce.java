package com.ideas2It.model;

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
