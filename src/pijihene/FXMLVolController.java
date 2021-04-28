/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pijihene;

import entities.AutoComplete;
import entities.vol;
import java.io.File;
import java.io.IOException;
import services.volservices;
import java.net.URL;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import org.controlsfx.control.Notifications;
import org.controlsfx.control.textfield.TextFields;

/**
 * FXML Controller class
 *
 * @author Admin
 */
public class FXMLVolController implements Initializable {

    @FXML
    private TableColumn<vol, Integer> idtab;
    @FXML
    private TableColumn<vol, Integer> id_voltab;
    @FXML
    private TableColumn<vol, String> aero_dtab;
    @FXML
    private TableColumn<vol, String> aero_atab;
    @FXML
    private TableColumn<vol, Date> date_dtab;
    @FXML
    private TableColumn<vol, Date> date_atab;
    @FXML
    private TableColumn<vol, Integer> place_dtab;
    @FXML
    private TableColumn<vol, Double> prixtab;
    @FXML
    private TableColumn<vol, String> imagetab;
    @FXML
    private TextField id;
    @FXML
    private TextField aero_d;
    @FXML
    private TextField aero_a;
    @FXML
    private DatePicker date_d;
    @FXML
    private DatePicker date_a;
    @FXML
    private TextField place_d;
    @FXML
    private TextField prix;
    @FXML
    private TextField image;
    @FXML
    private Button modifier;
    @FXML
    private Button supprimer;
    @FXML
    private Button ajouter;
    @FXML
    private TableView<vol> tablevol;
    @FXML
    private TextField searsh;
    @FXML
    private ImageView photo;
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

