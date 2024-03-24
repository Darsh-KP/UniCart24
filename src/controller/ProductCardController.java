package controller;

import app.UniCart;
import database.Storage;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;

import java.net.URL;
import java.text.DecimalFormat;
import java.util.ResourceBundle;

import model.Product;

public class ProductCardController implements Initializable {
    @FXML
    private Text productName, originalPrice, yourPrice;

    @FXML
    private ImageView productImage;

    @FXML
    private ChoiceBox<String> productQuantity;

    private Product product;

    @FXML
    void addToCart() {
        int quantity = Integer.parseInt(productQuantity.getValue());
        String name = productName.toString();
        UniCart.currentUser.addProductToCart(Storage.findProduct(name), quantity);
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
        DecimalFormat df = new DecimalFormat("#.##");
        String formattedNumber = df.format(studentPrice);

        yourPrice.setText("$" + formattedNumber);
        Image image = new Image(getClass().getResourceAsStream(product.getImagePath()));
        productImage.setImage(image);
    }

}
