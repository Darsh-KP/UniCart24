package controller;

import app.Shoprite;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

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

    }
}
