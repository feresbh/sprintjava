/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.gui;

import app.entities.voiture_louee;
import app.services.voiture_loueeCrud;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author HP
 */
public class TableLocationsVController implements Initializable {

    @FXML
    private TableColumn<voiture_louee, String> col_id;
    @FXML
    private TableColumn<voiture_louee, Date> col_debut;
    @FXML
    private TableColumn<voiture_louee, Date> col_fin;
    @FXML
    private TableColumn<voiture_louee, String> col_serie;
    @FXML
    private TableColumn<voiture_louee, String> col_client;
    @FXML
    private TableColumn<voiture_louee, String> col_voiture;
    @FXML
    private TableColumn<voiture_louee, String> col_prix;
    @FXML
    private TableView<voiture_louee> table;
    @FXML
    private Button supploc;
    @FXML
    private Hyperlink logoutLink;
    @FXML
    private ImageView logo;
    @FXML
    private Button btnvoitures;
    @FXML
    private Button btnhotels;
    @FXML
    private Button btnvols;
    @FXML
    private Button btncamping;
    @FXML
    private Button btnbon_plans;
    @FXML
    private Button btnhome;
    @FXML
    private Button btnblog;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        showCat();
    }
    public void showCat(){
        
            voiture_loueeCrud vlc = new voiture_loueeCrud();
            ObservableList<voiture_louee> locations=vlc.getlocations();
            //ObservableList<Voiture> voiturelist= getVoitures();

            col_id.setCellValueFactory(new PropertyValueFactory<>("id"));
            col_debut.setCellValueFactory(new PropertyValueFactory<>("datedebut"));
            col_fin.setCellValueFactory(new PropertyValueFactory<>("datefin"));
            //col_debut.setCellFactory(new PropertyValueFactory<>("datedebut"));
            //col_fin.setCellFactory(new PropertyValueFactory<>("datefin"));
            col_serie.setCellValueFactory(new PropertyValueFactory<>("serie"));
            col_client.setCellValueFactory(new PropertyValueFactory<>("clientid"));
            col_voiture.setCellValueFactory(new PropertyValueFactory<>("voiture_id"));
            col_prix.setCellValueFactory(new PropertyValueFactory<>("prix"));

            table.setItems(locations);
        }   

    @FXML
    private void supprimerlocation(MouseEvent event) {
        try {
            voiture_loueeCrud vlc = new voiture_loueeCrud();
            voiture_louee vl = table.getSelectionModel().getSelectedItem();
            vlc.Deletelocation(vl);
            table.getItems().clear();
            showCat();
        } catch (SQLException ex) {
            Logger.getLogger(TableLocationsVController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void logout(ActionEvent event) {
    }

    @FXML
    private void voitures(MouseEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("location_voiture.fxml"));
            Parent root = loader.load();
            Location_voitureController tv = loader.getController();
            btnvoitures.getScene().setRoot(root);
        } catch (IOException ex) {
            Logger.getLogger(Main_dashController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void hotels(MouseEvent event) {
    }

    @FXML
    private void vols(MouseEvent event) {
    }

    @FXML
    private void camping(MouseEvent event) {
    }

    @FXML
    private void bon_plans(MouseEvent event) {
    }

    @FXML
    private void home(MouseEvent event) {
    }

    @FXML
    private void blog(MouseEvent event) {
    }
    
}
