package controller;

import app.UniCart;
import database.Storage;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import model.Product;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class HomeController {
    @FXML
    private Text budgetDisplay;

    @FXML
    private GridPane latestDealsDisplay, popularItemsDisplay;

    @FXML
    private ImageView recipie1;
    @FXML   
    private ImageView logoHome;
    @FXML
    private ImageView recipie2;

    @FXML
    void button1 ()
    {
        Product product1 = Storage.findProduct("corn flour");
        Product product2 = Storage.findProduct("cocoa powder");

        UniCart.currentUser.addProductToCart(product1, 1);
        UniCart.currentUser.addProductToCart(product2, 1);

    }

    @FXML
    void button2 ()
    {
        Product product1 = Storage.findProduct("corn flour");
        Product product2 = Storage.findProduct("Parmesan cheese");

        UniCart.currentUser.addProductToCart(product1, 1);
        UniCart.currentUser.addProductToCart(product1, 1);

    }

    public void start ()
    {
        logoHome.setImage(new Image(new File("data/logo.png").toURI().toString()));
        recipie1.setImage(new Image(new File("data/Brownies.png").toURI().toString()));
        recipie2.setImage(new Image(new File("data/pizza.png").toURI().toString()));

        List<Product> products = Storage.getTopDiscountProducts();
        int column = 0;
        try {
            for (Product product : products) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/view/ProductCard.fxml"));

                AnchorPane anchorPane = fxmlLoader.load();

                ProductCardController productCardController = fxmlLoader.getController();
                productCardController.setData(product);

                latestDealsDisplay.add(anchorPane, column++, 1);
                GridPane.setMargin(anchorPane,new Insets(10));
            }
        }catch (IOException e){
            System.out.println("Error!");
        }

        products = Storage.getBestSellers();
        column = 0;
        try {
            for (Product product : products) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/view/ProductCard.fxml"));

                AnchorPane anchorPane = fxmlLoader.load();

                ProductCardController productCardController = fxmlLoader.getController();
                productCardController.setData(product);

                popularItemsDisplay.add(anchorPane, column++, 1);
                GridPane.setMargin(anchorPane,new Insets(10));
            }
        }catch (IOException e){
            System.out.println("Error!");
        }

        // Show current budget
        budgetDisplay.setText(String.valueOf(UniCart.currentUser.getBudget()));
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
