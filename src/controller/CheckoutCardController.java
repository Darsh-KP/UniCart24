package controller;

import app.UniCart;
import database.Storage;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import model.Product;
import model.ProductWithQuantity;
import model.User;

import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;

public class CheckoutCardController {
    @FXML
    private Text originalPrice, productName, yourQuantity;

    @FXML
    private ImageView productImage;

    private ProductWithQuantity productWithQuantity;

    @FXML
    void removeFromCart() {
        String name = productName.getText();
        Product product = Storage.findProduct(name);
        int quantity = Integer.parseInt(yourQuantity.getText());
        UniCart.currentUser.removeProductFromCart(UniCart.currentUser.findProductWithQuantity(product,quantity));
    }

    public void setData(ProductWithQuantity productWithQuantity){
        this.productWithQuantity = productWithQuantity;
        productName.setText(productWithQuantity.getItem().getName());

        double studentPrice = (productWithQuantity.getQuantity())*(productWithQuantity.getItem().getPrice()
                - productWithQuantity.getItem().getStoreDiscount() - productWithQuantity.getItem().getLoyaltyDiscount()
                - productWithQuantity.getItem().getDigitalCoupon());
        double correctValue = (studentPrice > 0.0) ? studentPrice : ((productWithQuantity.getQuantity()) * 0.01);
        DecimalFormat df = new DecimalFormat("#.##");
        String formattedNumber = df.format(correctValue);

        originalPrice.setText("$" + formattedNumber);

        yourQuantity.setText(String.valueOf(productWithQuantity.getQuantity()));
        if(!productWithQuantity.getItem().getImagePath().equals("")) {
            productImage.setImage(new Image(new File(productWithQuantity.getItem().getImagePath()).toURI().toString()));
        }
    }
}
