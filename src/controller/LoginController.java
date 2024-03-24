package controller;

import app.Shoprite;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Class responsible for logging in user and managing the login view.
 */
public class LoginController {
    @FXML
    TextField usernameInputField;
    @FXML
    Button loginButton;
    @FXML
    Text loginErrorMessage;

    /**
     * Logs in the appropriate user. Decides whether to load admin or non-admin view.
     *
     * @throws IOException
     */
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
