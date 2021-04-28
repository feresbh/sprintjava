/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.gui;

import app.entities.Voiture;
import app.services.VoitureCrud;
import java.io.File;
import java.io.IOException;
import static java.lang.Integer.parseInt;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Spinner;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import app.gui.Notifications;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import static java.util.Arrays.stream;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.ComboBox;
import javafx.scene.paint.Color;
import javax.swing.JOptionPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * FXML Controller class
 *
 * @author HP
 */
public class AddVoitureController implements Initializable {

    @FXML
    private TextField tfmarque;
    private TextField tfmodel;
    private TextField tfcouleur;
    @FXML
    private TextField tfserie;
    @FXML
    private TextField tfprix;
    @FXML
    private TextField tfimage;
    @FXML
    private Button btnajouter;
    @FXML
    private ImageView image_update;
    @FXML
    private Button importer;
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
    private Button btnblog;
    @FXML
    private Button btnutilisateurs;
    @FXML
    private Hyperlink logoutLink;
    @FXML
    private ImageView logo1;
    @FXML
    private ColorPicker mycolorpicker;
    @FXML
    private ComboBox cb_model;
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }   
    
        
    

    @FXML
    private void ajouter(ActionEvent event) {
        
            if(tfmarque.getText().equals("")){
                JOptionPane.showMessageDialog(null, "il faut remplir le champs marque");
            }
            /*else if(tfmodel.getText().equals("")){
                JOptionPane.showMessageDialog(null, "il faut remplir le champs model");
            }*/
            else if(tfserie.getText().equals("")){
                JOptionPane.showMessageDialog(null, "il faut remplir le champs serie");
            }
            else if(tfprix.getText().equals("")){
                JOptionPane.showMessageDialog(null, "il faut remplir le champs prix");
            }
            else{
                try {
                    System.out.println("je suis la 1");
                    // sauvgarde dans la base
                    VoitureCrud vc = new VoitureCrud();
                    Voiture v = new Voiture();
                    
                    
                    Color mycolor = mycolorpicker.getValue();
                    
                    String tmarque = tfmarque.getText();
                    String tmodel = cb_model.getSelectionModel().getSelectedItem().toString();
                    String tserie = tfserie.getText();
                    String tprix = tfprix.getText();
                    String timage = tfimage.getText();

                    v.setMarque(tmarque);
                    v.setModel(tmodel);
                    v.setCouleur(mycolor.toString());
                    v.setDiponibilite(true);
                    v.setSerie(tserie);
                    v.setPrix(parseInt(tprix));
                    v.setImage(timage);
                    vc.addVoiture2(v);
                    
                    //redirection vers la page show

                    FXMLLoader loader = new FXMLLoader(getClass().getResource("tableVoiture.fxml"));
                    Parent root = loader.load();
                    TableVoitureController dc = loader.getController();

                    //vc.getVoitures();

                    dc.setResmarque(tfmarque.getText());
                    /*
                    dc.setResdisponibilite(tfserie.getText());
                    dc.setResserie(tfserie.getText());
                    dc.setResprix(tfprix.getText());
                    dc.setResimage(tfimage.getText());*/

                    tfmarque.getScene().setRoot(root);

                } catch (IOException ex) {
                    System.out.println(ex.getMessage());
                }
            }
        
    }

    @FXML
    private void importerImageUpdate(ActionEvent event) {
        
        JFileChooser chooser = new JFileChooser();
        chooser.showOpenDialog(null);
        File f = chooser.getSelectedFile();
        String filename = f.getName();
        tfimage.setText(filename);
        Image getAbsolutePath = null;
        ImageIcon icon = new ImageIcon(filename);
        InputStream stream = null;
        
        try {
            stream = new FileInputStream("C:\\Users\\HP\\Documents\\school\\SEM2\\PiDev\\SprintWeb\\public\\images\\voiture\\"+filename+"");
        } catch (FileNotFoundException ex) {
            Logger.getLogger(TableVoitureController.class.getName()).log(Level.SEVERE, null, ex);
        }
        Image image = new Image(stream);
        image_update.setImage(image);
        
        
   }

    @FXML
    private void voitures(MouseEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("tableVoiture.fxml"));
            Parent root = loader.load();
            TableVoitureController tv = loader.getController();
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
    private void blog(MouseEvent event) {
    }

    @FXML
    private void utilisateurs(MouseEvent event) {
    }

 

    @FXML
    private void cbset(MouseEvent event) {
        String marque =tfmarque.getText();
         

        ObservableList<String> list;
        if (marque.equalsIgnoreCase("mercedes")){
            list = FXCollections.observableArrayList("classe a","classe b","classe c","classe e","classe s");
            cb_model.setItems(list);
        }
        else if (marque.equalsIgnoreCase("bmw")){
            list = FXCollections.observableArrayList("serie 1","serie 3","serie 4","serie 5","x2","x3","x5");
            cb_model.setItems(list);
        }
        else if (marque.equalsIgnoreCase("audi")){
            list = FXCollections.observableArrayList("Q2","Q3","A3","A4","A5");
            cb_model.setItems(list);
        }
        else if (marque.equalsIgnoreCase("citroen")){
            list = FXCollections.observableArrayList("C3","c elysee","C4");
            cb_model.setItems(list);
        }
        else if (marque.equalsIgnoreCase("ford")){
            list = FXCollections.observableArrayList("fiesta","focus","ecosport");
            cb_model.setItems(list);
        }
        else if (marque.equalsIgnoreCase("hyundai")){
            list = FXCollections.observableArrayList("Grand I10","I20","Elentra");
            cb_model.setItems(list);
        }
        else if (marque.equalsIgnoreCase("kia")){
            list = FXCollections.observableArrayList("Picanto","Rio","Sportage","Seltos");
            cb_model.setItems(list);
        }
        else if (marque.equalsIgnoreCase("nissan")){
            list = FXCollections.observableArrayList("Micra","Juke","Qashqai");
            cb_model.setItems(list);
        }
        else if (marque.equalsIgnoreCase("peugeot")){
            list = FXCollections.observableArrayList("208","301","308","2008","3008");
            cb_model.setItems(list);
        }
        else if (marque.equalsIgnoreCase("renault")){
            list = FXCollections.observableArrayList("Symbol","Clio 4","Megane","Captur","Kadjar","Talisman");
            cb_model.setItems(list);
        }
        else if (marque.equalsIgnoreCase("seat")){
            list = FXCollections.observableArrayList("Ibiza","Leon","Arona","Ateca","Tarraco");
            cb_model.setItems(list);
        }
        else if (marque.equalsIgnoreCase("Skoda")){
            list = FXCollections.observableArrayList("Fabia","Scala","Kamiq","Octavia");
            cb_model.setItems(list);
        }
        else if (marque.equalsIgnoreCase("suzuki")){
            list = FXCollections.observableArrayList("Celerio","Dzire","Swift","Baleno");
            cb_model.setItems(list);
        }
        else if (marque.equalsIgnoreCase("toyota")){
            list = FXCollections.observableArrayList("Yaris","Corolla","C-HR","Rav 4","Land Cruiser");
            cb_model.setItems(list);
        }
        else if (marque.equalsIgnoreCase("volkswagen")){
            list = FXCollections.observableArrayList("Polo","Golf 7","Passat","Tguan","Touareg");
            cb_model.setItems(list);
        }
        else {
            list = FXCollections.observableArrayList(cb_model.getEditor().getText());
            cb_model.setItems(list);
        }
    }

    @FXML
    private void logout(ActionEvent event) {
    }
    
}
