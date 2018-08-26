/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 *
 * @author dylan
 */
public class MainMenu 
{

    @FXML
    private Button btnCustomers;

    @FXML
    private Button btnSuppliers;

    @FXML
    private Button btnStock;

    @FXML
    private Button btnEmployees;

    @FXML
    private void btnCustomersPressed(ActionEvent event) throws Exception
    {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        try
        {
            Parent Window = FXMLLoader.load(getClass().getResource("/view/FXMLDocumentFXML_StockOptions_Main.fxml"));

            Scene scene = new Scene(Window);

            stage.setScene(scene);
            stage.show();
        } catch (Exception e)
        {
            System.out.println("e : " + e);
            System.out.println("Message : " + e.getMessage());
        }
       
        
    }

   
}
