/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.gui;

import app.entities.Voiture;
import app.entities.voiture_louee;
import app.services.VoitureCrud;
import app.services.voiture_loueeCrud;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
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
import javafx.scene.control.DatePicker;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;


import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.Base64;
import javafx.scene.control.RadioButton;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author HP
 */
public class Location_voitureController implements Initializable {

    @FXML
    private TableColumn<Voiture, String> col_id;
    @FXML
    private TableColumn<Voiture, String> col_marque;
    @FXML
    private TableColumn<Voiture, String> col_model;
    @FXML
    private TableColumn<Voiture, String> col_couleur;
    @FXML
    private TableColumn<Voiture, String> col_serie;
    @FXML
    private TableColumn<Voiture, String> col_prix;
    @FXML
    private TableColumn<Voiture, ImageView> col_image;
    @FXML
    private TableView<Voiture> table;
    @FXML
    private TextField tf_marque;
    @FXML
    private TextField tf_model;
    @FXML
    private DatePicker date_debut;
    @FXML
    private DatePicker date_fin;
    @FXML
    private Button confirmer;

    Voiture v = new Voiture();
    @FXML
    private Button locations;
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
    @FXML
    private TextField num_sms;
    @FXML
    private RadioButton sms_check;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        showCat();
    }

    public void showCat() {
        VoitureCrud vc = new VoitureCrud();
        ObservableList<Voiture> voiturelist = vc.getVoitures();
        //ObservableList<Voiture> voiturelist= getVoitures();

        col_id.setCellValueFactory(new PropertyValueFactory<>("id"));
        col_marque.setCellValueFactory(new PropertyValueFactory<>("marque"));
        col_model.setCellValueFactory(new PropertyValueFactory<>("model"));
        col_couleur.setCellValueFactory(new PropertyValueFactory<>("couleur"));
        col_serie.setCellValueFactory(new PropertyValueFactory<>("serie"));
        col_prix.setCellValueFactory(new PropertyValueFactory<>("prix"));
        col_image.setCellValueFactory(new PropertyValueFactory<>("img"));

        table.setItems(voiturelist);
    }

    @FXML
    private void showdetail(MouseEvent event) {
        v = table.getSelectionModel().getSelectedItem();
        tf_marque.setText(v.getMarque());
        tf_model.setText(v.getModel());
    }

    static public void sms(String number, String marque) throws Exception {

    // This URL is used for sending messages
    String myURI = "https://api.bulksms.com/v1/messages";

    // change these values to match your own account
    String myUsername = "feres999";
    String myPassword = "Pidevsms@3a23";

    // the details of the message we want to send
    //String myData = "{to: \"21652515367\", encoding: \"UNICODE\", body: \"this is a test messsage\"}";

    // if your message does not contain unicode, the "encoding" is not required:
    String myData = "{to: \""+number+"\", body: \"Bonjour, Votre reservation de la voiture "+marque+" "
            + "a ete bien confirmer vous pouvez consulter votre espace reservation sur le site web pour plus de detaille.\"}";

    // build the request based on the supplied settings
    URL url = new URL(myURI);
    HttpURLConnection request = (HttpURLConnection) url.openConnection();
    request.setDoOutput(true);

    // supply the credentials
    String authStr = myUsername + ":" + myPassword;
    String authEncoded = Base64.getEncoder().encodeToString(authStr.getBytes());
    request.setRequestProperty("Authorization", "Basic " + authEncoded);

    // we want to use HTTP POST
    request.setRequestMethod("POST");
    request.setRequestProperty( "Content-Type", "application/json");

    // write the data to the request
    OutputStreamWriter out = new OutputStreamWriter(request.getOutputStream());
    out.write(myData);
    out.close();

    // try ... catch to handle errors nicely
    try {
      // make the call to the API
      InputStream response = request.getInputStream();
      BufferedReader in = new BufferedReader(new InputStreamReader(response));
      String replyText;
      while ((replyText = in.readLine()) != null) {
        System.out.println(replyText);
      }
      in.close();
    } catch (IOException ex) {
      System.out.println("An error occurred:" + ex.getMessage());
      BufferedReader in = new BufferedReader(new InputStreamReader(request.getErrorStream()));
      // print the detail that comes with the error
      String replyText;
      while ((replyText = in.readLine()) != null) {
        System.out.println(replyText);
      }
      in.close();
    }
    request.disconnect();
  }

    @FXML
    private void ajouterLocation(ActionEvent event) {

        voiture_loueeCrud vlc = new voiture_loueeCrud();
        String serie = "test";
        java.sql.Date debut = java.sql.Date.valueOf(date_debut.getValue());
        java.sql.Date fin = java.sql.Date.valueOf(date_fin.getValue());
        voiture_louee vl = new voiture_louee(debut, fin, serie, 1, v.getId(), 1);
        vlc.addlocation(vl, v);
        
        
        if(sms_check.isSelected()){
            try {
                sms(num_sms.getText(),v.getMarque());
            } catch (Exception ex) {
                Logger.getLogger(Location_voitureController.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
    }

    @FXML
    private void showlocations(MouseEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("TableLocationsV.fxml"));
            Parent root = loader.load();
            TableLocationsVController tv = loader.getController();
            locations.getScene().setRoot(root);
        } catch (IOException ex) {
            Logger.getLogger(Main_dashController.class.getName()).log(Level.SEVERE, null, ex);
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
