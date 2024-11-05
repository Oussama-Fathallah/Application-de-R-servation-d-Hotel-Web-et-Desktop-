package application;

import java.awt.Image;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

import com.hotel.db.DatabaseConnector;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class LoginController {

    @FXML
    private TextField emailField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Button signInButton;

    @FXML
    private Label errorLabel;
    
    Connection con;
    PreparedStatement pat;
    ResultSet res;

    @FXML
    public void signIn(ActionEvent event) throws IOException {
        String uname = emailField.getText();
        String pass = passwordField.getText();
        
        if (uname.equals("") || pass.equals("")) {
            JOptionPane.showMessageDialog(null, "Please fill in all fields.");
        } else {
            try { 
                Class.forName("com.mysql.cj.jdbc.Driver");
                con = DatabaseConnector.getConnection();
                pat = con.prepareStatement("select * from user where Email=? and Password=?");
                pat.setString(1, uname);
                pat.setString(2, pass);
                res = pat.executeQuery();

                if (res.next()) {
                    String userType = res.getString("UserType");

                    if ("admin".equals(userType)) {
                        
                    	ImageIcon originalIcon = new ImageIcon(getClass().getResource("/com/hotel/view/valid.png"));
                        Image originalImage = originalIcon.getImage();
                        Image resizedImage = originalImage.getScaledInstance(30, 35, 0);
                        ImageIcon resizedIcon = new ImageIcon(resizedImage);
                        int option = JOptionPane.showOptionDialog(null,"Login Success.","Message",JOptionPane.DEFAULT_OPTION,JOptionPane.INFORMATION_MESSAGE,resizedIcon,new Object[]{"OK"},"OK");
                        if (option == JOptionPane.OK_OPTION) {
                            Parent root = FXMLLoader.load(getClass().getResource("/com/hotel/view/dashboard.fxml"));
                            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                            Scene scene = new Scene(root);
                            stage.setScene(scene);
                            stage.setResizable(false);
                            stage.setTitle("Dashboard |");
                            stage.centerOnScreen();
                            stage.show();
                        }
                    } else {
                        
                        JOptionPane.showMessageDialog(null, "Only administrators can log in !", "Erreur", JOptionPane.WARNING_MESSAGE);
                        emailField.setText("");
                        passwordField.setText("");
                        emailField.requestFocus();
                    }
                } else {
                    
                    JOptionPane.showMessageDialog(null, "Username or password is incorrect", "Erreur", JOptionPane.ERROR_MESSAGE);
                    emailField.setText("");
                    passwordField.setText("");
                    emailField.requestFocus();
                }
            } catch (ClassNotFoundException | SQLException e) {
                e.printStackTrace();
            }
        }
    }

}
