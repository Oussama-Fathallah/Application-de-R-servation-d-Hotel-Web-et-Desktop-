package com.hotel.controllers;

import java.util.List;

import com.hotel.models.Employee;

public interface EmployeeDao {
	public void create(Employee employee);
	public void read(Employee employee);
	public void update(Employee employee);
	public void delete(int employeeid);
	public List<Employee> list();
}
