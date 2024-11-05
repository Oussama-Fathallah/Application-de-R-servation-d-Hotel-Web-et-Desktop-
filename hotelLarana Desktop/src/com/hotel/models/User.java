package com.hotel.models;

public class User {
	private int userID;
	private String firstname;
	private String lastname;
	private String email;
	private String password;
	private String userType;

    public User(String firstname, String lastname, String email, String password, String userType) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.setEmail(email);
        this.password = password;
        this.userType = userType;
    }
	
    public User(int userID, String firstname, String lastname, String email, String password, String userType) {
        this.userID = userID;
        this.firstname = firstname;
        this.lastname = lastname;
        this.setEmail(email);
        this.password = password;
        this.userType = userType;
    }
	public User(String email, String password, String type) {
        this.setEmail(email);
        this.password = password;
        this.userType = type;
	}
	public int getUserID() {
		return userID;
	}
	public void setUserID(int userID) {
		this.userID = userID;
	}
	

	
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getUserType() {
		return userType;
	}
	public void setUserType(String userType) {
		this.userType = userType;
	}
	public String getFirstname() {
		return firstname;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
}
