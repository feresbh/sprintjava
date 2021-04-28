/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tuneasy.views;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javax.mail.MessagingException;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import tuneasy.entities.Order;
import tuneasy.services.Email;
import tuneasy.services.OrderDetailsService;
import tuneasy.services.OrderService;
import static tuneasy.utils.Connection.connection;

/**
 * FXML Controller class
 *
 * @author weixin
 */
public class OrdersFXMLController implements Initializable {

public static final String ACCOUNT_SID = "ACb79f0b97cadcb593285c929ac05d0357"; 
public static final String AUTH_TOKEN = "bdcb5444eaf6cda38078376d35164c6a"; 
    
    
    @FXML
    private TableView<Order> ordersTableView;
    @FXML
    private TableColumn<Order, String> idColumn;
    @FXML
    private TableColumn<Order, String> idUserColumn;
    @FXML
    private TableColumn<Order, String> descriptionColumn;
    @FXML
    private TableColumn<Order, String> totalColumn;

    @FXML
    private TableColumn<Order, String> statusColumn;
    @FXML
    private Button updateStatusButton;
    @FXML
    private Button showDetailsButton;

    OrderService orderService = new OrderService();
    @FXML
    private PieChart piechart;
    @FXML
    private Button disc;
    @FXML
    private Button exel;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        displayOrders();
        
        updateStatusButton.setOnAction((t) -> {
            orderService.updateOrderStatus(ordersTableView.getSelectionModel().getSelectedItem().getId());
            ordersTableView.refresh();
                
            
           int id= ordersTableView.getSelectionModel().getSelectedItem().getId();
           double total= ordersTableView.getSelectionModel().getSelectedItem().getTotal();
           
            
            displayOrders();
            //**************MAIL*************//
             Email E=new Email();
            String sujet="BonPlan Ajoutée";
            String messgae="Votre BonPlan d'ordre " +id+ "et de total" +total+ "a ete Ajoutée Avec succes";
           
            
            try {
                E.sendEmail("malek.ayadi@esprit.tn", sujet, messgae);
                sms(id,total);
                
            
                //******************************//
                
                
                //******************SMS*******************//
                
            } catch (MessagingException ex) {
                System.out.println("erreur de mail");
            }
        });
        showDetailsButton.setOnAction((t) -> {
            OrderDetailsService.idOrder = ordersTableView.getSelectionModel().getSelectedItem().getId();
            navigate();
        });
          setPieChart();
         // getData();
         
         disc.setOnAction((t) -> {
          //  navigate("Orders");
             try {
                 Stage stage = (Stage) ((Node) t.getSource()).getScene().getWindow();
          stage.close();
          
                    Parent parent = FXMLLoader.load(getClass().getResource("LoginFXML.fxml"));
                    Scene scene = new Scene(parent);
                    Stage stagee = new Stage();
                    stagee.setScene(scene);
                    stagee.setTitle("Guidni");
                    stagee.initStyle(StageStyle.UTILITY);
                    stagee.show();
                } catch (IOException ex) {
                    Logger.getLogger(OrdersFXMLController.class.getName()).log(Level.SEVERE, null, ex);
                }
        });
         
                 exel.setOnAction((t) -> {
          //  navigate("Orders");
            orderService.exc();
        });
         
        
        
     
    }

    public void displayOrders() {
        //
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        idUserColumn.setCellValueFactory(new PropertyValueFactory<>("idUser"));
        descriptionColumn.setCellValueFactory(new PropertyValueFactory<>("description"));
        totalColumn.setCellValueFactory(new PropertyValueFactory<>("total"));
        statusColumn.setCellValueFactory(new PropertyValueFactory<>("status"));
        ordersTableView.setItems(orderService.showOrders());
        
    }

    public void navigate() {
        try {
            Parent parent = FXMLLoader.load(getClass().getResource("OrderDetailsFXML.fxml"));
            Scene scene = new Scene(parent);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.setTitle("Update Product");
            stage.initStyle(StageStyle.UTILITY);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(OrdersFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void setPieChart() {
        PieChart.Data delivered = new PieChart.Data("delivered", 3);
        PieChart.Data pending = new PieChart.Data("pending", 1);

        piechart.getData().add(delivered);
        piechart.getData().add(pending);

    }

    public void getData() {
        try {
            String query = "SELECT COUNT(*) FROM order WHERE status = 'delivered'";
            PreparedStatement preparedStatement = connection.getConnection().prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                System.out.println(""+ resultSet.getInt(1));
            }
           
        } catch (SQLException ex) {
            Logger.getLogger(OrdersFXMLController.class.getName()).log(Level.SEVERE, null, ex);
          
        }
    }
    
    
    public void sms(int id, double total){
        
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN); 
        Message message;
    message = Message.creator( 
            new com.twilio.type.PhoneNumber("+21654164001"),
            new com.twilio.type.PhoneNumber("+13203346023"),
            "Cher client ,nous vous informons que votre ordre d'id "+id+"et de total "+total+"a éte approuvé") 
            .create();
 
        System.out.println(message.getSid()); 
        
    }
    
    

}
