package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class User implements Serializable {
    private final String username;
    private final String password;
    private List<ProductWithQuantity> cart;
    private List<ProductWithQuantity> wishlist;
    private List<ProductWithQuantity> essentials;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
        cart = new ArrayList<>();
        wishlist = new ArrayList<>();
        essentials = new ArrayList<>();
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public List<ProductWithQuantity> getWishlist() {
        return wishlist;
    }

    public List<ProductWithQuantity> getEssentials() {
        return essentials;
    }

    public List<ProductWithQuantity> getCart() {
        return cart;
    }

    public void addProductToCart(Product product, int quantity) {
        cart.add(new ProductWithQuantity(product, quantity));
    }
}
