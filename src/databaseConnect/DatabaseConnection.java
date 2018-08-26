/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package databaseConnect;

import java.sql.*;

/**
 *
 * @author Alex
 */
public class DatabaseConnection
{
    public static Connection connection;
    public static Connection getConnection() throws Exception
    {

        final String DATABASE_URL = "jdbc:mysql://localhost:3306/invento_systems";
        try
        {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException e)
        {
            System.out.println("Could not load driver.");
        }

        try
        {
            connection = DriverManager.getConnection(DATABASE_URL, "root", "password");
            System.out.println("connected to DB");
        } catch (SQLException e)
        {
            System.out.println("could not connect to DB");
            System.out.println("bitch");
        }
        
        return connection;
    }
}
