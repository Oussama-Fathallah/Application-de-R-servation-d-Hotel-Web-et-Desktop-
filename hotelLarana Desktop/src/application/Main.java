package application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/hotel/view/login.fxml"));
            Parent root = loader.load();
            Image logo = new Image(getClass().getResourceAsStream("/com/hotel/view/logo.png"));
            primaryStage.getIcons().add(logo);
            primaryStage.setScene(new Scene(root, 400, 451));
            primaryStage.setTitle("Login |");
            primaryStage.setResizable(false);
            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}