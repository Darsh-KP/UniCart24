package model;

import java.io.Serializable;

public class ProductWithQuantity implements Serializable {
    private Product item;
    private int quantity;

    public ProductWithQuantity(Product item, int quantity) {
        this.item = item;
        this.quantity = quantity;
    }
}
