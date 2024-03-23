package controller;

import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import model.Product;

public class ProductCardController {
    @FXML
    private Text productName, originalPrice, yourPrice;

    @FXML
    private ImageView productImage;

    private Product product;

    public void setData(Product product){
        this.product = product;
        productName.setText(product.getName());
        originalPrice.setText("$" + product.getPrice());
        yourPrice.setText("$" + (product.getPrice() - product.getStoreDiscount() - product.getLoyaltyDiscount() - product.getDigitalCoupon() ));
        /*Image image = new Image(getClass().getResourceAsStream(product.getImagePath()));
        productImage.setImage(image);*/
    }

}
