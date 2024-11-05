package com.hotel.controllers;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.hotel.db.DatabaseConnector;
import com.hotel.models.Room;

import javafx.scene.control.Alert;

public class RoomService implements RoomDao{

	public void create(Room room) {
	    try (Connection connection = DatabaseConnector.getConnection();
	         PreparedStatement statement = connection.prepareStatement("INSERT INTO room (RoomNumber, Type, Availability) VALUES (?, ?, ?)")) {

	        statement.setInt(1, room.getRoomNumber());
	        statement.setString(2, room.getRoomType());
	        statement.setInt(3, room.getRoomAvailability());

	        statement.executeUpdate();

	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}

	
	public void read(Room room) {
		// TODO Auto-generated method stub
		
	}

	public void update(Room room) {
	    try (Connection connection = DatabaseConnector.getConnection();
	         PreparedStatement updateStatement = connection.prepareStatement("UPDATE room SET Type = ?, Availability = ? WHERE RoomNumber = ?")) {

	    	updateStatement.setString(1, room.getRoomType());
	    	updateStatement.setInt(2, room.getRoomAvailability());
	    	updateStatement.setInt(3, room.getRoomNumber());

	        int rowsUpdated = updateStatement.executeUpdate();

	        if (rowsUpdated > 0) {
	            
	            Alert alert = new Alert(Alert.AlertType.INFORMATION);
	            alert.setTitle("Success");
	            alert.setHeaderText(null);
	            alert.setContentText("Room updated successfully!");
	            alert.showAndWait();
	        }

	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}

	
	public void delete(int roomNumber) {
	    
	    try (Connection connection = DatabaseConnector.getConnection();
	         PreparedStatement selectStatement = connection.prepareStatement("SELECT * FROM room WHERE RoomNumber = ?")) {

	        selectStatement.setInt(1, roomNumber);
	        ResultSet resultSet = selectStatement.executeQuery();
	        if (resultSet.next()) {
	            try (PreparedStatement deleteStatement = connection.prepareStatement("DELETE FROM room WHERE RoomNumber = ?")) {

	                deleteStatement.setInt(1, roomNumber);
	                int rowsDeleted = deleteStatement.executeUpdate();

	                if (rowsDeleted > 0) {
	                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
	                    alert.setTitle("Success");
	                    alert.setHeaderText(null);
	                    alert.setContentText("Room deleted successfully!");
	                    alert.showAndWait();
	                } else {
	                    Alert alert = new Alert(Alert.AlertType.ERROR);
	                    alert.setTitle("Error");
	                    alert.setHeaderText(null);
	                    alert.setContentText("Failed to delete room. Please try again.");
	                    alert.showAndWait();
	                }

	            } catch (SQLException e) {
	                e.printStackTrace();
	            }
	        } else {
	            Alert alert = new Alert(Alert.AlertType.ERROR);
	            alert.setTitle("Error");
	            alert.setHeaderText(null);
	            alert.setContentText("Please enter a valid room number.");
	            alert.showAndWait();
	        }

	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}


	public List<Room> list() {
        List<Room> roomList = new ArrayList<>();

        try (Connection connection = DatabaseConnector.getConnection();
             PreparedStatement statement = connection.prepareStatement("SELECT * FROM room");
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                int roomNumber = resultSet.getInt("RoomNumber");
                String roomType = resultSet.getString("Type");
                int roomAvailability = resultSet.getInt("Availability");

                roomList.add(new Room(roomNumber, roomType, roomAvailability));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return roomList;
    }

}
