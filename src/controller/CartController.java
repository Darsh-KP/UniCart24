package controller;

import app.UniCart;
import database.Storage;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
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
}
