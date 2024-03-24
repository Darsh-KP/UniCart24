package controller;

import data.Storage;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import model.Product;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ProductPageController {
    @FXML
    private GridPane grid;

    public void start(){
        List<Product> products = Storage.getProductList();
        int column = 0;
        int row = 0;
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

}
