package model;

import java.io.Serializable;

public class ProductWithQuantity implements Serializable {
    private final Product item;
    private int quantity;

    public ProductWithQuantity(Product item, int quantity) {
        this.item = item;
        this.quantity = quantity;
    }

    public Product getItem() {
        return item;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
