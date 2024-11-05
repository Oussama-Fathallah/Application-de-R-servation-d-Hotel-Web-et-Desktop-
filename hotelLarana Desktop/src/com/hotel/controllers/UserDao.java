package com.hotel.controllers;

import java.util.List;

import com.hotel.models.User;

public interface UserDao {
	public void create(User user);
	public void read(User user);
	public void update(User user);
	public void delete(int userid);
	public List<User> list();
}