    private File selectedFile2;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            affichevol();

        } catch (SQLException ex) {
            Logger.getLogger(FXMLVolController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private File getImageFile() {
        return this.selectedFile2 = selectedFile2;
    }

    private void setImageFile(File file2) {
        this.selectedFile2 = file2;
    }

    public void affichevol() throws SQLException {
        volservices vs = new volservices();
        ObservableList<vol> p;

        p = vs.afficheVol();
        idtab.setCellValueFactory(new PropertyValueFactory<vol, Integer>("id"));
        id_voltab.setCellValueFactory(new PropertyValueFactory<vol, Integer>("id_v"));
        aero_dtab.setCellValueFactory(new PropertyValueFactory<vol, String>("aero_d"));
        aero_atab.setCellValueFactory(new PropertyValueFactory<vol, String>("aero_a"));
        date_dtab.setCellValueFactory(new PropertyValueFactory<vol, Date>("date_d"));
        date_atab.setCellValueFactory(new PropertyValueFactory<vol, Date>("date_a"));
        place_dtab.setCellValueFactory(new PropertyValueFactory<vol, Integer>("place"));
        prixtab.setCellValueFactory(new PropertyValueFactory<vol, Double>("prix"));
        imagetab.setCellValueFactory(new PropertyValueFactory<vol, String>("brochure_filename"));
        tablevol.setItems(p);

    }

    @FXML
    private void modifiervol(MouseEvent event) throws SQLException {
        volservices vs = new volservices();
        java.sql.Date gettedDatePickerDate = java.sql.Date.valueOf(date_a.getValue());
        java.sql.Date gettedDatePickerDate2 = java.sql.Date.valueOf(date_d.getValue());
        vol Vol = tablevol.getSelectionModel().getSelectedItem();

        Vol.setId_v(Integer.parseInt(id.getText()));
        Vol.setAero_d(aero_d.getText());
        Vol.setAero_a(aero_a.getText());

        Vol.setDate_a(gettedDatePickerDate);

        Vol.setDate_d(gettedDatePickerDate2);
        Vol.setPlace(Integer.parseInt(place_d.getText()));
        Vol.setPrix(Double.parseDouble(prix.getText()));
        Vol.setBrochure_filename(image.getText());

        vs.UpdatVol(Vol);
        tablevol.getItems().clear();
        affichevol();
    }

    @FXML
    private void supprimervol(MouseEvent event) throws SQLException {
        volservices vs = new volservices();
        vol v = tablevol.getSelectionModel().getSelectedItem();
        vs.DeleteVol(v);
        tablevol.getItems().clear();
        affichevol();
    }

    @FXML
    private void ajoutervol(MouseEvent event) throws SQLException {
        volservices vs = new volservices();
        vol ex = new vol();
        String testAeroD = aero_d.getText().replace(" ", "");
        String testAeroA = aero_a.getText().replace(" ", "");

        Date d = new Date();

        if (!testAeroD.matches("[a-zA-Z]+") || testAeroD.matches("[         \\\\!\"#$%&() *+,./:;<=>?@\\[\\]^_{|}~ ]+")) {
            Notifications notificationBuilder = Notifications.create()
                    .title("Error!!! ")
                    .text("Verifier aeroport départ!")
                    .darkStyle()
                    .graphic(null)
                    .hideAfter(javafx.util.Duration.seconds(8))
                    .position(Pos.TOP_CENTER);
            notificationBuilder.showError();
        } else if (place_d.getText().matches("[a-zA-Z]+") || place_d.getText().matches("[         \\\\!\"#$%&() *+,./:;<=>?@\\[\\]^_{|}~ ]+")) {
            Notifications notificationBuilder = Notifications.create()
                    .title("Error!!! ")
                    .text("Verifier place disponible")
                    .darkStyle()
                    .graphic(null)
                    .hideAfter(javafx.util.Duration.seconds(8))
                    .position(Pos.TOP_CENTER);
            notificationBuilder.showError();
        } else if (prix.getText().matches("[a-zA-Z]+") || prix.getText().matches("[         \\\\!\"#$%&() *+,./:;<=>?@\\[\\]^_{|}~ ]+")) {

            Notifications notificationBuilder = Notifications.create()
                    .title("Error!!! ")
                    .text("Verifier prix")
                    .darkStyle()
                    .graphic(null)
                    .hideAfter(javafx.util.Duration.seconds(8))
                    .position(Pos.TOP_CENTER);
            notificationBuilder.showError();
        } else if (!testAeroA.matches("[a-zA-Z]+") || testAeroA.matches("[         \\\\!\"#$%&() *+,./:;<=>?@\\[\\]^_{|}~ ]+")) {

            Notifications notificationBuilder = Notifications.create()
                    .title("Error!!! ")
                    .text("Verifier aeroport arrive")
                    .darkStyle()
                    .graphic(null)
                    .hideAfter(javafx.util.Duration.seconds(8))
                    .position(Pos.TOP_CENTER);
            notificationBuilder.showError();
        } else if (d.after(java.sql.Date.valueOf(date_d.getValue()))) {
            Notifications notificationBuilder = Notifications.create()
                    .title("Error!!! ")
                    .text("Verifier Date de depart")
                    .darkStyle()
                    .graphic(null)
                    .hideAfter(javafx.util.Duration.seconds(8))
                    .position(Pos.TOP_CENTER);
            notificationBuilder.showError();
        } else {

            ex.setId_v(Integer.parseInt(id.getText()));
            ex.setAero_d(aero_d.getText());
            ex.setAero_a(aero_a.getText());
            java.sql.Date gettedDatePickerDate = java.sql.Date.valueOf(date_d.getValue());
            ex.setDate_d(gettedDatePickerDate);
            java.sql.Date gettedDatePickerDate2 = java.sql.Date.valueOf(date_a.getValue());
            ex.setDate_a(gettedDatePickerDate2);
            ex.setPlace(Integer.parseInt(place_d.getText()));
            ex.setPrix(Double.parseDouble(prix.getText()));

            ex.setBrochure_filename(image.getText());
            File f = new File(getImageFile().getAbsolutePath());
            f.renameTo(new File("C:\\Users\\Admin\\Desktop\\imagajava\\" + image.getText()));

            vs.AddVol(ex);
            tablevol.getItems().clear();
            affichevol();
        }
    }

    @FXML
    private void handelMouseAction(MouseEvent event) {

        vol ex = tablevol.getSelectionModel().getSelectedItem();
        System.out.println(ex);
        id.setText(String.valueOf(ex.getId_v()));
        aero_d.setText(ex.getAero_d());
        aero_a.setText(ex.getAero_a());
        place_d.setText(String.valueOf(ex.getPlace()));
        prix.setText(String.valueOf(ex.getPrix()));
        image.setText(ex.getBrochure_filename());

        Image img2 = new Image("file:///C:\\Users\\Admin\\Desktop\\imagajava\\" + ex.getBrochure_filename());
        photo.setImage(img2);

        LocalDate dc = ex.getDate_a().toLocalDate();

        date_a.setValue(LocalDate.of(dc.getYear(), dc.getMonth(), dc.getDayOfMonth()));

        LocalDate dc1 = ex.getDate_d().toLocalDate();

        date_d.setValue(LocalDate.of(dc1.getYear(), dc1.getMonth(), dc1.getDayOfMonth()));

    }

    public void displayFilteredExposee(ObservableList<vol> filteredvol) throws SQLException {

        idtab.setCellValueFactory(new PropertyValueFactory<vol, Integer>("id"));
        id_voltab.setCellValueFactory(new PropertyValueFactory<vol, Integer>("id_v"));
        aero_dtab.setCellValueFactory(new PropertyValueFactory<vol, String>("aero_d"));
        aero_atab.setCellValueFactory(new PropertyValueFactory<vol, String>("aero_a"));
        date_dtab.setCellValueFactory(new PropertyValueFactory<vol, Date>("date_d"));
        date_atab.setCellValueFactory(new PropertyValueFactory<vol, Date>("date_a"));
        place_dtab.setCellValueFactory(new PropertyValueFactory<vol, Integer>("place"));
        prixtab.setCellValueFactory(new PropertyValueFactory<vol, Double>("prix"));
        imagetab.setCellValueFactory(new PropertyValueFactory<vol, String>("brochure_filename"));
        tablevol.setItems(filteredvol);

    }

    @FXML
    private void loadAuto(KeyEvent event) throws SQLException {
        volservices ps2 = new volservices();
        AutoComplete ac = new AutoComplete();
        String[] a = ac.MotsAutoComplete();
        TextFields.bindAutoCompletion(searsh, a);
        tablevol.getItems().clear();
        displayFilteredExposee(ps2.MyProductFilteredByName(searsh.getText()));

        if (searsh.getText().length() == 0) {
            tablevol.getItems().clear();
            affichevol();
        }

    }

    @FXML
    private void loadaero(KeyEvent event) throws SQLException {

        AutoComplete ac = new AutoComplete();
        String[] a = ac.MotsAutoAero();
        TextFields.bindAutoCompletion(aero_d, a);
        System.out.println(aero_d.getText());

    }

    @FXML
    private void attatchimage(ActionEvent event) {
        JFileChooser chooser = new JFileChooser();
        chooser.showOpenDialog(null);
        File f = chooser.getSelectedFile();
        String filename = f.getName();
        Image getAbsolutePath = null;
        ImageIcon icon = new ImageIcon(filename);

        image.setText(filename);

        Image img2 = new Image("file:///" + filename);
        setImageFile(f);
        photo.setImage(img2);
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

}
