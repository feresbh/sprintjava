package checkin;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import com.teknikindustries.bulksms.SMS;
import hotel.Reservation;
import hotel.Room;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;
import project.DataBase;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;


import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;


/**
 * FXML Controller class
 *
 * @author UpToDate
 */
public class ConfirmnDataController implements Initializable {
    
    
    
    
    private FileChooser fc = new FileChooser();

    @FXML
    private TextField NameField;
    @FXML
    private TextField PhoneField;
    @FXML
    private TextField EmailField;
    @FXML
    private TextField CityField;
    @FXML
    private TextField NationalityField;
    @FXML
    private TextField PassportField;
    @FXML
    private TextArea AddressField;
    @FXML
    private TextField CardNumField;
    @FXML
    private TextField CVCcodeField;
    @FXML
    private JFXButton Save_Button;
    @FXML
    private JFXButton CancelButton;
    @FXML
    private Label Room_Type;
    @FXML
    private Label CheckOutLabel;
    @FXML
    private Label Room_Capacity;
    @FXML
    private JFXTextField roomIDField;
    @FXML
    private Label Number_of_Nights;
    @FXML
    private Label Night_Cost;

    @FXML
    private ImageView LogoImg;
    @FXML
    private Label emptyLabel;
    @FXML
    private Label CheckInLabel;
    @FXML
    private Label Total_fees;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Reservation reservation = CheckInController.reservation;

        NameField.setText(reservation.getGuest().getName());
        PhoneField.setText(reservation.getGuest().getPhoneNo());
        EmailField.setText(reservation.getGuest().getEmail());
        AddressField.setText(reservation.getGuest().getAddress());
        CityField.setText(reservation.getGuest().getCity());
        NationalityField.setText(reservation.getGuest().getNationality());
        PassportField.setText(reservation.getGuest().getPassportNumber());
        CardNumField.setText(reservation.getGuest().getCardNumber());
        CVCcodeField.setText(reservation.getGuest().getCardPass());
        Room_Type.setText(reservation.getRoom().getRoom_Type());
        Room_Capacity.setText(reservation.getRoom().getRoom_capacity());
        roomIDField.setText(reservation.getGuest().getRoomID() + "");
        CheckOutLabel.setText(CheckInController.Date_TO_LocalDate(reservation.getRoom().getCheck_Out_Date()) + "");
        CheckInLabel.setText(CheckInController.Date_TO_LocalDate(reservation.getRoom().getCheck_In_Date()) + "");
        Number_of_Nights.setText(reservation.getGuest().getNumberOfDaysStayed() + "");
        Night_Cost.setText(reservation.getRoom().nightCost(reservation.getRoom()) + "");
        Total_fees.setText(reservation.getGuest().getFees() + " $");

    }

    @FXML
    private void SaveButtonAction(ActionEvent event) throws Exception  {
       
        DataBase db = new DataBase();
        Reservation reservation = CheckInController.reservation;

        Room.CheckIn(reservation.getGuest(), reservation.getRoom(), 
                reservation.getGuest().getRoomID());

        new homepage.HomePageController().goTO_check_in(event);
        ((Stage) NameField.getScene().getWindow()).close();
        
      //  fc.getExtensionFilters().add(new FileChooser.ExtensionFilter("PDF File","*.PDF"));
        //fc.setTitle("save to pdf");
        //fc.setInitialFileName("untitled.pdf");
        
       // File file = fc.showSaveDialog(event);
        
       
      Alert alert = new Alert(Alert.AlertType.INFORMATION, "", ButtonType.OK);
            alert.setHeaderText("reservation added successfully");
            alert.setTitle("Notification");
            alert.showAndWait();
            
           
                
                
          JavaMailUtil.main("medazizbensmida@gmail.com");
          
          
        SMS smsTut = new SMS();
        smsTut.SendSMS("rito99", "Iwillbeericher1003", "Hello customer this is Travminers agency, we are pleased to have you in our comunity.", "216", "https://bulksms.vsms.net/eapi/submission/send_sms/2/2.0");
    
       
        
    }

    @FXML
    private void CancelAction(ActionEvent event) {
        ((Stage) NameField.getScene().getWindow()).close();
    }   

}
