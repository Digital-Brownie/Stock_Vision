///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package Invento.StockVision;
//
////import static com.sun.corba.se.impl.util.Utility.printStackTrace;
//import java.io.IOException;
//import java.net.URL;
//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.util.ResourceBundle;
//import java.util.logging.Level;
//import java.util.logging.Logger;
//import javafx.event.ActionEvent;
//import javafx.fxml.FXML;
//import javafx.fxml.FXMLLoader;
//import javafx.fxml.Initializable;
//import javafx.geometry.Pos;
//import javafx.scene.Node;
//import javafx.scene.Parent;
//import javafx.scene.Scene;
//import javafx.scene.control.Alert;
//import javafx.scene.control.Alert.AlertType;
//import javafx.scene.control.Button;
//import javafx.scene.control.Label;
//import javafx.scene.control.PasswordField;
//import javafx.scene.control.TextField;
//import javafx.scene.image.Image;
//import javafx.scene.layout.AnchorPane;
//import javafx.scene.layout.Pane;
//import javafx.stage.Stage;
//import javafx.stage.StageStyle;
//import javax.management.Notification;
//import javax.swing.JOptionPane;
//
///**
// *
// * @author danml
// */
//public class FXMLDocumentController implements Initializable {
//
//    public void start(Stage stage) throws Exception {
//        Parent root = FXMLLoader.load(getClass().getResource("/view/Login.fxml"));
//        Scene scene = new Scene(root); 
//        stage.setScene(scene);
//        stage.getIcons().add(new Image("/image/STOCKVISION LOGO V1.0.png"));
//        stage.setTitle("Invento Systems - StockVision V1.0");
//        stage.show();
//    }    
//   
//    @Override
//    public void initialize(URL url, ResourceBundle rb) {
//        
//    }    
//    
//}
