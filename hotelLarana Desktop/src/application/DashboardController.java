package application;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import com.hotel.controllers.CustomerService;
import com.hotel.controllers.EmployeeService;
import com.hotel.controllers.ReservationService;
import com.hotel.controllers.RoomService;
import com.hotel.controllers.UserService;
import com.hotel.db.DatabaseConnector;
import com.hotel.models.Customer;
import com.hotel.models.Employee;
import com.hotel.models.Reservation;
import com.hotel.models.Room;
import com.hotel.models.User;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;

import javafx.scene.chart.XYChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;

public class DashboardController {

    @FXML
    private HBox signout;

    @FXML
    private Label Available;


    @FXML
    private Label Booked;
    @FXML
    private Button overviewbtn;

    @FXML
    private AnchorPane overviewform;
    @FXML
    private Button reservationbtn;

    @FXML
    private AnchorPane reservationform;
    @FXML
    private Button usersbtn;

    @FXML
    private AnchorPane usersform;
    @FXML
    private Button employeesbtn;

    @FXML
    private AnchorPane employeesform;
    @FXML
    private Button customersbtn;

    @FXML
    private AnchorPane customersform;

    @FXML
    private TableView<Room> Roomtableview;
    @FXML
    private TableColumn<?, ?> RoomNumberColumn;
    @FXML
    private TableColumn<?, ?> roomAvailabilityColumn;

    @FXML
    private TableColumn<?, ?> roomtypeColumn;

    @FXML
    private TableView<User> userTableView;

    @FXML
    private TableColumn<User, Integer> userIDColumn;
    @FXML
    private TableColumn<User, String> EMAILUSERColumn;

    @FXML
    private TableColumn<User, String> FirstNameuserColumn;
    @FXML
    private TableColumn<User, String> LastNameuserDColumn;

    @FXML
    private TableColumn<User, String> passwordColumn;

    @FXML
    private TableColumn<User, String> userTypeColumn;
    @FXML
    private TableView<Employee> employeesTableView;
    @FXML
    private TableColumn<Employee, Integer> EmployeeIDColumn;

    @FXML
    private TableColumn<Employee, String> EmployeefirstNameColumn;

    @FXML
    private TableColumn<Employee, String> EmployeelastNameColumn;

    @FXML
    private TableColumn<Employee, String> EmployeepositionColumn;
    @FXML
    private TableView<Customer> tableviewCustomer;

    @FXML
    private TableColumn<Customer, Integer> CustomidColumn;

    @FXML
    private TableColumn<Customer, String> CustomerFNcol;

    @FXML
    private TableColumn<Customer, String> CustomerLNcol;

    @FXML
    private TableColumn<Customer, String> CustomerEMcol;

    @FXML
    private TableColumn<Customer, Integer> CustomerPHcol;
    
    @FXML
    private TextField employeeID;

    @FXML
    private TextField employeefirstName;

    @FXML
    private TextField employeelastName;
    @FXML
    private TextField userlastname;
    @FXML
    private TextField userfirstname;

    @FXML
    private TextField employeeposition;
    @FXML
    private TextField userPassword;
    @FXML
    private TextField userUserName;


    @FXML
    private ChoiceBox<String> userUserType;
    
    @FXML
    private ChoiceBox<Integer> RoomAvailability;

    @FXML
    private AnchorPane availabilityform;
    @FXML
    private AnchorPane manageReservationform;
    @FXML
    private TableView<Reservation> managereservationtable;

    @FXML
    private TableColumn<Reservation, Date> roomCheckinColumn;

    @FXML
    private TableColumn<Reservation, Date> roomCheckoutColumn;

    @FXML
    private TableColumn<Reservation, Integer> ReservationidColumn;
    
    
    @FXML
    private TableColumn<Reservation, Integer> ROOMTYPEColumn;
    @FXML
    private TableColumn<Reservation, Integer> NumberOFguestColumn;
    @FXML
    private TableColumn<Reservation, Integer> RoomnumberColumn;
    @FXML
    private TableColumn<Reservation, Integer> EmailColumn;
    
    @FXML
    private TableColumn<Reservation, Integer> lastnameColumn;
    @FXML
    private TableColumn<Reservation, Integer> firstnameColumn;
    

    @FXML
    private TextField reservecustomeremail;
    @FXML
    private TextField reservecustomerlastname;
    @FXML
    private TextField reservecustomerfirstname;
    @FXML
    private TextField NumberGuest;
    @FXML
    private TextField ROOMTYPE;
    
    
    @FXML
    private TextField reservecustomerid;
    @FXML
    private TextField reserveroomnumber;
    @FXML
    private DatePicker reservecheckin;
    @FXML
    private DatePicker reservecheckout;

    @FXML
    private TextField customeremail;

    @FXML
    private TextField customerfirstname;

    @FXML
    private TextField customerlastname;

