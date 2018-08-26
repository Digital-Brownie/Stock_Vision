/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package businessObjects;

import databaseConnect.DatabaseConnection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.ObservableList;


/**
 *
 * @author Alex
 */
public class receipt
{

    
    SimpleStringProperty receiptID, customerName,  customerID;
    LocalDate transactionDate;

    public receipt(String receiptID, String customerID, String customerName, LocalDate transactionDate)
    {
        this.receiptID = new SimpleStringProperty("RCT" + receiptID);
        this.customerID = new SimpleStringProperty (customerID);
        this.customerName = new SimpleStringProperty(customerName);
        this.transactionDate = transactionDate;
    }

    public String getReceiptID()
    {
        return receiptID.get();
    }

    public void setReceiptID(String receiptID)
    {
        this.receiptID = new SimpleStringProperty(receiptID);
    }

    public String getCustomerID()
    {
        return customerID.get();
    }

    public void setCustomerID(String customerID)
    {
        this.customerID = new SimpleStringProperty (customerID);
    }

    public String getCustomerName()
    {
        return customerName.get();
    }

    public void setCustomerName(String customerName)
    {
        this.customerName = new SimpleStringProperty(customerName);
    }

    public LocalDate getArrivalDate()
    {
        return transactionDate;
    }

    public void setArrivalDate(LocalDate arrivalDate)
    {
        this.transactionDate = arrivalDate;
    }

    public static void insertReceiptTable(receipt newReceipt) throws Exception
    {
        try
        {
            System.out.println("receiptID: " +newReceipt.getReceiptID() + "custName: " + newReceipt.getCustomerName() + " ArrivalDate: " + newReceipt.getArrivalDate() + " CustomerID: " + newReceipt.getCustomerID());
            PreparedStatement INSERT = DatabaseConnection.connection.prepareStatement("Insert into receipts(receiptID, customerName, ArrivalDate, CustomerID )"
                    + " VALUES('"+newReceipt.getReceiptID()+"', ' " + newReceipt.getCustomerName() + " ', '" + newReceipt.getArrivalDate() + " ', '" + newReceipt.getCustomerID() + "')");

            INSERT.executeUpdate();
            
            Stock_Item.incrementKey(newReceipt.getReceiptID());
            
            
        } catch (Exception e)
        {
            System.out.println(e);
        }
    }
    
    
    
}
