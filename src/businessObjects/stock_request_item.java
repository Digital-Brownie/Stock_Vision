/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package businessObjects;

import databaseConnect.DatabaseConnection;
import java.sql.PreparedStatement;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.ObservableList;

/**
 *
 * @author Alex
 */
public class stock_request_item
{

    public stock_request_item(String requestItemName, String requestID, String StockID, Integer quantity)
    {
        this.requestItemName = new SimpleStringProperty (requestItemName);
        this.requestID = new SimpleStringProperty (requestID);
        this.StockID = new SimpleStringProperty (StockID);
        this.quantity = quantity;
    }
    SimpleStringProperty requestItemName, requestID, StockID;
    Integer quantity;
       
    public String getRequestItemName()
    {
        return requestItemName.get();
    }

    public void setRequestItemName(String ReceiptItemName)
    {
        this.requestItemName = new SimpleStringProperty(ReceiptItemName);
    }

    public Integer getQuantity()
    {
        return quantity;
    }

    public void setQuantity(Integer quantity)
    {
        this.quantity = quantity;
    }

    public String getRequestID()
    {
        return requestID.get();
    }

    public void setRequestID(String ReceiptID)
    {
        this.requestID = new SimpleStringProperty(ReceiptID);
    }

    public String getStockID()
    {
        return StockID.get();
    }

    public void setStockID(String StockID)
    {
        this.StockID = new SimpleStringProperty(StockID);
    }
        
    public static void insertRequestItemsTable(ObservableList<stock_request_item> newRequestList)
    {
        try
        {
            for (stock_request_item item : newRequestList)
            {                
                PreparedStatement INSERT = DatabaseConnection.connection.prepareStatement("Insert into stock_request_items(RequestItemName,Quantity, StockID, RequestID) VALUES ('" + item.getRequestItemName() + "', " + item.getQuantity() + ",'" + item.getStockID() + "', '" + item.getRequestID() + "');");
                INSERT.executeUpdate();
                
                //Stock_Item.decrementKey(item.getStockID());
            }

        } catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
    }
}
