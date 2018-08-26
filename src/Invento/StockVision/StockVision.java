/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package invento.stockvision;


import controller.MainMenu;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 *
 * @author rifat
 */
public class StockVision extends Application {
    
    @Override
    public void start(Stage primaryStage) throws Exception {
        

//        Parent root = FXMLLoader.load(getClass().getResource("/view/MainMenu.fxml"));
//        
//        Scene scene = new Scene(root);
//        primaryStage.setScene(scene);
//        primaryStage.getIcons().add(new Image("/image/STOCKVISION LOGO V1.0.png"));
//        primaryStage.setTitle("StockVision V1.0 - Main Menu");
//        primaryStage.show();


        try
        {
            Parent root = FXMLLoader.load(getClass().getResource("/view/FXMLDocumentFXML_StockOptions_Main.fxml"));
        
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.getIcons().add(new Image("/image/STOCKVISION LOGO V1.0.png"));
        primaryStage.setTitle("StockVision V1.0 - Main Menu");
        primaryStage.show();
        } catch (IOException e)
        {
            System.out.println(e.getMessage());
        }

    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
