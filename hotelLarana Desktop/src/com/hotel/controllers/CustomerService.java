package com.hotel.controllers;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.hotel.db.DatabaseConnector;
import com.hotel.models.Customer;

import javafx.scene.control.Alert;

public class CustomerService implements CustomerDao{

    
    public void create(Customer customer) {
        try (Connection connection = DatabaseConnector.getConnection();
             PreparedStatement statement = connection.prepareStatement(
                     "INSERT INTO customer (FirstName, LastName, Email,Phone) VALUES (?, ?, ?, ?)")) {

            statement.setString(1, customer.getFirstName());
            statement.setString(2, customer.getLastName());
            statement.setString(3, customer.getEmail());
            statement.setString(4, customer.getPhone());

            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


	public void read(Customer customer) {
		// TODO Auto-generated method stub
		
	}

	
    public void update(Customer customer) {
        try (Connection connection = DatabaseConnector.getConnection();
             PreparedStatement statement = connection.prepareStatement(
                     "UPDATE customer SET FirstName=?, LastName=?, Email=?, Phone=? WHERE CustomerID=?")) {

            statement.setString(1, customer.getFirstName());
            statement.setString(2, customer.getLastName());
            statement.setString(3, customer.getEmail());
            statement.setString(4, customer.getPhone());
            statement.setInt(5, customer.getCustomerID());

            int rowsUpdated = statement.executeUpdate();

            if (rowsUpdated > 0) {
                
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Success");
                alert.setHeaderText(null);
                alert.setContentText("Customer updated successfully!");
                alert.showAndWait();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

	public void delete(int customerId) {
	    try (Connection connection = DatabaseConnector.getConnection();
	         PreparedStatement selectStatement = connection.prepareStatement("SELECT * FROM customer WHERE customerId = ?")) {

	        selectStatement.setInt(1, customerId);
	        ResultSet resultSet = selectStatement.executeQuery();
	        if (resultSet.next()) {
	            try (PreparedStatement deleteStatement = connection.prepareStatement("DELETE FROM customer WHERE customerId = ?")) {

	                deleteStatement.setInt(1, customerId);
	                int rowsDeleted = deleteStatement.executeUpdate();

	                if (rowsDeleted > 0) {
	                    
	                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
	                    alert.setTitle("Success");
	                    alert.setHeaderText(null);
	                    alert.setContentText("customer deleted successfully!");
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

	
    public List<Customer> list() {
        List<Customer> customerList = new ArrayList<>();

        try (Connection connection = DatabaseConnector.getConnection();
             PreparedStatement statement = connection.prepareStatement("SELECT * FROM customer");
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                int customerID = resultSet.getInt("CustomerID");
                String firstName = resultSet.getString("FirstName");
                String lastName = resultSet.getString("LastName");
                String email = resultSet.getString("Email");
                String phone = resultSet.getString("Phone");

                customerList.add(new Customer(customerID, firstName, lastName, email, phone));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return customerList;
    }

}
