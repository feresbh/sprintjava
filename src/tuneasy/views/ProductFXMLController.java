/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tuneasy.views;

import java.io.File;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.value.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import tuneasy.entities.Product;
import tuneasy.services.ProductService;

/**
 * FXML Controller class
 *
 * @author weixin
 */
public class ProductFXMLController implements Initializable {

    @FXML
    private TextField nameTextField;
    @FXML
    private TextField descriptionTextField;
    @FXML
    private TextField subtitleTextField;
    @FXML
    private TextField categoryTextField;
    @FXML
    private TextField prixTextField;
    @FXML
    private TextField imageTextField;
    @FXML
    private Button submitButton;

    String name, description, subtitle, image;
    int category;
    double prix;
    private Image imagee;
    private FileChooser fileChooser;
    private File file;

String st[] = { "1", "2", "13" };
    ProductService productService = new ProductService();
    int idProduct = productService.idProduct;
    boolean selected = productService.selected;
    @FXML
    private Label nameVal;
    @FXML
    private Label ValCat;
    @FXML
    private Label Valprix;
    @FXML
    private Label valDesc;
    @FXML
    private Label valSub;
    @FXML
    private ImageView imageV;
    @FXML
    private Button btnBrowse;
    @FXML
    private ChoiceBox<String> categoryy;
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        loadcom();
        
        if (selected) {
            System.out.println("selected from combo"+ categoryy.getSelectionModel().getSelectedItem());
            Product product = productService.findById(idProduct);
            nameTextField.setText(product.getName());
            descriptionTextField.setText(product.getDescription());
            subtitleTextField.setText(product.getSubtitle());
            imageTextField.setText(product.getImage());
          //  categoryTextField.setText( categoryy.getSelectionModel().getSelectedItem());
            prixTextField.setText("" + product.getPrix());
            
            
            
            
            categoryy.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {

			// if the item of the list is changed
			public void changed(ObservableValue ov, Number value, Number new_value)
			{

				// set the text for the label to the selected item
				categoryTextField.setText(st[new_value.intValue()] + " selected");
			}
		});
          

        }
        
        
        
        
        
        
        submitButton.setOnAction((ActionEvent event) -> {
            
            if(nameTextField.getText().isEmpty()){
                nameVal.setText("This field is Empty");
                
            }
            
            
           
            if(descriptionTextField.getText().isEmpty()){
                valDesc.setText("This field is Empty");
                
            }
            
            if(subtitleTextField.getText().isEmpty()){
                valSub.setText("This field is Empty");
                
            }
            
          else if(!nameTextField.getText().isEmpty()&& !descriptionTextField.getText().isEmpty() && !subtitleTextField.getText().isEmpty() && !categoryTextField.getText().isEmpty() && !prixTextField.getText().isEmpty() && !imageTextField.getText().isEmpty() ){
                
            
            name = nameTextField.getText();
            description = descriptionTextField.getText();
            subtitle = subtitleTextField.getText();
            image = imageTextField.getText();
           category = Integer.parseInt(categoryTextField.getText());
            prix = Double.parseDouble(prixTextField.getText());
            Product product = new Product(name, image, subtitle, description, prix, category);
          Alert alert = new Alert(Alert.AlertType.INFORMATION);
          if (!selected) {
                productService.addProduct(product);
                productService.showProduct();
                alert.setTitle("Product added");
                alert.setHeaderText(null);
                alert.setContentText("Ypur product has been successfully added!");
               
            } else {
                productService.updateProduct(idProduct, product);
                productService.selected = false;
                 alert.setTitle("Product updated");
                alert.setHeaderText(null);
                alert.setContentText("Ypur product has been successfully updated!");
            } 
          alert.showAndWait();
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                 stage.close();
                 }
               /* else{
                
                System.out.println("ERROR Inputs !");
                Alert alert = new Alert(AlertType.ERROR);
                alert.setTitle("Error Inputs");
                alert.setHeaderText(null);
                alert.setContentText("All fields must be != empty");
                alert.showAndWait();
            }*/
        });
        
                        btnBrowse.setOnAction((ActionEvent event) -> {
                      FileChooser fc = new FileChooser();
      File selectedFile = fc.showOpenDialog(null);

      if(selectedFile != null) {
      imageTextField.setText(selectedFile.getAbsolutePath());
      imagee = new Image(selectedFile.toURI().toString(),160,160,true,true,true);

      imageV.setImage(imagee);
      imageV.setFitHeight(160);
      imageV.setFitWidth(160);



      }
                        
                        
                        });
                        }
    
    
    @FXML
    public void controlCat(){
        
        
         try{
             int i=Integer.parseInt(categoryTextField.getText());     
             ValCat.setText(" "); 

            }
            catch(NumberFormatException e){
               ValCat.setText("Invalid number"); 
                
            }
    }
    
    
    @FXML
     public void controlPrix(){
        
        
         try{
             int j=Integer.parseInt(prixTextField.getText());     
             Valprix.setText(" "); 

            }
            catch(NumberFormatException ex){
               Valprix.setText("Invalid number"); 
                
            }
    }
     
     public void loadcom(){
         
               List<String> Ensemble = null;
        try {
            Ensemble = productService.preparerListeCategories();
            System.out.println("lista    "+Ensemble);
        } catch (SQLException ex) {
            System.out.println("famma errrrrreur fel combobox");
            
        }

        categoryy.getItems().addAll(Ensemble);
        }
     }

    
    
    


