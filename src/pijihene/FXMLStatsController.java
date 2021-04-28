/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pijihene;

import entities.reservation;
import entities.stats;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import services.ReservationServices;

/**
 * FXML Controller class
 *
 * @author Slim
 */
public class FXMLStatsController implements Initializable {


  
    @FXML
    private AnchorPane anchorstat;
 
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        /// STATISTIQUES SUR LA VENTE DE PRODUITS PAR RESPONSABLE 
    final CategoryAxis xAxis = new CategoryAxis();
        final NumberAxis yAxis = new NumberAxis();
        final BarChart<String,Number> bc = 
            new BarChart<String,Number>(xAxis,yAxis);
        bc.setTitle("Reservation Summary");
        xAxis.setLabel("Date des reservation");       
        yAxis.setLabel("Valeurs");
 
        XYChart.Series series1 = new XYChart.Series();
        series1.setName("Reservation Par date");  
         ReservationServices bs=new ReservationServices();
        
        try {
            List<stats>Stat=bs.StatsData();
              for (int i = 0; i < Stat.size(); i++)
              {
            series1.getData().add(new XYChart.Data(Stat.get(i).getDate().toString(),Stat.get(i).getCount()));
        }
        } catch (SQLException ex) {
            Logger.getLogger(FXMLStatsController.class.getName()).log(Level.SEVERE, null, ex);
        }
      
 
         
        
       
   
       
        bc.getData().addAll(series1);
        bc.setTranslateY(120);
        anchorstat.getChildren().add(bc);
    }    

    @FXML
    private void gotoreservation(ActionEvent event) throws IOException {
         Stage primaryStage = new Stage();

        Parent root = new FXMLLoader().load(getClass().getResource("FXMLRservation.fxml"));
        Scene scene = new Scene(root);
        primaryStage.setTitle("Our Users");
        primaryStage.setScene(scene);
        primaryStage.show();
        ((Node) event.getSource()).getScene().getWindow().hide();

    }
    
}
