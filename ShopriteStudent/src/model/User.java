package model;

import java.io.Serializable;
import java.util.List;

public class User implements Serializable {
    private String username;
    private String password;
    private List<ProductWithQuantity> cart;
    private List<ProductWithQuantity> wishlist;
    private List<ProductWithQuantity> essentials;
}
