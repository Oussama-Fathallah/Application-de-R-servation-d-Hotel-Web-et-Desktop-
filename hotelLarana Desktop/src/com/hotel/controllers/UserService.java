package com.hotel.controllers;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.hotel.db.DatabaseConnector;
import com.hotel.models.User;

import javafx.scene.control.Alert;

public class UserService implements UserDao{

	
	public void create(User user) {
	    try (Connection connection = DatabaseConnector.getConnection();
	         PreparedStatement statement = connection.prepareStatement("INSERT INTO user (FirstName,LastName,Email, Password, UserType) VALUES (?, ?,?, ?, ?)")) {

	    	statement.setString(1, user.getFirstname());
	    	statement.setString(2, user.getLastname());
	    	statement.setString(3, user.getEmail());
	        statement.setString(4, user.getPassword());
	        statement.setString(5, user.getUserType());

	        statement.executeUpdate();
	        

	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}


	
	public void read(User user) {
		// TODO Auto-generated method stub
		
	}

	public void update(User user) {
	    try (Connection connection = DatabaseConnector.getConnection();
	         PreparedStatement updateStatement = connection.prepareStatement("UPDATE user SET FirstName =?, LastName = ?, Email = ?, Password = ?, UserType = ? WHERE UserID = ?")) {

	    	updateStatement.setString(1, user.getFirstname());
	    	updateStatement.setString(2, user.getLastname());
	    	updateStatement.setString(3, user.getEmail());
	        updateStatement.setString(4, user.getPassword());
	        updateStatement.setString(5, user.getUserType());
	        updateStatement.setInt(6, user.getUserID()); 

	        int rowsUpdated = updateStatement.executeUpdate();

	        if (rowsUpdated > 0) {
	            
	            Alert alert = new Alert(Alert.AlertType.INFORMATION);
	            alert.setTitle("Success");
	            alert.setHeaderText(null);
	            alert.setContentText("User updated successfully!");
	            alert.showAndWait();
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}


	public void delete(int userId) {
	    try (Connection connection = DatabaseConnector.getConnection();
	         PreparedStatement selectStatement = connection.prepareStatement("SELECT * FROM user WHERE UserID = ?")) {

	        selectStatement.setInt(1, userId);
	        ResultSet resultSet = selectStatement.executeQuery();
	        if (resultSet.next()) {
	            try (PreparedStatement deleteStatement = connection.prepareStatement("DELETE FROM user WHERE UserID = ?")) {

	                deleteStatement.setInt(1, userId);
	                int rowsDeleted = deleteStatement.executeUpdate();

	                if (rowsDeleted > 0) {
	                    
	                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
	                    alert.setTitle("Success");
	                    alert.setHeaderText(null);
	                    alert.setContentText("User deleted successfully!");
	                    alert.showAndWait();
	                } else {
	                    Alert alert = new Alert(Alert.AlertType.ERROR);
	                    alert.setTitle("Error");
	                    alert.setHeaderText(null);
	                    alert.setContentText("Failed to delete user. Please try again.");
	                    alert.showAndWait();
	                }

	            } catch (SQLException e) {
	                e.printStackTrace();
	            }
	        } else {
	            Alert alert = new Alert(Alert.AlertType.ERROR);
	            alert.setTitle("Error");
	            alert.setHeaderText(null);
	            alert.setContentText("Please enter a valid user ID.");
	            alert.showAndWait();
	        }

	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}


	
	public List<User> list() {
	    List<User> userList = new ArrayList<>();

	    try (Connection connection = DatabaseConnector.getConnection();
	         PreparedStatement statement = connection.prepareStatement("SELECT * FROM user");
	         ResultSet resultSet = statement.executeQuery()) {

	        while (resultSet.next()) {
                int userid = resultSet.getInt("UserID");
                String firstname = resultSet.getString("FirstName");
                String lastname = resultSet.getString("LastName");
                String email = resultSet.getString("Email");
                String password = resultSet.getString("Password");
                String usertype = resultSet.getString("UserType");

	            userList.add(new User(userid,firstname,lastname,email,password,usertype ));
	        }

	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	    return userList;
	}


}
