package com.mtanevski.models;

public class User {
	
	private String name;
	private String email;
	private String website;
	private String password;
	
	public User(){
		this.name = "";
		this.email = "";
		this.website = "";
		this.password = "";
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getWebsite() {
		return website;
	}
	public void setWebsite(String website) {
		this.website = website;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
