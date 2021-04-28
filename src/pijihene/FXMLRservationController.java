/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pijihene;

import entities.reservation;
import entities.vol;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Pagination;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import org.controlsfx.control.Notifications;
import services.ReservationServices;
import services.volservices;
import utils.datasource;

/**
 * FXML Controller class
 *
 * @author Admin
 */
public class FXMLRservationController implements Initializable {

    Statement st;
    PreparedStatement pst;
    ResultSet res;
     ResultSet res1;

    static datasource ds = datasource.getInstance();
    @FXML
    private TableView<reservation> tablereservation;
    @FXML
    private TableColumn<reservation, Integer> idtable;
    @FXML
    private TableColumn<reservation, Integer> voltable;
    @FXML
    private TableColumn<reservation, Integer> cintable;
    @FXML
    private TableColumn<reservation, Date> datetable;
    @FXML
    private TableColumn<reservation, Integer> placetable;
    @FXML
    private TableColumn<reservation, String> mailtable;
    @FXML
    private ComboBox<Integer> vol_id;
    @FXML
    private TextField cin;
    @FXML
    private DatePicker date_v;
    @FXML
    private TextField num_p;
    @FXML
    private TextField email;
    @FXML
        private ComboBox<Integer> user;
    @FXML
    private TableColumn<reservation, Integer> usertab;
    @FXML
    private TableColumn<reservation, Double> prixtab;
    @FXML
    private TextField prix;
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
    private Pagination pagination;
     private final static int rowsPerPage = 10;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            afficheReservation();
            String requete = "SELECT * FROM vol ";
            String requete1 = "SELECT * FROM user ";
            PreparedStatement ste = ds.getConnection().prepareStatement(requete);
         
                    
          
            
            
            
            res = ste.executeQuery(requete);
            while (res.next()) {

                vol_id.getItems().add(res.getInt("id"));
              pagination.setPageFactory(this::createPage); 

            }
            
