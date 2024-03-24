package controller;

import database.Storage;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.chart.PieChart;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import model.Product;

import java.io.IOException;
import java.security.Key;
import java.util.HashMap;
import java.util.List;


public class BudgetController {

    @FXML private Text budgetLabel;

    @FXML private Text expensesLabel;

    @FXML private PieChart pieChart;

    private HashMap<String,Double> expenseRecord;

    public void start() {
        List<Product> products = Storage.getTopDiscountProducts();
        
        int column = 0;
        try {
            for (Product product : products) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/view/ProductCard.fxml"));

                AnchorPane anchorPane = fxmlLoader.load();
            }
        }
        catch (IOException e){
            System.out.println("Error!");
        }
    }



}
