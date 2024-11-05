package com.hotel.controllers;

import java.util.List;

import com.hotel.models.Room;

public interface RoomDao {
	public void create(Room room);
	public void read(Room room);
	public void update(Room room);
	public void delete(int roomid);
	public List<Room> list();
}
