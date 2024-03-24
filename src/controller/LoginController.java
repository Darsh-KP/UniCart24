package controller;

import app.UniCart;
import database.Storage;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import model.User;

import java.io.File;
import java.io.IOException;


public class LoginController {
    @FXML
    private TextField usernameInput;
    @FXML
    private PasswordField passwordInput;
    @FXML
    private Text errorDisplay;
    @FXML
    private ImageView iconDisplay, logoDisplay, userIcon, passIcon;

    public void start() {
        // Set up all the images on the log-in screen
        iconDisplay.setImage(new Image(new File("data/GroceryIcon.png").toURI().toString()));
        logoDisplay.setImage(new Image(new File("data/logo.png").toURI().toString()));
        userIcon.setImage(new Image(new File("data/profileIcon.png").toURI().toString()));
        passIcon.setImage(new Image(new File("data/passwordicon.png").toURI().toString()));
    }

    public void loginAttempt() throws IOException {
        // Check if the user already exists,if so,log them in
        boolean isExistingUser = false;
        for (User userPointer : Storage.getUserList()) {
            if(userPointer.getUsername().equals(
                    usernameInput.getText().trim())) {
                UniCart.currentUser = userPointer;
                isExistingUser = true;
            }
        }

        // Show error if the user does not exist
        if (!isExistingUser) {
            errorDisplay.setText("User does not exist.");
            return;
        }
        errorDisplay.setText("");

        // Check if the password is correct
        if (!UniCart.currentUser.getPassword().equals(
                passwordInput.getText().trim())) {
            errorDisplay.setText(("Incorrect password."));
            return;
        }
        errorDisplay.setText("");

        System.out.println("Logging in: " + usernameInput.getText().trim());

        // Set up FXML loader
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/view/Home.fxml"));
        AnchorPane root = loader.load();

        // Initialize page
        HomeController controller = loader.getController();
        controller.start();

        // Show the page
        UniCart.currentStage.setScene(new Scene(root, 1280, 720));
        UniCart.currentStage.show();
    }
}
