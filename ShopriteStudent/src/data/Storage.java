package data;

import model.Product;

import java.net.HttpURLConnection;
import java.net.URL;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Storage {
    private static List<Product> productList = new ArrayList<>();

    Storage(){}

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

        Product product = new Product(UPC, description, department, price, storeDiscount, loyaltyDiscount, digitalCoupon);
        productList.add(product);
    }

   public static void intializeProductsAPI(){
       try {
           // API endpoint
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
}
