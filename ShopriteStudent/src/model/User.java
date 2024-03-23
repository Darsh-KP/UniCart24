package model;

import java.io.Serializable;
import java.util.List;

public class User implements Serializable {
    private String username;
    private String password;
    private List<CartItem> cart;
    private List<CartItem> wishlist;
    private List<CartItem> essentials;
}
