package controller;

import app.UniCart;
import database.Storage;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import model.Product;

import java.io.IOException;
import java.util.List;

public class ProductPageController {
    @FXML
    private GridPane grid;

    public void start(){
        List<Product> products = Storage.getProductList();
        int column = 0;
        int row = 1;
        try {
            for (Product product : products) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/view/ProductCard.fxml"));

                AnchorPane anchorPane = fxmlLoader.load();

                ProductCardController productCardController = fxmlLoader.getController();
                productCardController.setData(product);

                if(column == 5){
                    column = 0;
                    row++;
                }

                grid.add(anchorPane, column++, row);
                GridPane.setMargin(anchorPane,new Insets(10));
            }
        }catch (IOException e){
            System.out.println("Error!");
        }
    }

    public void backToHome() throws IOException {
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

    public void openCartPageView() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("../view/Cart.fxml"));

        AnchorPane root = loader.load();
        CartController cartController = loader.getController();
        cartController.start();

        Scene scene = new Scene(root, 1280, 720);
        UniCart.currentStage.setScene(scene);
        UniCart.currentStage.show();
    }

    public void openBudgetPageView() throws IOException {
        // Set up an FXML Loader
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("../view/Budget.fxml"));

        //Loading the FXML
        AnchorPane root = loader.load();
        BudgetController budget = loader.getController();
        //budget.start();

        // Show the page and the scene
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
