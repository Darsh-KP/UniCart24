package app;

import controller.LoginController;
import database.Storage;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.User;

import java.io.File;

public class UniCart extends Application {
    public static Stage currentStage;
    public static User currentUser;

    @Override
    public void start(Stage primaryStage) throws Exception {
        // Initializes API get all the products
        Storage.intializeProductsAPI();

        // Initialize demo user and its data
        Storage.initializeDemo();

        // Load saved data when app is started
        Storage.loadUsers();

        // Save date when app is closed
        primaryStage.setOnCloseRequest(windowEvent -> {
            System.out.print("Closing app. ");
            Storage.saveUsers();
        });

        // Set up FXML loader
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/view/Login.fxml"));

        // Load the fxml
        AnchorPane root = loader.load();
        LoginController controller = loader.getController();
        controller.start();

        // Set the stage when you open the application
        currentStage = primaryStage;
        Scene scene = new Scene(root, 1280, 720);
        currentStage.setScene(scene);
        currentStage.setResizable(false);
        currentStage.getIcons().add(new Image(new File("data/logo.png").toURI().toString()));
        currentStage.setTitle("UniCart");
        currentStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
