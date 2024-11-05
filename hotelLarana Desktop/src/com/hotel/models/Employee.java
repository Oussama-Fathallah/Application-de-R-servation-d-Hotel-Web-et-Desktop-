package com.hotel.models;

public class Employee {
	private int employeeID;
	private String firstName;
	private String lastName;
	private String position;

	

	
    public Employee(int employeeID, String firstName, String lastName, String position) {
        this.employeeID = employeeID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.position = position;
    }
	public Employee(String firstName, String lastName, String position) {
		
	    this.firstName = firstName;
	    this.lastName = lastName;
	    this.position = position;
	}
	public int getEmployeeID() {
		return employeeID;
	}
	public void setEmployeeID(int employeeID) {
		this.employeeID = employeeID;
	}
	
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	public String getPosition() {
		return position;
	}
	public void setPosition(String position) {
		this.position = position;
	}
}
