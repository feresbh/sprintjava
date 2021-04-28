/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tuneasy.views;

import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
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
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import tuneasy.entities.Product;
import tuneasy.services.ProductService;
import static tuneasy.utils.Connection.connection;

/**
 * FXML Controller class
 *
 * @author weixin
 */
public class ProductsFXMLController implements Initializable {

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
    private TextField searchTextField;
    @FXML
    private Button searchButton;
    @FXML
    private Button addButton;
    @FXML
    private Button editButton;
    @FXML
    private Button deleteButton;

    ProductService productService = new ProductService();
    @FXML
    private Button refreshButton;
    @FXML
    private Button malek;
    @FXML
    private Button disco;
    @FXML
    private Button btnpdf;
    
       ObservableList<Product> prodList=FXCollections.observableArrayList();


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        displayProducts();
        deleteButton.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                if (productTableView.getSelectionModel().getSelectedItem() != null) {
                    System.out.println(productTableView.getSelectionModel().getSelectedItem().getId());
                    productService.deleteProduct(productTableView.getSelectionModel().getSelectedItem().getId());
                productTableView.refresh();
                displayProducts();
                }
            }
        });
        addButton.setOnAction((ActionEvent event) -> {
            displayProducts();
            productTableView.refresh();
            navigate();
            displayProducts();
        });
        displayProducts();
        editButton.setOnAction((ActionEvent event) -> {
            productService.idProduct = productTableView.getSelectionModel().getSelectedItem().getId();
            productService.selected = true;
            navigate();
            displayProducts();
        });
        refreshButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                productTableView.refresh();
                displayProducts();
            }
        });
        
        malek.setOnAction((t) -> {
          //  navigate("Orders");
         
                     
             try {
                     Stage stage = (Stage) ((Node) t.getSource()).getScene().getWindow();
          stage.close();
                 
                    Parent parent = FXMLLoader.load(getClass().getResource("OrdersFXML.fxml"));
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
        
        disco.setOnAction((t) -> {
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
    }

    public void displayProducts() {
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        categoryColumn.setCellValueFactory(new PropertyValueFactory<>("idCategory"));
        subtitleColumn.setCellValueFactory(new PropertyValueFactory<>("subtitle"));
        descriptionColumn.setCellValueFactory(new PropertyValueFactory<>("description"));
        prixColumn.setCellValueFactory(new PropertyValueFactory<>("prix"));
        productTableView.setItems(productService.showProduct());
    }

    public void navigate() {
        try {
            Parent parent = FXMLLoader.load(getClass().getResource("ProductFXML.fxml"));
            displayProducts();
            Scene scene = new Scene(parent);
            displayProducts();
            Stage stage = new Stage();
            displayProducts();
            stage.setScene(scene);
            displayProducts();
            stage.setTitle("Update Product");
            displayProducts();
            stage.initStyle(StageStyle.UTILITY);
displayProducts();
            stage.show();
            displayProducts();
        } catch (IOException ex) {
            Logger.getLogger(ProductsFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void serach(KeyEvent event) {
        
       
        
        searchTextField.setOnKeyReleased(e->{
         String requete = "SELECT * FROM `product` where id LIKE '%"+searchTextField.getText()+"%' or category_id LIKE '%"+searchTextField.getText()+"%' or name LIKE '%"+searchTextField.getText()+"%' ";
            PreparedStatement pst;
     try {
            pst = connection.getConnection().prepareStatement(requete);
     
        ResultSet resultSet = pst.executeQuery();
         
        prodList.clear();
            while (resultSet.next()){
 int idProduct = resultSet.getInt("id");
                String name = resultSet.getString("name");
                int idCategory = resultSet.getInt("category_id");
                String image = resultSet.getString("image");
                String description = resultSet.getString("description");
                double prix = resultSet.getDouble("prix");
                String subtitle = resultSet.getString("subtitle");
                Product product = new Product(idProduct, name, image, subtitle, description, prix, idCategory);
                prodList.add(product);
                productTableView.setItems(prodList);
                                productTableView.refresh();

                 
            }}catch (SQLException ex) {
         System.out.println("Error : "+ex.getMessage());     }
     });
        
    }
    
    
    @FXML
    public void pdf(){
        
        try{
            String file_name="C:\\Users\\MY HP\\Desktop\\JAVA_Pidev\\Final\\TunEasy2\\TunEasy2\\PDF\\test.pdf";
            Document document= new Document();
            
            PdfWriter.getInstance(document, new FileOutputStream(file_name));
            document.open();
            
            
            //Add Image
            document.add(Image.getInstance("C:\\Users\\MY HP\\Desktop\\JAVA_Pidev\\Final\\TunEasy2\\TunEasy2\\src\\img\\loggo.png"));
              document.add(new Paragraph(" "));
                     document.add(new Paragraph(" "));
            
            //Add paragraph
            Paragraph pars= new Paragraph("                 List Produits PDF");
            document.add(pars);
            
            
            document.add(new Paragraph(" "));
               document.add(new Paragraph(" "));
                  document.add(new Paragraph(" "));
                     document.add(new Paragraph(" "));
                     
            
            //Add Table
            PdfPTable table= new PdfPTable(4);
            PdfPCell c1= new PdfPCell(new Phrase("id "));
            table.addCell(c1);
            
            c1= new PdfPCell(new Phrase("name "));
            table.addCell(c1);
            
             c1= new PdfPCell(new Phrase("category_id "));
            table.addCell(c1);
            
             c1= new PdfPCell(new Phrase("image "));
            table.addCell(c1);
            table.setHeaderRows(1);
            
            /*
            
            table.addCell("1.0");
            table.addCell("2.1");
            table.addCell("3.0");
            table.addCell("4.1");
            table.addCell("5.0");
            table.addCell("6.1");
            table.addCell("7.0");
            table.addCell("8.1");
            */
            
                        PreparedStatement pst;

            
            String requete = "SELECT *FROM product";
           // Statement pst = MyConnection.getInstance().getCnx().createStatement();
            
             pst = connection.getConnection().prepareStatement(requete);
     
     
            ResultSet rs = pst.executeQuery(requete);

            while (rs.next()) {

                table.addCell(String.valueOf(rs.getInt(1)));
                table.addCell(rs.getString(2));
                table.addCell(String.valueOf(rs.getInt(3)));
                table.addCell(rs.getString(4));
                

            }
            //adding the table
           // document.add(tablepdf);
            document.add(table);
            
            
            
            
            
            
            
            
            
            
            document.close();
            System.out.println("finished");
            
            
        } catch(Exception ex){
            System.out.println("error PDF");
            
            
        }
        
    }
    
    
}
