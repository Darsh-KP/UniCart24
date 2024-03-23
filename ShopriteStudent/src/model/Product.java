package model;

import java.io.Serializable;

public class Product implements Serializable {
    String id;
    String description;
    String department;
    double price;
    double storeDiscount;
    double loyaltyDiscount;
    double digitalCoupon;

    public Product(){

    }

    public Product(
            String id, String description, String department,
            double price, double storeDiscount, double loyaltyDiscount, double digitalCoupon
    ) {
        this.id = id;
        this.description = description;
        this.department = department;
        this.price = price;
        this.storeDiscount = storeDiscount;
        this.loyaltyDiscount = loyaltyDiscount;
        this.digitalCoupon = digitalCoupon;
    }

    @Override
    public String toString(){
        return "\nProducts:\n" + id + "\n" + description + "\n" + price + "\n"
                + storeDiscount + "\n" + loyaltyDiscount + "\n" + digitalCoupon + "\n";
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getStoreDiscount() {
        return storeDiscount;
    }

    public void setStoreDiscount(double storeDiscount) {
        this.storeDiscount = storeDiscount;
    }

    public double getLoyaltyDiscount() {
        return loyaltyDiscount;
    }

    public void setLoyaltyDiscount(double loyaltyDiscount) {
        this.loyaltyDiscount = loyaltyDiscount;
    }

    public double getDigitalCoupon() {
        return digitalCoupon;
    }

    public void setDigitalCoupon(double digitalCoupon) {
        this.digitalCoupon = digitalCoupon;
    }
}
