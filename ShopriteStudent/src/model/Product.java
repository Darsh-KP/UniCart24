package model;

import java.io.Serializable;

public class Product implements Serializable {
    String id;
    String name;
    String department;
    double price;
    double storeDiscount;
    double loyaltyDiscount;
    double digitalCoupon;
    String imagePath;

    public Product(){

    }

    public Product(
            String id, String name, String department,
            double price, double storeDiscount, double loyaltyDiscount, double digitalCoupon,
    ) {
        this.id = id;
        this.name = name;
        this.department = department;
        this.price = price;
        this.storeDiscount = storeDiscount;
        this.loyaltyDiscount = loyaltyDiscount;
        this.digitalCoupon = digitalCoupon;
        this.imagePath = "";
    }

    @Override
    public String toString(){
        return "\nProducts:\n" + id + "\n" + name + "\n" + price + "\n"
                + storeDiscount + "\n" + loyaltyDiscount + "\n" + digitalCoupon + "\n";
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
