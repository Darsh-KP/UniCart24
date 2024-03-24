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
import model.ProductWithQuantity;
import model.User;

import java.io.IOException;
import java.util.List;

public class CartController {

    @FXML
    private GridPane grid;

    @FXML
    void checkout(){
        UniCart.currentUser.emptyCart();
        grid.getChildren().clear();
    }

    public void start(){


        updateCartView();
    }

    public void updateCartView(){
        List<ProductWithQuantity> products = UniCart.currentUser.getCart();
        grid.getChildren().clear();
        int column = 0;
        try {

            for (ProductWithQuantity product : products) {
                System.out.printf(product.getItem().toString());
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/view/CheckoutCard.fxml"));

                AnchorPane anchorPane = fxmlLoader.load();

                CheckoutCardController checkoutCardController = fxmlLoader.getController();
                checkoutCardController.setData(product);

                grid.add(anchorPane, column++, 1);
                GridPane.setMargin(anchorPane, new Insets(10));
            }
        }catch (IOException e){
            System.out.println(e.getMessage());
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

    public void openBudgetPageView() throws IOException {
        // Set up an FXML Loader
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("../view/Budget.fxml"));

        //Loading the FXML
        AnchorPane root = loader.load();

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
