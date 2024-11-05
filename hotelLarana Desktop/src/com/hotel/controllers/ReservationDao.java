package com.hotel.controllers;

import java.util.List;

import com.hotel.models.Reservation;

public interface ReservationDao {
	public void create(Reservation reservation);
	public void read(Reservation reservation);
	public void update(Reservation reservation);
	public void delete(int reservationid);
	public List<Reservation> list();
}
