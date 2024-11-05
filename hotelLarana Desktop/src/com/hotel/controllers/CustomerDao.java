package com.hotel.controllers;

import java.util.List;

import com.hotel.models.Customer;

public interface CustomerDao {
	public void create(Customer customer);
	public void read(Customer customer);
	public void update(Customer customer);
	public void delete(int customerid);
	public List<Customer> list();
}
