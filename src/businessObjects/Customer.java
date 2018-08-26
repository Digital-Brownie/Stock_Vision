/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package businessObjects;
import databaseConnect.DatabaseConnection;
import java.sql.*;
import java.sql.PreparedStatement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.*;
/**
 *
 * @author Alex
 */
public class Customer
{
   DatabaseConnection conn = new DatabaseConnection();
        
    public SimpleStringProperty custCode;
    protected SimpleStringProperty custName;
    public SimpleStringProperty custCompany;

    public Customer(String custCode, String custName, String custCompany)
    {
        this.custCode = new SimpleStringProperty (custCode);
        this.custName = new SimpleStringProperty (custName);
        this.custCompany = new SimpleStringProperty (custCompany);
    }
        
    public String getCustCode()
    {
        return custCode.get();
    }

    public void setCustCode(String custCode)
    {
        this.custCode = new SimpleStringProperty (custCode);
    } 

    public String getCustName()
    {
        return custName.get();
    }

    public void setCustName(String custName)
    {
        this.custName = new SimpleStringProperty(custName);
    }

    public String getCustCompany()
    {
        return custCompany.get();
    }

    public void setCustCompany(String custCompany)
    {
        this.custCompany = new SimpleStringProperty(custCompany);
    }
         
    public static Customer getCustomer(String custID)
    {
        ResultSet result;
        Customer cust = null;
        try
        {
            PreparedStatement SELECT = DatabaseConnection.getConnection().prepareStatement("SELECT * FROM customer WHERE customerID = '"+custID+"';");
             result = SELECT.executeQuery();
             while (result.next())
            {
                cust = new Customer(result.getString("customerID"), result.getString("customerName"), result.getString("customerCompany"));
            }                                     
            
            return cust;
        } catch (Exception e)
        {
            System.out.println(e.getMessage());
            return null;
        }
                
    }
    
    public static ObservableList<Customer> getCustomers() throws Exception
    {
        ResultSet result;
        ObservableList<Customer> customers = FXCollections.observableArrayList();
        
        try
        {
            PreparedStatement SELECT = DatabaseConnection.getConnection().prepareStatement("SELECT * FROM customer;");
            result = SELECT.executeQuery();
            
            while (result.next())
            {
                customers.add(new Customer(result.getString("customerID"),result.getString("customerName"),result.getString("customerCompany")));
                
                //System.out.printf("%s %s %s%n", result.getString("custCode"),result.getString("custName"),result.getString("custCompany"));
            }
        } catch (SQLException ex)
        {
            //Logger.getLogger(DatabaseConnection.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println(ex.getMessage());
        }
        return customers;
    }
    
    public static void addCustomer(String ID, String name, String company)
    {
        try
        {
            PreparedStatement addCust = DatabaseConnection.getConnection().prepareStatement("insert into customer(customerID, customerName, customerCompany) VALUES('"+ID+"', '"+name+"', '"+company+"');");
            addCust.executeUpdate();
            System.out.println("Add customer complete");
            
        } catch (Exception ex)
        {
            System.out.println(ex.getMessage());
        }
        
        
        //Customer newCust = new Customer(name, company);                
    }
    
    public static void alterCustomerName(String oldValue, String newValue) 
    {
        try
        {
            PreparedStatement update = DatabaseConnection.getConnection().prepareStatement("update customer set customerName = '"+newValue+"' where customerName = '"+oldValue+"';");
            update.executeUpdate();                                                                                       
            System.out.println("alter customer complete");
        } catch (Exception ex)
        {
            System.out.println(ex.getMessage());
            System.out.println("failed to update table");
        }
    }
    
    public static void alterCompanyName(String oldValue, String newValue) 
    {
        try
        {
            PreparedStatement update = DatabaseConnection.getConnection().prepareStatement("update customer set customerCompany = '"+newValue+"' where customerCompany = '"+oldValue+"';");
            update.executeUpdate();                                                                                       
            System.out.println("update complete");
        } catch (Exception ex)
        {
            Logger.getLogger(Customer.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("failed to update table");
        }
    }
    
    public static void deleteCustomer(String custCode) throws Exception
    {
        PreparedStatement delete = DatabaseConnection.getConnection().prepareStatement("DELETE from customer WHERE customerID =' "+custCode+" ';");
        delete.executeUpdate();                            
    } 
    
    public static void incrementKey(String itemCode) throws SQLException
    {
        String code = itemCode.substring(0, 3);        
        
        PreparedStatement UPDATE = DatabaseConnection.connection.prepareStatement("update invento_systems.keys set "+code+" = "+code+" + 1 Where keyID = 1; ");
        UPDATE.executeUpdate();
    }
    
    public static String getKey() throws SQLException
    {
        Integer key = -1;
        try
        {
            PreparedStatement getKey = DatabaseConnection.connection.prepareStatement("SELECT CUS FROM invento_systems.keys WHERE keyID = 1;");
            ResultSet result = getKey.executeQuery();
            
            while (result.next())
            {
                key = result.getInt("CUS");
            }
            
            incrementKey("CUS");
        } catch (SQLException e)
        {
            System.out.println("e: " + e);
            System.out.println("Message: " + e.getMessage());
        }

        return String.format("%03d",key) ;
    }
}