    @FXML
    private TextField customerphone;
    @FXML
    private TextField Roomtype;

    @FXML
    private TextField Roomumber;
    @FXML
    private Button availablroombtn; 
    @FXML
    private Button manageresbtn;
    @FXML
    private TextField searchCustomer;
    @FXML
    private BarChart<String, Number> barChart;


    private ReservationService reservationService = new ReservationService();
    private UserService userService = new UserService();
    private EmployeeService employeeService = new EmployeeService();
    private CustomerService customerService = new CustomerService();
    private RoomService roomService = new RoomService();
    

    @FXML
    public void initialize() {
    	loadBarChart();
        initializeRoomCounts();
        initializeRoomTableView();
        toggleOverviewForm();
        initializeUsersTableView();
        initializeEmployeesTableView();
        initializeCustomersTableView();
        initializeReservationTableView();
        userUserType.getItems().addAll("admin", "user");
        RoomAvailability.getItems().addAll(0, 1);
        availabilityform.setVisible(true);
        tableviewCustomer.setOnMouseClicked(event -> {
            if (event.getClickCount() == 1) {
                Customer selectedCustomer = tableviewCustomer.getSelectionModel().getSelectedItem();
                if (selectedCustomer != null) {
                	customerfirstname.setText(selectedCustomer.getFirstName());
                	customerlastname.setText(selectedCustomer.getLastName());
                	customeremail.setText(selectedCustomer.getEmail());
                	customerphone.setText(selectedCustomer.getPhone());
                }
            }
        });
        employeesTableView.setOnMouseClicked(event -> {
            if (event.getClickCount() == 1) {
                Employee selectedEmployee = employeesTableView.getSelectionModel().getSelectedItem();
                if (selectedEmployee != null) {
                    employeefirstName.setText(selectedEmployee.getFirstName());
                    employeelastName.setText(selectedEmployee.getLastName());
                    employeeposition.setText(selectedEmployee.getPosition());
                }
            }
        });
        userTableView.setOnMouseClicked(event -> {
            if (event.getClickCount() == 1) {
                User selectedUser = userTableView.getSelectionModel().getSelectedItem();
                if (selectedUser != null) {
                    
                	userfirstname.setText(selectedUser.getFirstname());
                	userlastname.setText(selectedUser.getLastname());
                    userUserName.setText(selectedUser.getEmail());
                    userPassword.setText(selectedUser.getPassword());
                    userUserType.setValue(selectedUser.getUserType());
                }
            }
        });
        Roomtableview.setOnMouseClicked(event -> {
            if (event.getClickCount() == 1) {
                Room selectedRoom = Roomtableview.getSelectionModel().getSelectedItem();
                if (selectedRoom != null) {
                	Roomumber.setText(String.valueOf(selectedRoom.getRoomNumber())); 
                	Roomtype.setText(selectedRoom.getRoomType()); 
                	RoomAvailability.setValue(selectedRoom.getRoomAvailability());
                }
            }
        });
        managereservationtable.setOnMouseClicked(event -> {
            if (event.getClickCount() == 1) {
                Reservation selectedReservation = managereservationtable.getSelectionModel().getSelectedItem();
                if (selectedReservation != null) {
                	reservecustomerfirstname.setText(String.valueOf(selectedReservation.getFirstname()));
                	reservecustomerlastname.setText(String.valueOf(selectedReservation.getLastname())); 
                	reservecustomeremail.setText(String.valueOf(selectedReservation.getEmail())); 
                	reserveroomnumber.setText(String.valueOf(selectedReservation.getNumber()));
                	reservecheckin.setValue(((java.sql.Date) selectedReservation.getCheckInDate()).toLocalDate()); 
                	reservecheckout.setValue(((java.sql.Date) selectedReservation.getCheckOutDate()).toLocalDate());
                	NumberGuest.setText(String.valueOf(selectedReservation.getNumberOfGuests())); 
                	ROOMTYPE.setText(String.valueOf(selectedReservation.getRoomType()));
                }
            }
        });
    }



