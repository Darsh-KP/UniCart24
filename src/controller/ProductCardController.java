package controller;

import app.UniCart;
import database.Storage;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import java.awt.event.ActionEvent;
import java.io.File;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.ResourceBundle;

import model.Product;

public class ProductCardController implements Initializable {
    @FXML
    private Text productName, originalPrice, yourPrice;

    @FXML
    private ImageView productImage, cartIcon;

    @FXML
    private ChoiceBox<String> productQuantity;

    private Product product;

    @FXML
    void addToCart() {
        int quantity = Integer.parseInt(productQuantity.getValue());
        String name = productName.getText();
        Product product1 = Storage.findProduct(name);
        UniCart.currentUser.addProductToCart(product1, quantity);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        for(int i = 1; i <= 5; i++){
            productQuantity.getItems().add(Integer.toString(i));
        }

        productQuantity.setValue("1");

    }

    public void setData(Product product){
        this.product = product;
        productName.setText(product.getName());
        originalPrice.setText("$" + product.getPrice());

        double studentPrice = (product.getPrice() - product.getStoreDiscount() - product.getLoyaltyDiscount() - product.getDigitalCoupon());
        String formattedNumber = "0.01";
        DecimalFormat df = new DecimalFormat("#.##");
        if(studentPrice > 0.0) formattedNumber = df.format(studentPrice);

        yourPrice.setText("$" + formattedNumber);

        if(!product.getImagePath().equals("")) {
            productImage.setImage(new Image(new File(product.getImagePath()).toURI().toString()));
        }

        cartIcon.setImage(new Image(new File("data/addCart.png").toURI().toString()));

    }

}
