/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tuneasy.views;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;
import tuneasy.entities.User;
import tuneasy.services.UserService;
import tuneasy.utils.LoginSession;

/**
 * FXML Controller class
 *
 * @author weixin
 */
public class LoginFXMLController implements Initializable {

    @FXML
    private TextField emailTextField;
    @FXML
    private PasswordField passwordTextField;
    @FXML
    private Button loginButton;
    @FXML
    private Button signupButton;

    /**
     * Initializes the controller class.
     */
    UserService userService = new UserService();
    @FXML
    private Button resetPasswordButton;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
             TrayNotification tray = new TrayNotification("success","Bienvenue",NotificationType.SUCCESS);
            tray.showAndDismiss(Duration.seconds(5));
        // TODO
        
        System.out.println("tawa not");
        
          
            
        signupButton.setOnAction((ActionEvent event) -> {
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                 stage.close();
            navigate("Register");
        });
        resetPasswordButton.setOnAction((t) -> {
            Stage stage = (Stage) ((Node) t.getSource()).getScene().getWindow();
                 stage.close();
            navigate("ResetPassword");
        });
        loginButton.setOnAction((ActionEvent event) -> {
            User user = new User(emailTextField.getText(), passwordTextField.getText());
            if (userService.login(user)) {
                if (userService.checkRole(LoginSession.idLoggedUser).equals("admin")) {
                    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                 stage.close();
                    navigate("Menu");
                } else {
                    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                 stage.close();
                    navigate("Store");
                }
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                 stage.close();
            } else {
                System.out.println("ERROR LOGIN !");
                Alert alert = new Alert(AlertType.ERROR);
                alert.setTitle("Error Login");
                alert.setHeaderText(null);
                alert.setContentText("Ooops, there was an error!");
                alert.showAndWait();
            }
        });
    }

    public void navigate(String panel) {
        try {
            TrayNotification tray = new TrayNotification("success","Bienvenue",NotificationType.SUCCESS);
            tray.showAndDismiss(Duration.seconds(5));
            Parent parent = FXMLLoader.load(getClass().getResource( panel + "FXML.fxml"));
            Scene scene = new Scene(parent);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.setTitle("Guidni");
            stage.initStyle(StageStyle.UTILITY);
            stage.show();
            
        } catch (IOException ex) {
            Logger.getLogger(LoginFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
