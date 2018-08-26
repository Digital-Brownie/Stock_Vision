/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package businessObjects;

import databaseConnect.DatabaseConnection;
import java.sql.Array;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Arrays;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.ObservableList;

/**
 *
 * @author Alex
 */
public class Receipt_Item
{

    SimpleStringProperty ReceiptItemName, ReceiptID, StockID;
    Integer quantity;

    public Receipt_Item(String ReceiptItemName, String ReceiptID, String StockID, Integer quantity)
    {
        this.ReceiptItemName = new SimpleStringProperty(ReceiptItemName);
        this.ReceiptID = new SimpleStringProperty(ReceiptID);
        this.StockID = new SimpleStringProperty(StockID);
        this.quantity = quantity;
    }

    public String getReceiptItemName()
    {
        return ReceiptItemName.get();
    }

    public void setReceiptItemName(String ReceiptItemName)
    {
        this.ReceiptItemName = new SimpleStringProperty(ReceiptItemName);
    }

    public Integer getQuantity()
    {
        return quantity;
    }

    public void setQuantity(Integer quantity)
    {
        this.quantity = quantity;
    }

    public String getReceiptID()
    {
        return ReceiptID.get();
    }

    public void setReceiptID(String ReceiptID)
    {
        this.ReceiptID = new SimpleStringProperty(ReceiptID);
    }

    public String getStockID()
    {
        return StockID.get();
    }

    public void setStockID(String StockID)
    {
        this.StockID = new SimpleStringProperty(StockID);
    }

    public static void insertReceiptItemsTable(ObservableList<Receipt_Item> newReceiptList)
    {
        try
        {
            for (Receipt_Item item : newReceiptList)
            {                
                PreparedStatement INSERT = DatabaseConnection.connection.prepareStatement("Insert into receipt_items(ReceiptItemName,Quantity, ReceiptID, StockID) VALUES ('" + item.getReceiptItemName() + "', " + item.getQuantity() + ", '" + item.getReceiptID() + "', '" + item.getStockID() + "');");
                INSERT.executeUpdate();
                
                Stock_Item.incrementKey(item.getStockID());
            }

        } catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
    }
}
