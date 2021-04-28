/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.gui;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 *
 * @author HP
 */
public class StartPoint extends Application {
    
    @Override
    public void start(Stage primaryStage) {
        
        try {
            //Parent root = FXMLLoader.load(getClass().getResource("AddVoiture.fxml"));
            Parent root = FXMLLoader.load(getClass().getResource("tableVoiture.fxml"));
            //Parent root = FXMLLoader.load(getClass().getResource("location_voiture.fxml"));
            //Parent root = FXMLLoader.load(getClass().getResource("main_dash.fxml"));
            
            Scene scene = new Scene(root);
            
            primaryStage.setTitle("ajout voiture");
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException ex) {
            Logger.getLogger(StartPoint.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
