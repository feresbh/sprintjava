/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tuneasy.views;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Callback;
import tuneasy.entities.Order;
import tuneasy.entities.OrderDetails;
import tuneasy.entities.Product;
import tuneasy.services.OrderDetailsService;
import tuneasy.services.OrderService;
import tuneasy.services.ProductService;
import tuneasy.utils.LoginSession;

/**
 * FXML Controller class
 *
 * @author weixin
 */
public class StoreFXMLController implements Initializable {

    @FXML
    private TableView<OrderDetails> orderTableView;
    @FXML
    private TableColumn<OrderDetails, String> idProductColumn;
    @FXML
    private TableColumn<OrderDetails, String> quantityColumn;
    @FXML
    private TableColumn<OrderDetails, String> totalColumn;
    @FXML
    private TableView<Product> productTableView;
    @FXML
    private TableColumn<Product, String> idColumn;
    @FXML
    private TableColumn<Product, String> nameColumn;
    @FXML
    private TableColumn<Product, String> categoryColumn;
    @FXML
    private TableColumn<Product, String> subtitleColumn;
    @FXML
    private TableColumn<Product, String> descriptionColumn;
    @FXML
    private TableColumn<Product, String> prixColumn;
    @FXML
    private TextField quantityTextField;
    @FXML
    private Button addQuabtityButton;
    @FXML
    private Label totalLabel;

    ProductService productService = new ProductService();
    OrderDetailsService orderDetailsService = new OrderDetailsService();
    OrderService orderService = new OrderService();
    OrderDetails orderDetails;
    @FXML
    private Button orderButton;
    private TextField descriptionTextView;
    @FXML
    private TextField descriptionTextField;
    @FXML
    private Button ayadi;
    @FXML
    private Label valQuan;
    @FXML
    private TableColumn<OrderDetails, String> colAction;
    @FXML
    private Button btnpaniervide;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        totalLabel.setText("0");
        displayProducts();
        
        addQuabtityButton.setOnAction((t) -> {
            int idProduct=0;
            idProduct = productTableView.getSelectionModel().getSelectedItem().getId();
            int quantity = Integer.parseInt(quantityTextField.getText());
            double total = quantity * productTableView.getSelectionModel().getSelectedItem().getPrix();
           
             if( idProduct==0){
                valQuan.setText("You must choose a product ");
                
            }
              if(quantity==0){
                valQuan.setText("You must choose a quantity ");
                
            }
               if(total==0){
                valQuan.setText("You must choose a product or a quantity ");
                
            }
             else{
                 
             
            orderDetails = new OrderDetails(idProduct, 0, quantity, total);
            displayOrder();
            orderTableView.getItems().add(orderDetails);
//if doesn't work just try setIems
            double totalOrder = Double.parseDouble(totalLabel.getText()) + total;
            totalLabel.setText(totalOrder + "");
            orderTableView.refresh();
            quantityTextField.setText("1");
             }
        });
        
        
        orderButton.setOnAction((t) -> {
           if(descriptionTextField.getText().isEmpty()){
                //valQuan.setText("You didn't choose a product to order");
                System.out.println("ERROR Order !");
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Order ");
                alert.setHeaderText(null);
                alert.setContentText("You must Complete all fields!");
                alert.showAndWait();
                
            }
           else{
            Order order = new Order(LoginSession.idLoggedUser, descriptionTextField.getText(), Double.parseDouble(totalLabel.getText()));
            orderService.addOrder(order);
            saveOrderDetails();
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Ordering");
            alert.setHeaderText(null);
            alert.setContentText("Your order has been successfully saved!");
            alert.showAndWait();
            orderTableView.getItems().clear();
            orderTableView.refresh();
            totalLabel.setText("0");
            descriptionTextField.setText("");
           }});
        
        
        ayadi.setOnAction((t) -> {
          //  navigate("Orders");
             try {
                 Stage stage = (Stage) ((Node) t.getSource()).getScene().getWindow();
          stage.close();
          
                    Parent parent = FXMLLoader.load(getClass().getResource("LoginFXML.fxml"));
                    Scene scene = new Scene(parent);
                    Stage stagee = new Stage();
                    stagee.close();
                    stagee.setScene(scene);
                    stagee.setTitle("Guidni");
                    stagee.initStyle(StageStyle.UTILITY);
                    stagee.show();
                } catch (IOException ex) {
                    Logger.getLogger(OrdersFXMLController.class.getName()).log(Level.SEVERE, null, ex);
                }
        });
        
        btnpaniervide.setOnAction((t) -> {
          //  navigate("Orders");
          
          orderTableView.getItems().clear();
            orderTableView.refresh();
            totalLabel.setText("0");
            descriptionTextField.setText("");
            
         
                
        });
    }

    public void displayProducts() {
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        categoryColumn.setCellValueFactory(new PropertyValueFactory<>("category"));
        subtitleColumn.setCellValueFactory(new PropertyValueFactory<>("subtitle"));
        descriptionColumn.setCellValueFactory(new PropertyValueFactory<>("description"));
        prixColumn.setCellValueFactory(new PropertyValueFactory<>("prix"));
        productTableView.setItems(productService.showProduct());
    }

    public void displayOrder() {
        idProductColumn.setCellValueFactory(new PropertyValueFactory<>("idProduct"));
        quantityColumn.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        totalColumn.setCellValueFactory(new PropertyValueFactory<>("total"));
                
        
        
        
        
        
          
       
    }

    public void saveOrderDetails() {
        
        ObservableList<OrderDetails> orderDetailsObservableList = orderTableView.getItems();
        for (int i = 0; i < orderDetailsObservableList.size(); i++) {
            orderDetailsObservableList.get(i).setIdOrder(orderService.findLastOrder());
            orderDetailsService.addOrderDetails(orderDetailsObservableList.get(i));
        }
    }

}
