/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tuneasy.views;

import tuneasy.entities.Forum;
import tuneasy.services.PiCRUD;
import tuneasy.utils.Connection;
import java.io.File;
import java.net.URL;
import java.nio.file.Files;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author pc
 */
public class ForumDeatilsController implements Initializable {

    private TextField resnom;
    private TextField resemail;
    private TextField resdesc;
    private ImageView resimage;
    @FXML
    private TextField tid;
    @FXML
    private TextField tname;
    @FXML
    private TextField temail;
    @FXML
    private TextField tdescription;
    @FXML
    private TextField timage;
    @FXML
    private TableView<Forum> tvBooks;
    @FXML
    private TableColumn<Forum, Integer> colid;
    @FXML
    private TableColumn<Forum, String> colname;
    @FXML
    private TableColumn<Forum, String> colemail;
    @FXML
    private TableColumn<Forum, String> coldescription;
    @FXML
    private TableColumn<Forum, String> colimage;
    @FXML
    private Button btninsert;
    @FXML
    private Button btnupdate;
    @FXML
    private Button btndelete;
    @FXML
    private Button btnupload;

    /**
     * Initializes the controller class.
     */
    
    ObservableList<Forum> oblist=FXCollections.observableArrayList();
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        try {
                
        String requet ="select * FROM forum";
            Statement st =  Connection.getInstance().getConnection().createStatement();
            ResultSet rs= st.executeQuery(requet);
              while(rs.next()){
                  oblist.add(new Forum(rs.getString("id"),rs.getString("name"),rs.getString("email"),rs.getString("description"),rs.getString("image")));
              }
              
        } catch (SQLException ex) {
            Logger.getLogger(ForumDeatilsController.class.getName()).log(Level.SEVERE, null, ex);
        }
        colid.setCellValueFactory(new PropertyValueFactory<>("id"));
         colname.setCellValueFactory(new PropertyValueFactory<>("name"));
          colemail.setCellValueFactory(new PropertyValueFactory<>("email"));
           coldescription.setCellValueFactory(new PropertyValueFactory<>("description"));
               colimage.setCellValueFactory(new PropertyValueFactory<>("image"));
         //  table.setItems(oblist);
           
    }    

    

    /*@FXML
    private void initialize(ActionEvent event) {
       
        
    }*/
    
   // public ObservableList<Forum>

    @FXML
    private void insert(ActionEvent event) {
          PiCRUD f1 = new PiCRUD();
          Forum f = new Forum();
          String tNom=tname.getText();
          String tEmail=temail.getText();
          String tDesc=tdescription.getText();
          String tImg=timage.getText();
          f.setNom(tNom);
          f.setEmail(tEmail);
          f.setDescription(tDesc);
          f.setImage(tImg);
                   f1.addforum2(f); 
                   
                  
    }
       @FXML
    private void uploadImage(ActionEvent actionEvent) {
        
        Stage primary = new Stage();
        FileChooser fileChooser = new FileChooser();
       fileChooser.setTitle("Selectionner une image");
           fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif","*.jpeg"));
         File file = fileChooser.showOpenDialog(primary);
          String path = "C:\\User\\pc\\Descktop\\images";
          timage.setText(file.getName());
           if (file != null) {
            try {
                Files.copy(file.toPath(), new File(path + "\\" + file.getName()).toPath());
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }
    
}
