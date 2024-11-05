package com.example.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.example.model.User;

public class UserDao {
	
	public int registerUser(User user) throws ClassNotFoundException {
		String INSERT_USER_SQL = "INSERT INTO USER" + "(FirstName, LastName, Email, Password, UserType)"
				+ " VALUES " + "(?,?,?,?,?);";
		int result = 0;
		
		Class.forName("com.mysql.jdbc.Driver");
		try (Connection connection = DriverManager
				.getConnection("jdbc:mysql://localhost:3306/larana_hotel?useSSL=false","root","root");
				PreparedStatement ps = connection.prepareStatement(INSERT_USER_SQL)){
			ps.setString(1, user.getFirstName());
			ps.setString(2, user.getLastName());
			ps.setString(3, user.getEmail());
			ps.setString(4, user.getPassword());
			ps.setString(5, "user");
			System.out.println(ps);
			
			result = ps.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	return result;
	}
	public boolean validateUser(User user) throws ClassNotFoundException{
		Class.forName("com.mysql.jdbc.Driver");
		String query = "SELECT * FROM USER WHERE Email = ? AND Password = ?";
		
		
		try (Connection connection = DriverManager
				.getConnection("jdbc:mysql://localhost:3306/larana_hotel?useSSL=false","root","root");
				PreparedStatement ps = connection.prepareStatement(query)) {
			ps.setString(1, user.getEmail());
			ps.setString(2, user.getPassword());

			try (ResultSet resultSet = ps.executeQuery()) {
				return resultSet.next(); // Returns true if a matching user is found
			}
		} catch (Exception e) {
			e.printStackTrace(); // Log the exception or handle it appropriately
		}
		
		return false; // Return false if an exception occurs
	}
}
