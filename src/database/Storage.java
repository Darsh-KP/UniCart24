package database;

import model.Product;

import java.awt.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import model.User;
import java.io.*;
import java.util.*;
import java.util.List;

public class Storage {
    // List of all the users for the session
    private static List<User> userList;

    private static List<Product> productList = new ArrayList<>();

    Storage(){}

    public static void initializeDemo() {
        // Check if user.data already exists
        File userData = new File("src/database/users.data");
        if (userData.exists()) return;

        // Create the demo user
        userList = new ArrayList<>();
        User demo = new User("stock", "HackRU_S24");

        // Add stuff to demo

        // Add demo to user list
        addUser(demo);
    }

    private static String[] cleanItemList(String line){
        String[] tokens = line.split(",");

        for(int i = 0; i < tokens.length; i++){
            tokens[i] = tokens[i]
                    .replaceAll("\\[", "")
                    .replaceAll("\\]", "")
                    .replaceAll("\\{", "")
                    .replaceAll("\\}", "");
        }

        return tokens;
    }

    private static void parseInfo(String line){
        String[] tokens = cleanItemList(line);

        String UPC = tokens[0].split(":")[1].trim();
        String description = tokens[1].split(":")[1].replaceAll("\"", "").trim();
        String department = tokens[2].split(":")[1].replaceAll("\"", "").trim();

        String priceString = tokens[3].split(":")[1].replaceAll("\"", "").
                replaceAll("\\$","").replaceAll("\\s", "");
        double price = (priceString.length() == 0) ? 0.0 : Double.valueOf(priceString);

        String storeString = tokens[4].split(":")[1].replaceAll("\"", "").replaceAll("\\$","").
                replaceAll("\\s", "");
        double storeDiscount = (storeString.length() == 0) ? 0.0 : Double.valueOf(storeString);

        String loyaltyString = tokens[5].split(":")[1].replaceAll("\"", "").replaceAll("\\$","").
                replaceAll("\\s", "");
        double loyaltyDiscount = (loyaltyString.length() == 0) ? 0.0 : Double.valueOf(loyaltyString);

        String digitalString = tokens[5].split(":")[1].replaceAll("\"", "").
                replaceAll("\\$","").replaceAll("\\s", "");
        double digitalCoupon = (digitalString.length() == 0) ? 0.0 : Double.valueOf(digitalString);

        //String imagePath = "data/productImages/" + description + ".png";

        Product product = new Product(UPC, description, department, price, storeDiscount, loyaltyDiscount, digitalCoupon);
        productList.add(product);
    }

   public static void intializeProductsAPI(){
       try {
           //API endpoint
           String apiUrl = "https://apimdev.wakefern.com/mockexample/V1/getItemDetails";

           // Create URL object
           URL url = new URL(apiUrl);

           // Open connection
           HttpURLConnection connection = (HttpURLConnection) url.openConnection();

           // Set request method to GET
           connection.setRequestMethod("GET");

           // Set request headers
           connection.setRequestProperty("Ocp-Apim-Subscription-Key", "4ae9400a1eda4f14b3e7227f24b74b44");
           connection.setRequestProperty("Content-Type", "application/json");

           // Get response code
           int responseCode = connection.getResponseCode();

           // Read response body
           BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
           StringBuilder response = new StringBuilder();
           String inputLine;
           while ((inputLine = in.readLine()) != null) {
               response.append(inputLine);
           }
           in.close();

           String[] items = response.toString().split("\\},");
           for(int i = 0; i < items.length; i++) {
               parseInfo(items[i]);
           }

           // Close connection
           connection.disconnect();
       } catch (IOException e) {
           e.printStackTrace();
       }
   }

    public static Product findProduct(String name){
        for(int i = 0; i < Storage.productList.size(); i++){
            if(productList.get(i).getName().trim().equals(name.trim())){
                return productList.get(i);
            }
        }

        return null;
    }

   public static List<Product> getTopDiscountProducts(){
       Collections.sort(productList);
        List<Product> list = new ArrayList<>();
        int items = (productList.size() > 10) ? (productList.size()-11) : 0;
        for(int i = productList.size()-1; i >=  items; i--){
            list.add(productList.get(i));
        }

        return list;
   }

    public static List<Product> getBestSellers(){
        Random rand = new Random();
        List<Product> list = new ArrayList<>();
        int count = 0;

        for(Product product: productList){
            int num = rand.nextInt(100) + 1;
            if(num < 50){
                list.add(product);
                count++;
            }
            if(count == 10) break;
        }

        return list;
    }

    public static List<Product> getProductList() {
        return productList;
    }

    public static void setProductList(List<Product> productList) {
        Storage.productList = productList;
    }

    public static void testTemp(){
        intializeProductsAPI();
        for(int i = 0; i < productList.size(); i++){
            System.out.println(productList.get(i).toString());
        }
    }

    // Loads all user data from a file
    public static void loadUsers() {
        System.out.println("Loading data...");
        // Gets all the saved data from the file
        try {
            // Get input file
            FileInputStream inputFile = new FileInputStream("src/database/users.data");
            ObjectInputStream inputStream = new ObjectInputStream(inputFile);

            // Retrieve the user data
            userList = (List<User>) inputStream.readObject();

            // Close the input file
            inputStream.close();
            inputFile.close();
        } catch (IOException | ClassNotFoundException error) {
            System.out.println("IO error!");
        }

        // If userList is null then make it empty
        if (userList == null) userList = new ArrayList<>();
    }

    // Saves all user data to a file
    public static void saveUsers() {
        System.out.println("Saving data...");
        // Saves the list of users in a file
        try {
            // Get output file
            FileOutputStream outputFile = new FileOutputStream("src/database/users.data");
            ObjectOutputStream outputStream = new ObjectOutputStream(outputFile);

            // Write the user data
            outputStream.writeObject(userList);

            // Close the output file
            outputStream.close();
            outputFile.close();
        } catch (IOException error) {
            System.out.println(error.getMessage());
        }
    }

    // Returns the list of users and their data
    public static List<User> getUserList() {
        return Storage.userList;
    }

    // Adds a user in the system
    public static void addUser(User user) {
        Storage.userList.add(user);
    }

    // Removes a user from the system
    public static void removeUser(User user) {
        Storage.userList.remove(user);
    }


}
