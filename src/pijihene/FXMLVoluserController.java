/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pijihene;

import entities.vol;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.transitions.hamburger.HamburgerBackArrowBasicTransition;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.controlsfx.control.Rating;
import services.volservices;

/**
 * FXML Controller class
 *
 * @author Slim
 */

public class FXMLVoluserController implements Initializable {

    @FXML
    private AnchorPane ListAnchorPane;
    @FXML
    private JFXDrawer drawer;
    @FXML
    private ScrollPane ScrollPane1;
    @FXML
    private GridPane gridP;
    @FXML
    private JFXHamburger hamburger;
    @FXML
    private Button panier;

    public static int id2;
    
   public  int Id;
    @FXML
    private AnchorPane growPane;

    public int getId() 
    {
        return Id;
    }

    public void setId(int Id)
    {
        this.Id = Id;
    }
   
    



    @FXML
    private Text testText;
  
 public void setText(String p)
 {
     testText.setText(p);
 }
 
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        
    
       
      //  rootP=ListAnchorPane;
        


   
        


        volservices ps=new volservices();
        //System.out.println("..."+idStatic);       
        ScrollPane1.setStyle("-fx-background-color:transparent;");
        ScrollPane1.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        ScrollPane1.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        List<vol>lp;
        try {
            lp = ps.displayAllExposee();
         gridP.setHgap(600);
        gridP.setVgap(200);
        int cols = 2, colCnt = 0, rowCnt = 0;
        for (int i = 0; i < lp.size(); i++) {

               
            Image image = new Image("file:///C:\\Users\\Admin\\Desktop\\imagajava\\" + lp.get(i).getBrochure_filename());
            ImageView imageView = new ImageView(image);
            imageView.setTranslateY(20);
            imageView.setFitHeight(150);
            imageView.setFitWidth(150);
           
             HBox h = new HBox();
            Label label2 = new Label();
            label2.setStyle("-fx-font-size:18;");
            Text NomProd = new Text(lp.get(i).getAero_d());
            NomProd.setFont(Font.font("Arial", 20));
            NomProd.setStyle("-fx-fill:black");
            NomProd.setTranslateY(-50);
            label2.setText(lp.get(i).getAero_d());
            label2.setTranslateX(10);
            Label lb7 = new Label();
            lb7.setStyle("-fx-text-fill:black;-fx-font-size:20;");
            lb7.setText("$" + String.valueOf(lp.get(i).getId()));
            VBox v = new VBox();
            
            v.setStyle("-fx-background-color:white;-fx-background-radius:20;");


            Button addtocartt = new Button();
            addtocartt.setText("AddToCart");
            addtocartt.setFont(Font.font("Comic Sans MS", FontWeight.BOLD, 15));
            addtocartt.setTextFill(Paint.valueOf("white"));
            addtocartt.setStyle("-fx-background-color:#FFCD16;-fx-background-radius:30;");
            addtocartt.setPrefSize(100, 48);
            
            
            Text Price = new Text(lp.get(i).getPrix().toString());
            Price.setFont(Font.font("Ubuntu", 18));
            Price.setStyle("-fx-fill:black");
            Text Dollar = new Text("$");
            Dollar.setFont(Font.font("Ubuntu", FontWeight.BOLD, 17));
            Dollar.setStyle("-fx-fill:black");
            Dollar.setOpacity(0.5);
            Text DollarPromo = new Text("$");
            DollarPromo.setFont(Font.font("Ubuntu", FontWeight.BOLD, 17));
            DollarPromo.setStyle("-fx-fill:black");
            DollarPromo.setOpacity(0.5);
            
            imageView.addEventHandler(MouseEvent.MOUSE_ENTERED, (event) -> {
            imageView.setOpacity(0.3);
            
            });
            
            imageView.addEventHandler(MouseEvent.MOUSE_EXITED, (event) -> {
            imageView.setOpacity(1);
            
            });
            
            JFXButton ToCartButton = new JFXButton("Details");
            ToCartButton.setStyle("-fx-background-color:#8CD5EF;-fx-background-radius:0;-fx-min-width:120;");
            
            ToCartButton.setFont(Font.font("Arial", FontWeight.BOLD, 15));
            ToCartButton.setMinWidth(50);
            ToCartButton.setTranslateY(8);
            
           
            
            
            
  
      
            
            Label Separateur = new Label();
            
            Label Separateur2 = new Label();
            Separateur2.setStyle("-fx-background-color:black;-fx-min-height:1");
            Separateur2.setMaxHeight(2);
            Separateur2.setMinWidth(50);
            Separateur2.setTranslateX(-75);
            
            imageView.setTranslateY(-20);
            
            v.setTranslateY(24);
            v.setMinHeight(300);
            v.setMinWidth(180);
            
            v.getChildren().add(imageView);
            v.getChildren().add(Separateur);
            v.getChildren().add(Separateur2);

            
            v.getChildren().add(NomProd);
            HBox PriceDollar = new HBox();
            PriceDollar.getChildren().add(Dollar);
            PriceDollar.getChildren().add(Price);
            Dollar.setTranslateX(20);
            Price.setTranslateX(20);
           
            
            DollarPromo.setTranslateX(-7);
            PriceDollar.setAlignment(Pos.CENTER);
            
            v.getChildren().add(PriceDollar);
            v.getChildren().add(ToCartButton);
            
            v.setAlignment(Pos.CENTER);
            
            HBox ProdBox = new HBox();
            ProdBox.setStyle("-fx-background-color:white;");
            
            imageView.setTranslateY(20);
            ProdBox.setMinWidth(330);
            ProdBox.setMinHeight(200);

            ProdBox.getChildren().add(imageView);
            ProdBox.getChildren().add(Separateur);
            Separateur.setTranslateY(20);
            Separateur.setTranslateX(10);
            Separateur.setStyle("-fx-background-color:black;-fx-min-height:1");
            Separateur.setMaxHeight(150);
            Separateur.setMinWidth(3);
            
            NomProd.setTranslateY(15);
            NomProd.setTranslateX(20);
            PriceDollar.setTranslateX(-40);
            PriceDollar.setTranslateY(80);
            ToCartButton.setTranslateY(100);
                 ToCartButton.setTranslateX(20);
            
            VBox ProdInfo = new VBox();
            ProdInfo.getChildren().add(NomProd);
            ProdInfo.getChildren().add(PriceDollar);
            ProdInfo.getChildren().add(ToCartButton);
            ProdBox.getChildren().add(ProdInfo);
  
            
                       ProdBox.setStyle("-fx-background-color:gray");
                       ProdBox.setMinWidth(500);
            gridP.add(ProdBox, colCnt, rowCnt);
            
            colCnt++;
            if (colCnt > cols) {
            rowCnt++;
            colCnt = 0;
            growPane.setMinHeight(growPane.getMinHeight() + 250);
            }
                
                }
            
            
           
            

        
        } catch (SQLException ex) {
            Logger.getLogger(FXMLVoluserController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
      
     

    }    

   
    
}