    @FXML
    private void createReservation() {
        try {
            String firstName = reservecustomerfirstname.getText();
            String lastName = reservecustomerlastname.getText();
            String email = reservecustomeremail.getText();
            String roomType = ROOMTYPE.getText();
            
            if (firstName.isEmpty() || lastName.isEmpty() || email.isEmpty() || roomType.isEmpty() ||
                    reserveroomnumber.getText().isEmpty() || reservecheckin.getValue() == null ||
                    reservecheckout.getValue() == null || NumberGuest.getText().isEmpty()) {

                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText(null);
                alert.setContentText("Please fill in all fields.");
                alert.showAndWait();
            } else {
                int reservationId = Integer.parseInt(reservecustomerid.getText());
                int number = Integer.parseInt(reserveroomnumber.getText());
                Date checkinDate = java.sql.Date.valueOf(reservecheckin.getValue());
                Date checkoutDate = java.sql.Date.valueOf(reservecheckout.getValue());
                int numberOfGuests = Integer.parseInt(NumberGuest.getText());

                Reservation reservation = new Reservation(reservationId, firstName, lastName, email, number, checkinDate, checkoutDate, numberOfGuests, roomType);

                reservationService.create(reservation);

                Alert successAlert = new Alert(Alert.AlertType.INFORMATION);
                successAlert.setTitle("Add Reservation");
                successAlert.setHeaderText(null);
                successAlert.setContentText("Reservation added successfully!");
                successAlert.showAndWait();

                managereservationtable.getItems().clear();
                initializeReservationTableView();
                reservecustomerfirstname.clear();
                reservecustomerlastname.clear();
                reservecustomeremail.clear();
                reserveroomnumber.clear();
                reservecheckin.getEditor().clear();
                reservecheckout.getEditor().clear();
                NumberGuest.clear();
                ROOMTYPE.clear();
            }
        } catch (NumberFormatException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Please enter valid numeric values.");
            alert.showAndWait();
        }
    }



    
    @FXML
    private void deleteReservation() {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Delete Reservation");
        dialog.setHeaderText("Enter the ID to delete:");
        dialog.setContentText("ID:");

        Optional<String> result = dialog.showAndWait();
        if (result.isPresent()) {
            String id = result.get();
            try {
                int reservationId = Integer.parseInt(id);
                
                reservationService.delete(reservationId);
                managereservationtable.getItems().clear();
                initializeReservationTableView();
            } catch (NumberFormatException e) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText(null);
                alert.setContentText("Please enter a valid ID.");
                alert.showAndWait();
            }
        }
    }
    
    @FXML
    private void updatereservation() {
        Reservation selectedReservation = managereservationtable.getSelectionModel().getSelectedItem(); 
        if (selectedReservation != null) {

        	String firstName = reservecustomerfirstname.getText();
            String lastName = reservecustomerlastname.getText();
            String email = reservecustomeremail.getText();
            int number = Integer.parseInt(reserveroomnumber.getText());
            Date checkinDate = java.sql.Date.valueOf(reservecheckin.getValue());
            Date checkoutDate = java.sql.Date.valueOf(reservecheckout.getValue());
            int numberOfGuests = Integer.parseInt(NumberGuest.getText());
            String roomType = ROOMTYPE.getText();

            if (!selectedReservation.getFirstname().equals(firstName)  || !selectedReservation.getLastname().equals(lastName)  || !selectedReservation.getEmail().equals(email) ||
            		selectedReservation.getNumber() != number || !selectedReservation.getCheckInDate().equals(checkinDate) || !selectedReservation.getCheckOutDate().equals(checkoutDate) 
            		|| selectedReservation.getNumberOfGuests() != numberOfGuests || !selectedReservation.getRoomType().equals(roomType)  ) {

                selectedReservation.setFirstname(firstName);
                selectedReservation.setLastname(lastName);
                selectedReservation.setEmail(email);
                selectedReservation.setNumber(number);
                selectedReservation.setCheckInDate(checkinDate);
                selectedReservation.setCheckOutDate(checkoutDate);
                selectedReservation.setNumberOfGuests(numberOfGuests);
                selectedReservation.setRoomType(roomType);

                reservationService.update(selectedReservation);
                managereservationtable.getItems().clear();
                initializeReservationTableView();
                
                reservecustomerfirstname.clear();
                reservecustomerlastname.clear();
                reservecustomeremail.clear();
                reserveroomnumber.clear();
                reservecheckin.getEditor().clear();
                reservecheckout.getEditor().clear();
                NumberGuest.clear();
                ROOMTYPE.clear();
            } else {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Update Reservation");
                alert.setHeaderText(null);
                alert.setContentText("Please modify the fields to update.");
                alert.showAndWait();
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Update Reservation");
            alert.setHeaderText(null);
            alert.setContentText("Please select a reservation to update.");
            alert.showAndWait();
        }
    }

    
    
    @FXML
    private void addUser() {
        if (userUserName.getText().isEmpty() || userPassword.getText().isEmpty() || userUserType.getValue() == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Please fill in all fields.");
            alert.showAndWait();
        } else {
            
            String firstname = userfirstname.getText();
            String lastname = userlastname.getText();
            String email = userUserName.getText();
            String password = userPassword.getText();
            String usertype = userUserType.getValue(); 


            User newUser = new User(firstname,lastname,email, password, usertype);

            userService.create(newUser);

            Alert successAlert = new Alert(Alert.AlertType.INFORMATION);
            successAlert.setTitle("Add User");
            successAlert.setHeaderText(null);
            successAlert.setContentText("User added successfully!");
            successAlert.showAndWait();

            userTableView.getItems().clear();
            initializeUsersTableView();
            userfirstname.clear();
            userlastname.clear();
            userUserName.clear();
            userPassword.clear();
            userUserType.setValue(null);
        }
    }

    @FXML
    private void deleteUser() {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Delete User");
        dialog.setHeaderText("Enter User ID to delete:");
        dialog.setContentText("ID:");

        Optional<String> result = dialog.showAndWait();
        if (result.isPresent()) {
            String id = result.get();
            try {
                int UserId = Integer.parseInt(id);
                
                userService.delete(UserId);
                userTableView.getItems().clear();
                initializeUsersTableView();
            } catch (NumberFormatException e) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText(null);
                alert.setContentText("Please enter a valid ID.");
                alert.showAndWait();
            }
        }
    }
    
    @FXML
    private void addEmployee() {
        if (employeefirstName.getText().isEmpty() || employeelastName.getText().isEmpty() || employeeposition.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Please fill in all fields.");
            alert.showAndWait();
        } else {
            String firstName = employeefirstName.getText();
            String lastName = employeelastName.getText();
            String position = employeeposition.getText(); 

            Employee newemployee = new Employee(firstName, lastName, position);

            employeeService.create(newemployee); 

            Alert successAlert = new Alert(Alert.AlertType.INFORMATION);
            successAlert.setTitle("Add Employee");
            successAlert.setHeaderText(null);
            successAlert.setContentText("Employee added successfully!");
            successAlert.showAndWait();

            employeesTableView.getItems().clear();
            initializeEmployeesTableView();

            employeefirstName.clear();
            employeelastName.clear();
            employeeposition.clear(); 
        }
    }
    
    @FXML
    private void deleteEmployee() {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Delete Employee");
        dialog.setHeaderText("Enter Employee ID to delete:");
        dialog.setContentText("ID:");

        Optional<String> result = dialog.showAndWait();
        if (result.isPresent()) {
            String id = result.get();
            try {
                int employeeId = Integer.parseInt(id);
                
                employeeService.delete(employeeId);
                employeesTableView.getItems().clear();
                initializeEmployeesTableView();
            } catch (NumberFormatException e) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText(null);
                alert.setContentText("Please enter a valid ID.");
                alert.showAndWait();
            }
        }
    }
    
    @FXML
    private void addCustomer() {
        if (customerfirstname.getText().isEmpty() || customerlastname.getText().isEmpty() || customeremail.getText().isEmpty() || customerphone.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Please fill in all fields.");
            alert.showAndWait();
        } else {
            String firstName = customerfirstname.getText();
            String lastName = customerlastname.getText();
            String email = customeremail.getText(); 
            String phone = customerphone.getText(); 

            Customer newcustomer = new Customer(firstName, lastName, email,phone);

            customerService.create(newcustomer); 

            Alert successAlert = new Alert(Alert.AlertType.INFORMATION);
            successAlert.setTitle("Add Customer");
            successAlert.setHeaderText(null);
            successAlert.setContentText("Customer added successfully!");
            successAlert.showAndWait();

            tableviewCustomer.getItems().clear();
            initializeCustomersTableView();

            customerfirstname.clear();
            customerlastname.clear();
            customeremail.clear(); 
            customerphone.clear(); 
        }
    }
    
    @FXML
    private void deleteCustomer() {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Delete Customer");
        dialog.setHeaderText("Enter Customer ID to delete:");
        dialog.setContentText("ID:");

        Optional<String> result = dialog.showAndWait();
        if (result.isPresent()) {
            String id = result.get();
            try {
                int CustomerId = Integer.parseInt(id);
                
                customerService.delete(CustomerId);
                tableviewCustomer.getItems().clear();
                initializeCustomersTableView();
            } catch (NumberFormatException e) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText(null);
                alert.setContentText("Please enter a valid ID.");
                alert.showAndWait();
            }
        }
    }
    
    @FXML
    private void updateCustomer() {
        Customer selectedCustomer = tableviewCustomer.getSelectionModel().getSelectedItem(); 
        if (selectedCustomer != null) {
            
            String newFirstName = customerfirstname.getText();
            String newLastName = customerlastname.getText();
            String newEmail = customeremail.getText();
            String newPhone = customerphone.getText();
            if (!selectedCustomer.getFirstName().equals(newFirstName) || !selectedCustomer.getLastName().equals(newLastName) || !selectedCustomer.getEmail().equals(newEmail) || !selectedCustomer.getPhone().equals(newPhone)) {

                selectedCustomer.setFirstName(newFirstName);
                selectedCustomer.setLastName(newLastName);
                selectedCustomer.setEmail(newEmail);
                selectedCustomer.setPhone(newPhone);

                customerService.update(selectedCustomer);
                tableviewCustomer.getItems().clear();
                initializeCustomersTableView();

                customerfirstname.clear();
                customerlastname.clear();
                customeremail.clear();
                customerphone.clear();
            } else {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Update Customer");
                alert.setHeaderText(null);
                alert.setContentText("Please modify the fields to update.");
                alert.showAndWait();
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Update Customer");
            alert.setHeaderText(null);
            alert.setContentText("Please select a customer to update.");
            alert.showAndWait();
        }
    }

    
    @FXML
    private void updateEmployee() {
        Employee selectedEmployee = employeesTableView.getSelectionModel().getSelectedItem();
        if (selectedEmployee != null) {
            String newFirstName = employeefirstName.getText();
            String newLastName = employeelastName.getText();
            String newPosition = employeeposition.getText();

            if (!newFirstName.equals(selectedEmployee.getFirstName()) || !newLastName.equals(selectedEmployee.getLastName()) || !newPosition.equals(selectedEmployee.getPosition())) {
                selectedEmployee.setFirstName(newFirstName);
                selectedEmployee.setLastName(newLastName);
                selectedEmployee.setPosition(newPosition);

                employeeService.update(selectedEmployee);
                employeesTableView.getItems().clear();
                initializeEmployeesTableView();
                employeefirstName.clear();
                employeelastName.clear();
                employeeposition.clear();
            } else {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Update Employee");
                alert.setHeaderText(null);
                alert.setContentText("Please modify the fields to update.");
                alert.showAndWait();
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Update Employee");
            alert.setHeaderText(null);
            alert.setContentText("Please select an employee to update.");
            alert.showAndWait();
        }
    }

    @FXML
    private void updateUser() {
        User selectedUser = userTableView.getSelectionModel().getSelectedItem(); 
        if (selectedUser != null) {
            String newemail = userUserName.getText();
            String newPassword = userPassword.getText();
            String newUserType = userUserType.getValue();

            
            if (!selectedUser.getEmail().equals(newemail) || !selectedUser.getPassword().equals(newPassword) || !selectedUser.getUserType().equals(newUserType)) {

                selectedUser.setEmail(newemail);
                selectedUser.setPassword(newPassword);
                selectedUser.setUserType(newUserType);

                userService.update(selectedUser);
                userTableView.getItems().clear();
                initializeUsersTableView();
                userfirstname.clear();
                userlastname.clear();
                userUserName.clear();
                userPassword.clear();
                userUserType.setValue(null);
            } else {
                
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Update User");
                alert.setHeaderText(null);
                alert.setContentText("Please modify the fields to update.");
                alert.showAndWait();
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Update User");
            alert.setHeaderText(null);
            alert.setContentText("Please select a user to update.");
            alert.showAndWait();
        }
    }
    @FXML
    private void UpdateRoom() {
        Room selectedRoom = Roomtableview.getSelectionModel().getSelectedItem();
        if (selectedRoom != null) {

        	int newRoomNumber = Integer.parseInt(Roomumber.getText());
            String newRoomType = Roomtype.getText();
            int newRoomAvailability = RoomAvailability.getValue();

            if (newRoomNumber != selectedRoom.getRoomNumber() || !selectedRoom.getRoomType().equals(newRoomType) || newRoomAvailability != selectedRoom.getRoomAvailability()) {
                selectedRoom.setRoomNumber(newRoomNumber);
                selectedRoom.setRoomType(newRoomType);
                selectedRoom.setRoomAvailability(newRoomAvailability);

                roomService.update(selectedRoom); 
                Roomtableview.getItems().clear(); 
                initializeRoomTableView(); 
                Roomumber.clear();
                Roomtype.clear();
                RoomAvailability.setValue(null);
            } else {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Update Room");
                alert.setHeaderText(null);
                alert.setContentText("Please modify the fields to update.");
                alert.showAndWait();
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Update Room");
            alert.setHeaderText(null);
            alert.setContentText("Please select a room to update.");
            alert.showAndWait();
        }
    }

    @FXML
    private void AddRoom() {
        try {
            int roomNumber = Integer.parseInt(Roomumber.getText());
            String roomType = Roomtype.getText();
            int roomAvailability = RoomAvailability.getValue();

            	
                Room newRoom = new Room(roomNumber, roomType, roomAvailability);
                roomService.create(newRoom);

                Alert successAlert = new Alert(Alert.AlertType.INFORMATION);
                successAlert.setTitle("Add Room");
                successAlert.setHeaderText(null);
                successAlert.setContentText("Room added successfully!");
                successAlert.showAndWait();

                Roomtableview.getItems().clear();
                initializeRoomTableView();
                Roomumber.clear();
                Roomtype.clear();
                RoomAvailability.setValue(null);
            
        } catch (NumberFormatException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Please fill in all fields correctly.");
            alert.showAndWait();
        }
    }



    
    @FXML
    private void DeleteRoom() {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Delete Room");
        dialog.setHeaderText("Enter Room Number to delete:");
        dialog.setContentText("Room Number:");

        Optional<String> result = dialog.showAndWait();
        if (result.isPresent()) {
            String roomNumberInput = result.get();
            try {
                int roomNumber = Integer.parseInt(roomNumberInput);
                
                roomService.delete(roomNumber);
                Roomtableview.getItems().clear(); 
                initializeRoomTableView(); 
            } catch (NumberFormatException e) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText(null);
                alert.setContentText("Please enter a valid room number.");
                alert.showAndWait();
            }
        }
    }


    
    public void uploadCSV() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select a CSV file ");
        fileChooser.getExtensionFilters().add(new ExtensionFilter("Fichiers CSV", "*.csv"));
        File selectedFile = fileChooser.showOpenDialog(null);

        if (selectedFile != null) {
            if (selectedFile.getName().toLowerCase().endsWith(".csv")) {
                try (BufferedReader br = new BufferedReader(new FileReader(selectedFile))) {
                    String line;
                    while ((line = br.readLine()) != null) {
                        

                        String[] data = line.split(",");

                        if (data.length == 3) {
                        	Employee employee = new Employee(data[0], data[1], data[2]);
                            
                        	try {
                                Connection connection = DatabaseConnector.getConnection();

                                String query = "INSERT INTO employee (FirstName, LastName, Position) VALUES (?, ?, ?)";
                                PreparedStatement statement = connection.prepareStatement(query);
                                statement.setString(1, employee.getFirstName());
                                statement.setString(2, employee.getLastName());
                                statement.setString(3, employee.getPosition());

                                int rowsInserted = statement.executeUpdate();
                                if (rowsInserted > 0) {
                                    Alert successAlert = new Alert(Alert.AlertType.INFORMATION);
                                    successAlert.setTitle("Import Data");
                                    successAlert.setHeaderText(null);
                                    successAlert.setContentText("Data inserted successfully ");
                                    successAlert.showAndWait();
                                }

                                statement.close();
                                connection.close();
                            } catch (SQLException e) {
                                e.printStackTrace();
                            }
                            employeesTableView.getItems().clear();
                            initializeEmployeesTableView();
                        } else {
                            Alert alert = new Alert(AlertType.ERROR);
                            alert.setTitle("Import data");
                            alert.setHeaderText(null);
                            alert.setContentText("Erreur while Importing data ");
                            alert.showAndWait();
                        }
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
                
            }
        } else {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Import data");
            alert.setHeaderText(null);
            alert.setContentText("No files selected.");
            alert.showAndWait();
            
        }
    }
    
    public void exportCSV() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Save CSV file ");
        fileChooser.getExtensionFilters().add(new ExtensionFilter("Fichiers CSV", "*.csv"));
        File selectedFile = fileChooser.showSaveDialog(null);

        if (selectedFile != null) {
            try (PrintWriter writer = new PrintWriter(selectedFile)) {
                Connection connection = DatabaseConnector.getConnection();
                PreparedStatement statement = connection.prepareStatement("SELECT * FROM employee");
                ResultSet resultSet = statement.executeQuery();
                while (resultSet.next()) {
                    String firstName = resultSet.getString("FirstName");
                    String lastName = resultSet.getString("LastName");
                    String position = resultSet.getString("Position");
                    writer.println(firstName + "," + lastName + "," + position);
                }

                
                resultSet.close();
                statement.close();
                connection.close();

                Alert successAlert = new Alert(Alert.AlertType.INFORMATION);
                successAlert.setTitle("Export Data");
                successAlert.setHeaderText(null);
                successAlert.setContentText("Data exported successfully ");
                successAlert.showAndWait();
            } catch (SQLException | FileNotFoundException e) {
                e.printStackTrace();
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Export Data");
            alert.setHeaderText(null);
            alert.setContentText("No files selected. ");
            alert.showAndWait();
        }
    }

    
    private void initializeRoomCounts() {
        try {
            Connection connection = DatabaseConnector.getConnection();

            String sql = "SELECT Availability FROM room";
            PreparedStatement statement = connection.prepareStatement(sql);

            ResultSet resultSet = statement.executeQuery();

            int countAvailable = 0;
            int countBooked = 0;

            while (resultSet.next()) {
                int availability = resultSet.getInt("Availability");

                switch (availability) {
                    case 0:
                        countAvailable++;
                        break;
                    case 1:
                        countBooked++;
                        break;
                }
            }

            Available.setText(String.valueOf(countAvailable));
            Booked.setText(String.valueOf(countBooked));

            resultSet.close();
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void initializeReservationTableView() {
    	List<Reservation> reservationList = reservationService.list();
        if (reservationList != null) {
        	managereservationtable.getItems().addAll(reservationList);
        	ReservationidColumn.setCellValueFactory(new PropertyValueFactory<>("reservationID"));
        	firstnameColumn.setCellValueFactory(new PropertyValueFactory<>("firstname"));
        	lastnameColumn.setCellValueFactory(new PropertyValueFactory<>("lastname"));
        	EmailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));
        	RoomnumberColumn.setCellValueFactory(new PropertyValueFactory<>("number"));
        	roomCheckinColumn.setCellValueFactory(new PropertyValueFactory<>("checkInDate"));
        roomCheckoutColumn.setCellValueFactory(new PropertyValueFactory<>("checkOutDate"));
        NumberOFguestColumn.setCellValueFactory(new PropertyValueFactory<>("numberOfGuests"));
        ROOMTYPEColumn.setCellValueFactory(new PropertyValueFactory<>("roomType"));
        }
    }

    private void initializeUsersTableView() {
        List<User> userList = userService.list();

        if (userList != null) {
            userTableView.getItems().addAll(userList);

            userIDColumn.setCellValueFactory(new PropertyValueFactory<>("userID"));
            FirstNameuserColumn.setCellValueFactory(new PropertyValueFactory<>("firstname"));
            LastNameuserDColumn.setCellValueFactory(new PropertyValueFactory<>("lastname"));
            EMAILUSERColumn.setCellValueFactory(new PropertyValueFactory<>("email"));
            passwordColumn.setCellValueFactory(new PropertyValueFactory<>("password"));
            userTypeColumn.setCellValueFactory(new PropertyValueFactory<>("userType"));
        }
    }

    
    
    public void initializeCustomersTableView() {
        
        List<Customer> customerList = customerService.list();

        if (customerList != null) {
            tableviewCustomer.getItems().addAll(customerList);

            CustomidColumn.setCellValueFactory(new PropertyValueFactory<>("customerID"));
            CustomerFNcol.setCellValueFactory(new PropertyValueFactory<>("firstName"));
            CustomerLNcol.setCellValueFactory(new PropertyValueFactory<>("lastName"));
            CustomerEMcol.setCellValueFactory(new PropertyValueFactory<>("email"));
            CustomerPHcol.setCellValueFactory(new PropertyValueFactory<>("phone"));
        }
    }
    
    public void refreshTextfields(){
        Roomumber.clear();
        Roomtype.clear();
        RoomAvailability.setValue(null);
        customerfirstname.clear();
        customerlastname.clear();
        customeremail.clear(); 
        customerphone.clear();
        employeefirstName.clear();
        employeelastName.clear();
        employeeposition.clear(); 
    	userfirstname.clear();;
    	userlastname.clear();
        userUserName.clear();
        userPassword.clear();
        userUserType.setValue(null);
        reservecustomerfirstname.clear();
        reservecustomerlastname.clear();
        reservecustomeremail.clear();
        reserveroomnumber.clear();
        reservecheckin.getEditor().clear();
        reservecheckout.getEditor().clear();
        NumberGuest.clear();
        ROOMTYPE.clear();
    }

    
    private void initializeEmployeesTableView() {
        
        List<Employee> employeeList = employeeService.list();

        if (employeeList != null) {
            employeesTableView.getItems().addAll(employeeList);

            EmployeeIDColumn.setCellValueFactory(new PropertyValueFactory<>("employeeID"));
            EmployeefirstNameColumn.setCellValueFactory(new PropertyValueFactory<>("firstName"));
            EmployeelastNameColumn.setCellValueFactory(new PropertyValueFactory<>("lastName"));
            EmployeepositionColumn.setCellValueFactory(new PropertyValueFactory<>("position"));
        }
    }


    @FXML
    public void loadBarChart() {
        List<Employee> employees = employeeService.list();
        int employeeCount = employees.size();

        List<Customer> customers = customerService.list();
        int customerCount = customers.size();

        List<User> users = userService.list();
        int userCount = users.size();


        barChart.getYAxis().setLabel("Count");

        XYChart.Series<String, Number> series = new XYChart.Series<>();
        series.getData().add(new XYChart.Data<>("Employees", employeeCount));
        series.getData().add(new XYChart.Data<>("Customers", customerCount));
        series.getData().add(new XYChart.Data<>("Users", userCount));

        barChart.getData().add(series);
        barChart.setLegendVisible(false);
    }



    
    private void initializeRoomTableView() {
        List<Room> roomList = roomService.list();

        if (roomList != null) {
            Roomtableview.getItems().addAll(roomList);

            RoomNumberColumn.setCellValueFactory(new PropertyValueFactory<>("roomNumber"));
            roomtypeColumn.setCellValueFactory(new PropertyValueFactory<>("roomType"));
            roomAvailabilityColumn.setCellValueFactory(new PropertyValueFactory<>("roomAvailability"));
        }
    }




    @FXML
    public void signout() {
        
        Stage currentStage = (Stage) signout.getScene().getWindow();
        currentStage.hide();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/hotel/view/login.fxml"));
        Parent root;
        try {
            Image logo = new Image(getClass().getResourceAsStream("/com/hotel/view/logo.png"));
            root = loader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.getIcons().add(logo);	
            stage.setTitle("Login |");
            stage.setResizable(false);
            stage.centerOnScreen();
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    @FXML
    void manageReservation(ActionEvent event) {
    	availabilityform.setVisible(false);
    	manageReservationform.setVisible(true);
    	manageresbtn.setStyle("-fx-background-color: white;");
    	manageresbtn.setTextFill(javafx.scene.paint.Color.web("#58490f"));
    	availablroombtn.setStyle("-fx-background-color: #58490f;");
    	availablroombtn.setTextFill(javafx.scene.paint.Color.web("white"));
    }
    @FXML
    void manageAvailabilityRooms(ActionEvent event) {
    	availabilityform.setVisible(true);
    	manageReservationform.setVisible(false);
    	availablroombtn.setStyle("-fx-background-color: white;");
    	availablroombtn.setTextFill(javafx.scene.paint.Color.web("#58490f"));
    	manageresbtn.setStyle("-fx-background-color: #58490f;");
    	manageresbtn.setTextFill(javafx.scene.paint.Color.web("white"));
    }


    @FXML
    private void toggleOverviewForm() {
        resetButtonsAndForms();
        overviewform.setVisible(true);
        reservationform.setVisible(false);
        usersform.setVisible(false);
        employeesform.setVisible(false);
        customersform.setVisible(false);
        overviewbtn.setStyle("-fx-background-color: white;");
        overviewbtn.setTextFill(javafx.scene.paint.Color.web("#58490f"));
    }

    @FXML
    private void toggleReservationForm() {
    	resetButtonsAndForms();
        overviewform.setVisible(false);
        reservationform.setVisible(true);
        usersform.setVisible(false);
        employeesform.setVisible(false);
        customersform.setVisible(false);
        reservationbtn.setStyle("-fx-background-color: white;");
        reservationbtn.setTextFill(javafx.scene.paint.Color.web("#58490f"));
        overviewbtn.setStyle("-fx-background-color: #58490F;"); 
        overviewbtn.setTextFill(javafx.scene.paint.Color.WHITE); 
    }

    @FXML
    private void toggleUsersForm() {
    	resetButtonsAndForms();
        overviewform.setVisible(false);
        reservationform.setVisible(false);
        usersform.setVisible(true);
        employeesform.setVisible(false);
        customersform.setVisible(false);
        usersbtn.setStyle("-fx-background-color: white;");
        usersbtn.setTextFill(javafx.scene.paint.Color.web("#58490f"));
        overviewbtn.setStyle("-fx-background-color: #58490F;");
        overviewbtn.setTextFill(javafx.scene.paint.Color.WHITE);
    }
    @FXML
    private void toggleEmployeesForm() {
    	resetButtonsAndForms();
        overviewform.setVisible(false);
        reservationform.setVisible(false);
        usersform.setVisible(false);
        employeesform.setVisible(true);
        customersform.setVisible(false);
        employeesbtn.setStyle("-fx-background-color: white;");
        employeesbtn.setTextFill(javafx.scene.paint.Color.web("#58490f"));
        overviewbtn.setStyle("-fx-background-color: #58490F;"); 
        overviewbtn.setTextFill(javafx.scene.paint.Color.WHITE);
    }
    @FXML
    private void togglecustomersForm() {
    	resetButtonsAndForms();
        overviewform.setVisible(false);
        reservationform.setVisible(false);
        usersform.setVisible(false);
        employeesform.setVisible(false);
        customersform.setVisible(true);
        customersbtn.setStyle("-fx-background-color: white;");
        customersbtn.setTextFill(javafx.scene.paint.Color.web("#58490f"));
        overviewbtn.setStyle("-fx-background-color: #58490F;");
        overviewbtn.setTextFill(javafx.scene.paint.Color.WHITE);
    }
    private void resetButtonsAndForms() {
        overviewform.setVisible(false);
        reservationform.setVisible(false);
        usersform.setVisible(false);
        employeesform.setVisible(false);
        customersform.setVisible(false);

        
        reservationbtn.setStyle("-fx-background-color: #58490F;");
        usersbtn.setStyle("-fx-background-color: #58490F;");
        employeesbtn.setStyle("-fx-background-color: #58490F;");
        customersbtn.setStyle("-fx-background-color: #58490F;");

        
        reservationbtn.setTextFill(javafx.scene.paint.Color.web("WHITE"));
        usersbtn.setTextFill(javafx.scene.paint.Color.web("WHITE"));
        employeesbtn.setTextFill(javafx.scene.paint.Color.web("WHITE"));
        customersbtn.setTextFill(javafx.scene.paint.Color.web("WHITE"));
    }


}
