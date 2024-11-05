package com.hotel.controllers;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.hotel.db.DatabaseConnector;
import com.hotel.models.Employee;

import javafx.scene.control.Alert;

public class EmployeeService implements EmployeeDao{

	public void create(Employee employee) {
	    try (Connection connection = DatabaseConnector.getConnection();
	         PreparedStatement statement = connection.prepareStatement("INSERT INTO employee (FirstName, LastName, Position) VALUES (?, ?, ?)")) {

	        statement.setString(1, employee.getFirstName());
	        statement.setString(2, employee.getLastName());
	        statement.setString(3, employee.getPosition());

	        statement.executeUpdate();


	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}

	
	public void read(Employee employee) {
		// TODO Auto-generated method stub
		
	}

	
	public void update(Employee employee) {
	    try (Connection connection = DatabaseConnector.getConnection();
	         PreparedStatement statement = connection.prepareStatement(
	                 "UPDATE employee SET FirstName = ?, LastName = ?, Position = ? WHERE employeeId = ?")) {

	        statement.setString(1, employee.getFirstName());
	        statement.setString(2, employee.getLastName());
	        statement.setString(3, employee.getPosition());
	        statement.setInt(4, employee.getEmployeeID()); 

            int rowsUpdated = statement.executeUpdate();

            if (rowsUpdated > 0) {
                
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Success");
                alert.setHeaderText(null);
                alert.setContentText("Employee updated successfully!");
                alert.showAndWait();
            }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}


	public void delete(int employeeId) {
	    try (Connection connection = DatabaseConnector.getConnection();
	         PreparedStatement selectStatement = connection.prepareStatement("SELECT * FROM employee WHERE employeeId = ?")) {

	        selectStatement.setInt(1, employeeId);
	        ResultSet resultSet = selectStatement.executeQuery();
	        if (resultSet.next()) {
	            try (PreparedStatement deleteStatement = connection.prepareStatement("DELETE FROM employee WHERE employeeId = ?")) {

	                deleteStatement.setInt(1, employeeId);
	                int rowsDeleted = deleteStatement.executeUpdate();

	                if (rowsDeleted > 0) {
	                    
	                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
	                    alert.setTitle("Success");
	                    alert.setHeaderText(null);
	                    alert.setContentText("employees deleted successfully!");
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

	 public List<Employee> list() {
	        List<Employee> employeeList = new ArrayList<>();

	        try (Connection connection = DatabaseConnector.getConnection();
	             PreparedStatement statement = connection.prepareStatement("SELECT * FROM employee");
	             ResultSet resultSet = statement.executeQuery()) {

	            while (resultSet.next()) {
	                int employeeID = resultSet.getInt("EmployeeID");
	                String firstName = resultSet.getString("FirstName");
	                String lastName = resultSet.getString("LastName");
	                String position = resultSet.getString("Position");

	                employeeList.add(new Employee(employeeID, firstName, lastName, position));
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }

	        return employeeList;
	    }

}
