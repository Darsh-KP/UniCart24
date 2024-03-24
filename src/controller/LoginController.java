package controller;

import app.Shoprite;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

import java.io.File;
import java.io.IOException;


public class LoginController {
    @FXML
    private ImageView iconDisplay, logoDisplay, userIcon, passIcon;

    public void start() {
        iconDisplay.setImage(new Image(new File("data/GroceryIcon.png").toURI().toString()));
        logoDisplay.setImage(new Image(new File("data/logo.png").toURI().toString()));
        userIcon.setImage(new Image(new File("data/profileIcon.png").toURI().toString()));
        passIcon.setImage(new Image(new File("data/passwordicon.png").toURI().toString()));
    }

    public void loginAttempt() throws IOException {
        // Set up FXML loader
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/view/Home.fxml"));
        AnchorPane root = loader.load();

        // Initialize page
        //HomeController controller = loader.getController();
        //controller.start();

        // Show the page
        Shoprite.currentStage.setScene(new Scene(root, 1280, 720));
        Shoprite.currentStage.show();
    }
}