             PreparedStatement ste1 = ds.getConnection().prepareStatement(requete1);
            res = ste.executeQuery(requete1);
            while (res.next()) {
                user.getItems().add(res.getInt("id"));

            }

        } catch (SQLException ex) {
            Logger.getLogger(FXMLRservationController.class.getName()).log(Level.SEVERE, null, ex);
            
        }
        
    }

    @FXML
    private void ajouterReservation(MouseEvent event) throws SQLException, UnsupportedEncodingException, IOException {
        ReservationServices rs = new ReservationServices();
        reservation re = new reservation();

      
        if (cin.getText().matches("[a-zA-Z]+") || cin.getText().matches("[         \\\\!\"#$%&() *+,./:;<=>?@\\[\\]^_{|}~ ]+") ||cin.getText().length()!=8){
            Notifications notificationBuilder = Notifications.create()
                    .title("Error!!! ")
                    .text("Verifier Cin!")
                    .darkStyle()
                    .graphic(null)
                    .hideAfter(javafx.util.Duration.seconds(8))
                    .position(Pos.TOP_CENTER);
            notificationBuilder.showError();
        } else{
        re.setVol_id((vol_id.getValue()));
        re.setUser_id((user.getValue()));
        re.setCin(Integer.parseInt(cin.getText()));
        re.setNum_p(Integer.parseInt(num_p.getText()));
        java.sql.Date gettedDatePickerDate = java.sql.Date.valueOf(date_v.getValue());
        re.setDate_v(gettedDatePickerDate);

        re.setEmail(email.getText());
        int a=rs.userres(re);
        double prixpromo=Double.parseDouble(prix.getText());
        if(a==4){
         prixpromo=prixpromo*0.75;
        
        }
        re.setPrix_res(prixpromo);
        rs.AddReservation(re);
        pagination.setPageFactory(this::createPage); 
        }
      
    }


    @FXML
    private void modifierReservation(MouseEvent event) throws SQLException {

        ReservationServices rs = new ReservationServices();
        java.sql.Date gettedDatePickerDate = java.sql.Date.valueOf(date_v.getValue());

        reservation re = tablereservation.getSelectionModel().getSelectedItem();

        
  
        
        re.setVol_id((vol_id.getValue()));
        re.setCin(Integer.parseInt(cin.getText()));
        re.setNum_p(Integer.parseInt(num_p.getText()));

        re.setDate_v(gettedDatePickerDate);

        re.setEmail(email.getText());
         re.setUser_id((user.getValue()));
         re.setPrix_res(Double.parseDouble(prix.getText()));

        rs.UpdatReservation(re);
        pagination.setPageFactory(this::createPage); 
    }

    @FXML
    private void supprimerReservation(MouseEvent event) throws SQLException {
        ReservationServices rs = new ReservationServices();
        reservation re = tablereservation.getSelectionModel().getSelectedItem();

        rs.DeleteReservation(re);
       pagination.setPageFactory(this::createPage); 
    }

    public void afficheReservation()  {
        ReservationServices rs = new ReservationServices();
        ObservableList<reservation> re;

        try {
            re = rs.afficheReservation();
         idtable.setCellValueFactory(new PropertyValueFactory<reservation, Integer>("id"));
        voltable.setCellValueFactory(new PropertyValueFactory<reservation, Integer>("vol_id"));
        cintable.setCellValueFactory(new PropertyValueFactory<reservation, Integer>("cin"));
        datetable.setCellValueFactory(new PropertyValueFactory<reservation, Date>("date_v"));
        placetable.setCellValueFactory(new PropertyValueFactory<reservation, Integer>("num_p"));
        mailtable.setCellValueFactory(new PropertyValueFactory<reservation, String>("email"));
          usertab.setCellValueFactory(new PropertyValueFactory<reservation, Integer>("user_id"));
          prixtab.setCellValueFactory(new PropertyValueFactory<reservation, Double>("prix_res"));
        tablereservation.setItems(re);
        } catch (SQLException ex) {
            Logger.getLogger(FXMLRservationController.class.getName()).log(Level.SEVERE, null, ex);
        }
       

    }
     private Node createPage(int pageIndex)  {
       ReservationServices rs = new ReservationServices();
        ObservableList<reservation> list;
        try {
            list = rs.afficheReservation();
       int fromIndex = pageIndex * rowsPerPage;
    int toIndex = Math.min(fromIndex + rowsPerPage, list.size());
    tablereservation.setItems(FXCollections.observableArrayList(list.subList(fromIndex, toIndex)));
    
        } catch (SQLException ex) {
            Logger.getLogger(FXMLRservationController.class.getName()).log(Level.SEVERE, null, ex);
        }
    return tablereservation;
    }


    @FXML
    private void handelMouseAction(MouseEvent event) throws SQLException {
        ReservationServices rs = new ReservationServices();
        reservation re = tablereservation.getSelectionModel().getSelectedItem();

      
       
           vol_id.setValue(re.getVol_id());
        user.setValue(re.getUser_id());
        cin.setText(String.valueOf(re.getCin()));
        num_p.setText(String.valueOf(re.getNum_p()));
        email.setText(re.getEmail());
        prix.setText(String.valueOf(re.getPrix_res()));
        LocalDate dc = re.getDate_v().toLocalDate();
        dc.getYear();
        dc.getMonth();
        dc.getDayOfMonth();

        date_v.setValue(LocalDate.of(dc.getYear(), dc.getMonth(), dc.getDayOfMonth()));

    }
    
        @FXML
    private void afficheprix(MouseEvent event) throws SQLException {
        int idvol=vol_id.getValue();
         String requete = "SELECT * FROM vol where id="+idvol;
            PreparedStatement ste = ds.getConnection().prepareStatement(requete);
          
            
            
            
            res = ste.executeQuery(requete);
            res.next();
            double p=res.getDouble("prix");
            prix.setText(String.valueOf(p));
            System.out.println(p);
    }

    @FXML
    private void voitures(MouseEvent event) {
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
    private void logout(ActionEvent event) {
    }

    @FXML
    private void gotostats(ActionEvent event) throws IOException {
          Stage primaryStage = new Stage();

        Parent root = new FXMLLoader().load(getClass().getResource("FXMLStats.fxml"));
        Scene scene = new Scene(root);
        primaryStage.setTitle("Our Users");
        primaryStage.setScene(scene);
        primaryStage.show();
        ((Node) event.getSource()).getScene().getWindow().hide();

    }

}
