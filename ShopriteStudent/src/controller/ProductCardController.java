package controller;

import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import java.text.DecimalFormat;
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

        double studentPrice = (product.getPrice() - product.getStoreDiscount() - product.getLoyaltyDiscount() - product.getDigitalCoupon());
        DecimalFormat df = new DecimalFormat("#.##");
        String formattedNumber = df.format(studentPrice);

        yourPrice.setText("$" + formattedNumber);
        /*Image image = new Image(getClass().getResourceAsStream(product.getImagePath()));
        productImage.setImage(image);*/
    }

}
