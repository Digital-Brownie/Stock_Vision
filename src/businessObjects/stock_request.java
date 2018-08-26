/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package businessObjects;

import databaseConnect.DatabaseConnection;
import java.sql.PreparedStatement;
import java.time.LocalDate;
import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author Alex
 */
public class stock_request
{
    SimpleStringProperty requestID, customerName,  customerID;
    LocalDate transactionDate;

    public stock_request(String requestID, String customerName, String customerID, LocalDate transactionDate)
    {
        this.requestID = new SimpleStringProperty ("REQ" + requestID);
        this.customerName = new SimpleStringProperty (customerName);
        this.customerID = new SimpleStringProperty (customerID);
        this.transactionDate = transactionDate;
    }

    public String getRequestID()
    {
        return requestID.get();
    }

    public void setRequestID(String requestID)
    {
        this.requestID = new SimpleStringProperty(requestID);
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
    
    
    public static void insertStockRequestTable(stock_request newRequest) throws Exception
    {
        try
        {
            System.out.println("requestID: " +newRequest.getRequestID() + "custName: " + newRequest.getCustomerName() + " ArrivalDate: " + newRequest.getArrivalDate() + " CustomerID: " + newRequest.getCustomerID());
            PreparedStatement INSERT = DatabaseConnection.connection.prepareStatement("Insert into stock_request(requestID, customerName, ArrivalDate, CustomerID )"
                    + " VALUES('"+newRequest.getRequestID()+"', '" + newRequest.getCustomerName() + "', '" + newRequest.getArrivalDate() + "', '" + newRequest.getCustomerID() + "')");

            INSERT.executeUpdate();
            
            Stock_Item.incrementKey(newRequest.getRequestID());
            
            
        } catch (Exception e)
        {
            System.out.println(e);
        }
    }
    
}
