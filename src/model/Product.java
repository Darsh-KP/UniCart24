package model;

import java.io.File;
import java.io.Serializable;

public class Product implements Serializable, Comparable<Product> {
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
            double price, double storeDiscount, double loyaltyDiscount, double digitalCoupon
    ) {
        this.id = id;
        this.name = name;
        this.department = department;
        this.price = price;
        this.storeDiscount = storeDiscount;
        this.loyaltyDiscount = loyaltyDiscount;
        this.digitalCoupon = digitalCoupon;

        String imagePath1 = "data/productImages/" + name + ".png";
        String imagePath2 = "data/productImages/" + name + ".jpeg";
        File file1 = new File(imagePath1);
        File file2 = new File(imagePath2);
        if(file1.exists()) {
            this.imagePath = imagePath1;
        }else if(file2.exists()){
            this.imagePath = imagePath2;
        }else{
            this.imagePath = "";
        }
    }

    public Product(
            String id, String name, String department,
            double price, double storeDiscount, double loyaltyDiscount, double digitalCoupon, String imagePath
    ) {
        this.id = id;
        this.name = name;
        this.department = department;
        this.price = price;
        this.storeDiscount = storeDiscount;
        this.loyaltyDiscount = loyaltyDiscount;
        this.digitalCoupon = digitalCoupon;

        File file = new File(imagePath);
        if(file.exists()) {
            this.imagePath = imagePath;
        }else{
            this.imagePath = "";
        }
    }

    @Override
    public String toString(){
        return "\nProducts:\n" + id + "\n" + name + "\n" + price + "\n"
                + storeDiscount + "\n" + loyaltyDiscount + "\n" + digitalCoupon + "\n";
    }

    @Override
    public int compareTo(Product other) {
        double discount1 = this.loyaltyDiscount + this.getDigitalCoupon() + this.getStoreDiscount();
        double discount2 = other.loyaltyDiscount + other.getDigitalCoupon() + other.getStoreDiscount();
        return Double.compare(discount1, discount2);
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

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }
}
