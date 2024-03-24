package model;

import java.io.Serializable;
import java.util.List;

public class User implements Serializable {
    private String username;
    private String password;
    private List<ProductWithQuantity> cart;
    private List<ProductWithQuantity> wishlist;
    private List<ProductWithQuantity> essentials;



    public List<ProductWithQuantity> getCart() {
        return cart;
    }

    public void addProductToCart(Product product, int quantity) {
        cart.add(new ProductWithQuantity(product, quantity));
    }
}
