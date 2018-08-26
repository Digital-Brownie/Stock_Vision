/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package businessObjects;
import businessObjects.Customer;
import businessObjects.Customer;
import databaseConnect.DatabaseConnection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
/**
 *
 * @author Alex
 */
public class Stock_Item
{
    
    public SimpleStringProperty stockName, stockID, category;
    public static String[] categories = new String[] {"All", "General", "Perishable", "Fragile", "Medical", "Extra Large Items"};

    public Stock_Item(String stockID, String stockName, String category)
    {
        this.stockID = new SimpleStringProperty (stockID);
        this.stockName = new SimpleStringProperty (stockName);   
        this.category = new SimpleStringProperty (category);
    }

    public String getCategory()
    {
        return category.get();
    }

    public void setCategory(String category)
    {
        this.category = new SimpleStringProperty (category);
    }

    public String getStockID()
    {
        return stockID.get();
    }

    public void setStockID(String stockID)
    {
        this.stockID = new SimpleStringProperty (stockID);
    }    

    public String getStockName()
    {
        return stockName.get();
    }

    public void setStockName(String stockName)
    {
        this.stockName = new SimpleStringProperty (stockName);
    }           
                
    public static ObservableList<Stock_Item> getStockItems(int custID) throws Exception
    {
        ResultSet result;
        ObservableList<Stock_Item> stock_items = FXCollections.observableArrayList();
        
        try
        {
            PreparedStatement SELECT = DatabaseConnection.getConnection().prepareStatement("SELECT * FROM stock_items WHERE CustomerID = " + custID + ";");
            result = SELECT.executeQuery();
            
            while (result.next())
            {               
                stock_items.add(new Stock_Item(result.getString("StockID"), result.getString("ItemName"), result.getString("ItemCategory")));                
            }
        } catch (SQLException ex)
        {
            Logger.getLogger(DatabaseConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
        return stock_items;
    }
    
    public static ObservableList<Stock_Item> getStockItems() throws Exception
    {
        ResultSet result;
        ObservableList<Stock_Item> stock_items = FXCollections.observableArrayList();
        
        try
        {
            PreparedStatement SELECT = DatabaseConnection.getConnection().prepareStatement("SELECT * FROM stock_items;");
            result = SELECT.executeQuery();
            
            while (result.next())
            {               
                stock_items.add(new Stock_Item(result.getString("StockID"), result.getString("ItemName"),result.getString("ItemCategory")));  
            }
        } catch (SQLException ex)
        {
            Logger.getLogger(DatabaseConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
        return stock_items;
    }
    
    public static ObservableList<Stock_Item> getStockItems(String category) throws Exception
    {
        ResultSet result;
        ObservableList<Stock_Item> stock_items = FXCollections.observableArrayList();
        
        try
        {
            PreparedStatement SELECT = DatabaseConnection.getConnection().prepareStatement("SELECT * FROM stock_items WHERE ItemCategory = '" +category+ "' ;");
            result = SELECT.executeQuery();
            
            while (result.next())
            {               
                stock_items.add(new Stock_Item(result.getString("StockID"), result.getString("ItemName"),result.getString("ItemCategory")));  
            }
        } catch (SQLException ex)
        {
            Logger.getLogger(DatabaseConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
        return stock_items;
    }
    
    public static void addNewStockItem(String code, String name, String category)
    {        
        
        try
        {                        
            PreparedStatement addNewItem = DatabaseConnection.getConnection().prepareStatement("insert into stock_items(StockId, ItemName, ItemCategory) VALUES('"+code+""+getKey(code)+"', '"+name+"', '"+category+"');");
            addNewItem.executeUpdate();
            System.out.println("Add new Item complete");
            
            incrementKey(code);
        } catch (Exception ex)
        {
            Logger.getLogger(Customer.class.getName()).log(Level.SEVERE, null, ex);
        }
                                     
    }
    
    public static void incrementKey(String itemCode) throws SQLException
    {
        String code = itemCode.substring(0, 3);        
        
        PreparedStatement UPDATE = DatabaseConnection.connection.prepareStatement("update invento_systems.keys set "+code+" = "+code+" + 1 Where keyID = 1; ");
        UPDATE.executeUpdate();
    }
    
    public static void decrementKey(String itemCode) throws SQLException
    {
        String code = itemCode.substring(0, 3);        
        
        PreparedStatement UPDATE = DatabaseConnection.connection.prepareStatement("update invento_systems.keys set "+code+" = "+code+" - 1 Where keyID = 1; ");
        UPDATE.executeUpdate();
    }
    
    public static String getKey(String category) throws SQLException
    {
        Integer key = -1;
        try
        {
            PreparedStatement getKey = DatabaseConnection.connection.prepareStatement("SELECT "+category+" FROM invento_systems.keys WHERE keyID = 1;");
            ResultSet result = getKey.executeQuery();           

            while (result.next())
            {
                key = result.getInt(category);
            }

        } catch (SQLException e)
        {
            System.out.println("e: " + e);
            System.out.println("Message: " + e.getMessage());
        }

        return String.format("%03d",key) ;
    }
}
