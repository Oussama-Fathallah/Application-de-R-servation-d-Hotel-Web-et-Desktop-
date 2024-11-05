package com.hotel.controllers;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.hotel.db.DatabaseConnector;
import com.hotel.models.Reservation;

import javafx.scene.control.Alert;

public class ReservationService implements ReservationDao{

    public void create(Reservation reservation) {
        try (Connection connection = DatabaseConnector.getConnection();
             PreparedStatement statement = connection.prepareStatement("INSERT INTO reservations (FirstName, LastName,Email,Number, CheckInDate, CheckOutDate,NumberOfGuests,RoomType) VALUES (?, ?, ?, ?,?, ?, ?, ?)")) {

            statement.setString(1, reservation.getFirstname());
            statement.setString(2, reservation.getLastname());
            statement.setString(3, reservation.getEmail());
            statement.setInt(4, reservation.getNumber());
            statement.setDate(5, new java.sql.Date(reservation.getCheckInDate().getTime()));
            statement.setDate(6, new java.sql.Date(reservation.getCheckOutDate().getTime()));
            statement.setInt(7, reservation.getNumberOfGuests());
            statement.setString(8, reservation.getRoomType());

            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

	@Override
	public void read(Reservation reservation) {
		// TODO Auto-generated method stub
		
	}

	public void update(Reservation reservation) {
	    try (Connection connection = DatabaseConnector.getConnection();
	         PreparedStatement updateStatement = connection.prepareStatement(
	                 "UPDATE reservations SET FirstName=?, LastName=?, Email=?, Number=?, CheckInDate=?, CheckOutDate=?, NumberOfGuests=?, RoomType=? WHERE Id=?")) {

	        updateStatement.setString(1, reservation.getFirstname());
	        updateStatement.setString(2, reservation.getLastname());
	        updateStatement.setString(3, reservation.getEmail());
	        updateStatement.setInt(4, reservation.getNumber());
	        updateStatement.setDate(5, new java.sql.Date(reservation.getCheckInDate().getTime()));
	        updateStatement.setDate(6, new java.sql.Date(reservation.getCheckOutDate().getTime()));
	        updateStatement.setInt(7, reservation.getNumberOfGuests());
	        updateStatement.setString(8, reservation.getRoomType());
	        updateStatement.setInt(9, reservation.getReservationID());

	        int rowsAffected = updateStatement.executeUpdate();

	        if (rowsAffected > 0) {
	            Alert alert = new Alert(Alert.AlertType.INFORMATION);
	            alert.setTitle("Success");
	            alert.setHeaderText(null);
	            alert.setContentText("Reservation updated successfully!");
	            alert.showAndWait();
	        } else {
	            Alert alert = new Alert(Alert.AlertType.ERROR);
	            alert.setTitle("Error");
	            alert.setHeaderText(null);
	            alert.setContentText("Failed to update reservation. Please check the ID.");
	            alert.showAndWait();
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}



	public void delete(int reservationId) {
	    try (Connection connection = DatabaseConnector.getConnection();
	        PreparedStatement selectStatement = connection.prepareStatement("SELECT * FROM reservations WHERE Id = ?")) {

	        selectStatement.setInt(1, reservationId);
	        ResultSet resultSet = selectStatement.executeQuery();
	        if (resultSet.next()) {
	        	 try (PreparedStatement deleteStatement = connection.prepareStatement("DELETE FROM reservations WHERE Id = ?")) {

	                    deleteStatement.setInt(1, reservationId);
	                    deleteStatement.executeUpdate();
	                    
	                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
	                    alert.setTitle("Success");
	                    alert.setHeaderText(null);
	                    alert.setContentText("Reservation deleted successfully!");
	                    alert.showAndWait();

	                } catch (SQLException e) {
	                    e.printStackTrace();
	                }
	        }
	        else {
	            Alert alert = new Alert(Alert.AlertType.ERROR);
	            alert.setTitle("Error");
	            alert.setHeaderText(null);
	            alert.setContentText("Please enter a valid ID.");
	            alert.showAndWait();
	        }
	        
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}

	
	public List<Reservation> list() {
	    List<Reservation> reservations = new ArrayList<>();

	    try (Connection connection = DatabaseConnector.getConnection();
	         PreparedStatement statement = connection.prepareStatement("SELECT * FROM reservations");
	         ResultSet resultSet = statement.executeQuery()) {

	        while (resultSet.next()) {
	            int reservationID = resultSet.getInt("Id");
	            String firstname = resultSet.getString("FirstName");
	            String lastname = resultSet.getString("LastName");
	            String email = resultSet.getString("Email");
	            int number = resultSet.getInt("Number");
	            Date checkInDate = resultSet.getDate("CheckInDate");
	            Date checkOutDate = resultSet.getDate("CheckOutDate");
	            int numberOfGuests = resultSet.getInt("NumberOfGuests");
	            String roomType = resultSet.getString("RoomType");
	            
	            reservations.add(new Reservation(reservationID,firstname,lastname,email,number, checkInDate, checkOutDate,numberOfGuests,roomType));
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	    return reservations;
	}


}
