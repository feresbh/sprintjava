/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tuneasy.views;

import java.io.IOException;
import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import tuneasy.services.UserService;
import tuneasy.utils.SendMail;

/**
 * FXML Controller class
 *
 * @author weixin
 */
public class ResetPasswordFXMLController implements Initializable {

    @FXML
    private TextField emailTextField;
    @FXML
    private TextField codeTextField;
    @FXML
    private Button sendCodeButton;
    @FXML
    private Button verifyCodeButton;
    @FXML
    private Button resetPasswordButton;
    @FXML
    private PasswordField passwordTextField;
    @FXML
    private PasswordField rePasswordTextField;

    UserService userService = new UserService();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        disable();
        sendCodeButton.setOnAction((t) -> {
            System.out.println("clicked");
            System.out.println(checkEmail(emailTextField.getText()) + "");
            if (checkEmail(emailTextField.getText())) {
                System.out.println("" + userService.findUserByEmail(emailTextField.getText()));
                if (userService.findUserByEmail(emailTextField.getText())) {
                    verifyCodeButton.setDisable(false);
                    codeTextField.setDisable(false);
                    String generatedCode = generatingCode();
                    userService.generateCode(emailTextField.getText(), generatedCode);
                    try {
                        SendMail.sendMail(emailTextField.getText(), generatedCode);
                    } catch (Exception ex) {
                        Logger.getLogger(ResetPasswordFXMLController.class.getName()).log(Level.SEVERE, null, ex);
                        System.out.println("Exeception: " + ex);
                    }
                } else {
                    System.out.println("error");
                }
            } else {
                System.out.println("doesn't exist");
            }
        });
        verifyCodeButton.setOnAction((t) -> {
            System.out.println(userService.verifyCode(emailTextField.getText(), codeTextField.getText()));
                if (userService.verifyCode(emailTextField.getText(), codeTextField.getText())) {
                    passwordTextField.setDisable(false);
                    rePasswordTextField.setDisable(false);
                    resetPasswordButton.setDisable(false);
                }
            
        });
        resetPasswordButton.setOnAction((t) -> {
            if (checkPassword(passwordTextField.getText(), rePasswordTextField.getText())) {
                userService.resetPassword(emailTextField.getText(), passwordTextField.getText());
                navigate();
                Stage stage = (Stage) ((Node) t.getSource()).getScene().getWindow();
                 stage.close();
            }
        });

    }

    public boolean checkEmail(String email) {
        String masque = "^[a-zA-Z]+[a-zA-Z0-9\\._-]*[a-zA-Z0-9]@[a-zA-Z]+"
                + "[a-zA-Z0-9\\._-]*[a-zA-Z0-9]+\\.[a-zA-Z]{2,4}$";
        Pattern pattern = Pattern.compile(masque);
        Matcher controler = pattern.matcher(email);
        if (controler.matches()) {
            return true;
        } else {
            return false;
        }
    }

    public boolean checkPassword(String password, String rePassword) {
        if (password.length() > 8) {
            if (password.equals(rePassword)) {
                return true;
            } else {
                System.out.println("Password doesn't match");
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Password");
                alert.setHeaderText(null);
                alert.setContentText("Ooops, Password doesn't match!");
                alert.showAndWait();
                return false;
            }
        } else {
            System.out.println("Password is too short");
                            Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Password");
                alert.setHeaderText(null);
                alert.setContentText("Ooops, Password is too short!");
                alert.showAndWait();

            return false;
        }
    }

    public String generatingCode() {
        int leftLimit = 48; // numeral '0'
        int rightLimit = 122; // letter 'z'
        int targetStringLength = 10;
        Random random = new Random();

        String generatedCode = random.ints(leftLimit, rightLimit + 1)
                .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
                .limit(targetStringLength)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
        System.out.println(generatedCode);
        return generatedCode;
    }

    public void disable() {
        verifyCodeButton.setDisable(true);
        codeTextField.setDisable(true);
        passwordTextField.setDisable(true);
        rePasswordTextField.setDisable(true);
        resetPasswordButton.setDisable(true);
    }
     public void navigate( ) {
        try {
            Parent parent = FXMLLoader.load(getClass().getResource( "LoginFXML.fxml"));
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
