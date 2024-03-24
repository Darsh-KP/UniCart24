package model;

import database.Storage;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class User implements Serializable {
    private final String username;
    private final String password;
    private double budget;
    private List<ProductWithQuantity> cart = new ArrayList<>();
    private List<ProductWithQuantity> wishlist = new ArrayList<>();
    private List<ProductWithQuantity> essentials = new ArrayList<>();


    private HashMap<String,Double> expensesPerDepartment;

    public User(String username, String password) throws IllegalArgumentException {
        // Check for empty strings
        if (username.isEmpty() || password.isEmpty()) throw new IllegalArgumentException("Cannot have empty credentials.");

        // Check if user already exists
        for (User user : Storage.getUserList()) {
            if (user.getUsername().equals(username)) throw new IllegalArgumentException("User already exists.");
        }

        this.username = username.trim();
        this.password = password.trim();
        cart = new ArrayList<>();
        wishlist = new ArrayList<>();
        essentials = new ArrayList<>();

        expensesPerDepartment = new HashMap<String, Double>();
        budget = 250.00;
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

    public void emptyCart(){
        cart = new ArrayList<>();
    }


    public void addProductToCart(Product product, int quantity) {
        cart.add(new ProductWithQuantity(product, quantity));
    }

    public HashMap<String, Double> getExpensesPerDepartment() {
        return expensesPerDepartment;
    }

    public void addExpenseDepartment(String department, double expense) {
        if (expensesPerDepartment.containsKey(department)){
            Double existingValue = expensesPerDepartment.get(department);
            expensesPerDepartment.put(department, existingValue + expense);
        }
        else {
            expensesPerDepartment.put(department, expense);
        }
    }

    public ProductWithQuantity findProductWithQuantity(Product product, int quantity){
        for(int i = 0; i < cart.size(); i++){
            if(cart.get(i).getItem().getName().equals(product.getName()) && cart.get(i).getQuantity() == quantity){
                return cart.get(i);
            }
        }
        return null;
    }

    public void removeProductFromCart(ProductWithQuantity productWithQuantity){
        cart.remove(productWithQuantity);
    }

    public double getBudget() {
        return budget;
    }

    public void setBudget(double budget) {
        this.budget = budget;
    }
}
