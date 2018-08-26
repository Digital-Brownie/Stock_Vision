/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package databaseConnect;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author dylan
 */
public class dbConnect {
    
    Connection connection;
    PreparedStatement preparedStatement;
    boolean connected = false;
    
    Properties properties = new Properties();
    InputStream inputStream;
    String data;
    
    final String DATABASE_URL = "jdbc:mysql://localhost:3306/users";

    public void loadPropertiesFile(){
        try {
            inputStream = new FileInputStream("database.properties");
            properties.load(inputStream);
            data = properties.getProperty("db");
        } catch (IOException e) {
            System.out.println("Error!");
        }
    }
    
    public Connection connect() {

        //Verifies driver and establishes connection
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            connected = true;
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException f) {
            System.out.println("Cannot load driver!");
            connected = false;
        }

        //Get connection to database
        try {
            connection = DriverManager.getConnection(DATABASE_URL, "root", "password");
            System.out.println("CONNECTION SUCCESSFUL");
            connected = true;
        } catch (SQLException f) {
            System.out.println("Cannot connect to database!");
            connected = false;
        }
        
        return connection;
    }
    
}
