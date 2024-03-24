package controller;

import app.UniCart;
import database.Storage;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

import java.io.IOException;

public class HomeController {

    @FXML private Text budgetDisplay;

    public void start () {
    }

    // Show the products page with all the products
    public void openProductPageView() throws IOException {
        // Set up FXML loader
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("../view/ProductPage.fxml"));

        // Load the fxml
        AnchorPane root = loader.load();
        ProductPageController photoController = loader.getController();
        photoController.start();

        // Set the stage to the admin panel
        Scene scene = new Scene(root, 1280, 720);
        UniCart.currentStage.setScene(scene);
        UniCart.currentStage.show();
    }

    public void logoutUser() throws IOException {
        // Set current user to null
        UniCart.currentUser = null;

        // Save the user's data
        Storage.saveUsers();

        // Show the log in screen

        // Set up FXML loader
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("../view/Login.fxml"));

        // Load the fxml
        AnchorPane root = loader.load();
        LoginController controller = loader.getController();
        controller.start();

        // Set the stage to the admin panel
        Scene scene = new Scene(root, 1280, 720);
        UniCart.currentStage.setScene(scene);
        UniCart.currentStage.show();
    }
}
