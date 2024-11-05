package com.example.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import com.example.model.Reservation;

public class ReservationDao {
    
    public int addReservation(Reservation reservation) throws ClassNotFoundException {
        String INSERT_RESERVATION_SQL = "INSERT INTO reservations "
                + "(FirstName, LastName, Email, Number, CheckInDate, CheckOutDate, NumberOfGuests, RoomType) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        
        int result = 0;
        
        Class.forName("com.mysql.jdbc.Driver");
        
        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/larana_hotel?useSSL=false", "root", "root");
             PreparedStatement ps = connection.prepareStatement(INSERT_RESERVATION_SQL)) {
            
            ps.setString(1, reservation.getFirstName());
            ps.setString(2, reservation.getLastName());
            ps.setString(3, reservation.getEmail());
            ps.setString(4, reservation.getPhoneNumber());
            ps.setDate(5, new java.sql.Date(reservation.getCheckInDate().getTime()));
            ps.setDate(6, new java.sql.Date(reservation.getCheckOutDate().getTime()));
            ps.setInt(7, reservation.getNumberOfGuests());
            ps.setString(8, reservation.getRoomType());
            
            System.out.println(ps);
            
            result = ps.executeUpdate();
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return result;
    }
}
