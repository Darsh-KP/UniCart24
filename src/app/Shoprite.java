package app;

import controller.ProductPageController;
import data.Storage;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Screen;
import javafx.stage.Stage;
import model.User;

import static data.Storage.testTemp;

public class Shoprite extends Application {
    public static Stage currentStage;
    public static User currentUser;

    @Override
    public void start(Stage primaryStage) throws Exception {
        Storage.intializeProductsAPI();

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

        // Set the stage when you open the application
        currentStage = primaryStage;
        Scene scene = new Scene(root, 1280, 720);
        currentStage.setScene(scene);
        currentStage.setResizable(false);
        currentStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
