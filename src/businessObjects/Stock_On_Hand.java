/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package businessObjects;

import databaseConnect.DatabaseConnection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.RadioButton;
import javax.swing.text.DateFormatter;

/**
 *
 * @author Alex
 */
public class Stock_On_Hand
{

    Integer stockAvailableQuantity;
    SimpleStringProperty stockName, customerCompanyName, stockID, customerID;
    LocalDate transactionDate;

    public Stock_On_Hand(String stockID, String customerID, Integer stockAvailableQuantity, String stockName, String customerCompanyName, String transactionDate)
    {
        this.stockID = new SimpleStringProperty(stockID);
        this.customerID = new SimpleStringProperty(customerID);
        this.stockAvailableQuantity = stockAvailableQuantity;
        this.stockName = new SimpleStringProperty(stockName);
        this.customerCompanyName = new SimpleStringProperty(customerCompanyName);
        this.transactionDate = LocalDate.parse(transactionDate);
    }
    public Stock_On_Hand(String stockID, String customerID, Integer stockAvailableQuantity, String stockName, String customerCompanyName, LocalDate transactionDate)
    {
        this.stockID = new SimpleStringProperty(stockID);
        this.customerID = new SimpleStringProperty(customerID);
        this.stockAvailableQuantity = stockAvailableQuantity;
        this.stockName = new SimpleStringProperty(stockName);
        this.customerCompanyName = new SimpleStringProperty(customerCompanyName);
        this.transactionDate = transactionDate;
    }

    public LocalDate getTransactionDate()
    {
        return transactionDate;
    }

    public void setTransactionDate(LocalDate transactionDate)
    {
        this.transactionDate = transactionDate;
    }

    public String getStockID()
    {
        return stockID.get();
    }

    public void setStockID(String stockID)
    {
        this.stockID = new SimpleStringProperty(stockID);
    }

    public String getCustomerID()
    {
        return customerID.get();
    }

    public void setCustomerID(String customerID)
    {
        this.customerID = new SimpleStringProperty(customerID);
    }

    public Integer getStockAvailableQuantity()
    {
        return stockAvailableQuantity;
    }

    public void setStockAvailableQuantity(Integer stockAvailableQuantity)
    {
        this.stockAvailableQuantity = stockAvailableQuantity;
    }

    public String getStockName()
    {
        return stockName.get();
    }

    public void setStockName(String stockName)
    {
        this.stockName = new SimpleStringProperty(stockName);
    }

    public String getCustomerCompanyName()
    {
        return customerCompanyName.get();
    }

    public void setCustomerCompanyName(String customerCompanyName)
    {
        this.customerCompanyName = new SimpleStringProperty(customerCompanyName);
    }

    public static ObservableList<Stock_On_Hand> getStockOnHand()
    {   
        ObservableList<Stock_On_Hand> stock = FXCollections.observableArrayList();
        ResultSet result;
        try
        {
            PreparedStatement SELECT = DatabaseConnection.connection.prepareStatement("SELECT * FROM stock_by_customer;");
            result = SELECT.executeQuery();
            
            while (result.next())
            {                               
                stock.add(new Stock_On_Hand(result.getString("StockID"), result.getString("customerID"), Integer.parseInt(result.getString("Quantity")), result.getString("Item"), result.getString("Company"), result.getString("ArrivalDate")));
            }
            
        } catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
        
        return stock;
    }
    
    public static ObservableList<Stock_On_Hand> getStockOnHand(String customerID)
    {   
        ObservableList<Stock_On_Hand> stock = FXCollections.observableArrayList();
        ResultSet result;
        try
        {
            PreparedStatement SELECT = DatabaseConnection.connection.prepareStatement("SELECT * FROM stock_by_customer WHERE customerID = '"+customerID+"';");
            result = SELECT.executeQuery();
            
            while (result.next())
            {                               
                stock.add(new Stock_On_Hand(result.getString("StockID"), result.getString("customerID"), Integer.parseInt(result.getString("Quantity")), result.getString("Item"), result.getString("Company"), result.getString("ArrivalDate")));
            }
            
        } catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
        
        return stock;
    }
    
    public static ObservableList<Stock_On_Hand> searchTableByItem(String item)
    {
        ObservableList<Stock_On_Hand> stockItems = FXCollections.observableArrayList();
        ResultSet result;
        
        try
        {
            PreparedStatement SELECT = DatabaseConnection.connection.prepareStatement("SELECT * FROM stock_by_customer WHERE item = '"+ item +"' OR item LIKE '%"+item+"' OR item LIKE '"+item+"%'; ");
            result = SELECT.executeQuery();
            
            while (result.next())
            {   //(String stockID, String customerID, Integer stockAvailableQuantity, String stockName, String customerCompanyName, String transactionDate)
                stockItems.add(new Stock_On_Hand(result.getString("StockID"), result.getString("customerID"), Integer.parseInt(result.getString("Quantity")), result.getString("Item"), result.getString("Company"), result.getString("ArrivalDate")));
            }
            
        } catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
        return stockItems;
    }
    
    public static ObservableList<Stock_On_Hand> searchByReceipt(String item)
    {
        ObservableList<Stock_On_Hand> stockItems = FXCollections.observableArrayList();
        ResultSet result;
        
        try
        {
            PreparedStatement SELECT = DatabaseConnection.connection.prepareStatement("SELECT * FROM stock_by_customer WHERE ReceiptID = '"+item+"'");
            result = SELECT.executeQuery();
            
            while (result.next())
            {
                stockItems.add(new Stock_On_Hand(result.getString("StockID"), result.getString("customerID"), Integer.parseInt(result.getString("Quantity")), result.getString("Item"), result.getString("Company"), result.getString("ArrivalDate")));
            }
        } catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
        
        return stockItems;
    }
    
    public static ObservableList<Stock_On_Hand> searchByCustomer(String item)
    {
        ObservableList<Stock_On_Hand> stockItems = FXCollections.observableArrayList();
        ResultSet result;
        
        try
        {
            PreparedStatement SELECT = DatabaseConnection.connection.prepareStatement("SELECT * FROM stock_by_customer WHERE customerID = '"+item+"' OR Company LIKE '%"+item+"' OR Company LIKE '"+item+"%' ");
            result = SELECT.executeQuery();
            
            while (result.next())
            {
                stockItems.add(new Stock_On_Hand(result.getString("StockID"), result.getString("customerID"), Integer.parseInt(result.getString("Quantity")), result.getString("Item"), result.getString("Company"), result.getString("ArrivalDate")));
            }
        } catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
        
        return stockItems;
    }

     public static ObservableList<Stock_On_Hand> searchByDate(LocalDate date)
    {
        ObservableList<Stock_On_Hand> stockItems = FXCollections.observableArrayList();
        ResultSet result;
        String item = date.format(DateTimeFormatter.ISO_DATE);
        System.out.println(item);
        
        try
        {
            PreparedStatement SELECT = DatabaseConnection.connection.prepareStatement("SELECT * FROM stock_by_customer WHERE ArrivalDate = '"+item+"'");
            result = SELECT.executeQuery();
            
            while (result.next())
            {
                stockItems.add(new Stock_On_Hand(result.getString("StockID"), result.getString("customerID"), Integer.parseInt(result.getString("Quantity")), result.getString("Item"), result.getString("Company"), result.getString("ArrivalDate")));
            }
        } catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
        
        return stockItems;
    }
}
