/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tuneasy.views;


import com.itextpdf.text.DocumentException;
import tuneasy.entities.Centre;
import tuneasy.entities.Materiel;
import tuneasy.entities.Pdf;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.SortEvent;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import tuneasy.services.ServiceMateriel;
import tuneasy.utils.DataBase;

/**
 * FXML Controller class
 *
 * @author asus
 */
public class MaterielController implements Initializable {

    @FXML
    private TextField prix;
    @FXML
    private TextField description;
    @FXML
    private ImageView imagebox;
    @FXML
    private ImageView logo;
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
    private TableView<Materiel> table;
    @FXML
    private TableColumn<Materiel, Integer> col_id;
    @FXML
    private TableColumn<Materiel, String> col_type;
    @FXML
    private TableColumn<Materiel,String> col_description;
    @FXML
    private TableColumn<Materiel, Float> col_prix;
    @FXML
    private TableColumn<Materiel, Boolean> col_disponibilite;
    @FXML
    private TableColumn<Materiel, ImageView> col_image;
    @FXML
    private Button btnadd;
    @FXML
    private Button btn_exel;
    @FXML
    private TextField affiche;
    @FXML
    private Button uploadbutton;
    @FXML
    private TextField disponibilite;
    @FXML
    private TextField type;
        
    private final ObservableList<Materiel> data = FXCollections.observableArrayList();
    private Statement ste;
    private Connection con;
                           
    ServiceMateriel ser =new ServiceMateriel();
    @FXML
    private Label id_label;
    @FXML
    private TextField recherche;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        Aff();
        RechercheAV();
        id_label.setVisible(false);

    }    
   public void Aff(){
                        try {
            con = DataBase.getInstance().getConnection();
            ste = con.createStatement();
            data.clear();

            ResultSet rs = ste.executeQuery("select * from materiel");
            while(rs.next()){
                 Materiel a = new Materiel(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getFloat(4), rs.getBoolean(5), rs.getString(6));
                
                File file = new File(rs.getString(6));
                Image image = new Image(file.toURI().toString());
                
                ImageView imageView =new ImageView(image);
                imageView.setImage(image);
                imageView.setFitHeight(100);
                imageView.setFitWidth(100);

                a.setImg(imageView);
                
                data.add(a);
            }

        } catch (Exception e) {
                //Logger.getLogger(tab)
        }
               
            
            col_id.setCellValueFactory(new PropertyValueFactory<>("id"));
            col_type.setCellValueFactory(new PropertyValueFactory<>("type"));
            col_description.setCellValueFactory(new PropertyValueFactory<>("description"));
            col_prix.setCellValueFactory(new PropertyValueFactory<>("prix"));
            col_disponibilite.setCellValueFactory(new PropertyValueFactory<>("disponibilite"));
            col_image.setCellValueFactory(new PropertyValueFactory<>("img"));
            
            table.setItems(data);
          

    }
         public void RechercheAV(){
                // Wrap the ObservableList in a FilteredList (initially display all data).
        FilteredList<Materiel> filteredData = new FilteredList<>(data, b -> true);
		
		// 2. Set the filter Predicate whenever the filter changes.
		recherche.textProperty().addListener((observable, oldValue, newValue) -> {
			filteredData.setPredicate(matriel -> {
				// If filter text is empty, display all persons.
								
				if (newValue == null || newValue.isEmpty()) {
					return true;
				}
				
				// Compare first name and last name of every person with filter text.
				String lowerCaseFilter = newValue.toLowerCase();
				
				if (matriel.getType().toLowerCase().indexOf(lowerCaseFilter) != -1 ) {
					return true; // Filter matches first name.
				} else if (matriel.getDescription().toLowerCase().indexOf(lowerCaseFilter) != -1) {
					return true; // Filter matches last name.
				}
				else if (String.valueOf(matriel.getId()).indexOf(lowerCaseFilter)!=-1)
				     return true;
				     else  
				    	 return false; // Does not match.
			});
		});
		
		// 3. Wrap the FilteredList in a SortedList. 
		SortedList<Materiel> sortedData = new SortedList<>(filteredData);
		
		// 4. Bind the SortedList comparator to the TableView comparator.
		// 	  Otherwise, sorting the TableView would have no effect.
		sortedData.comparatorProperty().bind(table.comparatorProperty());
		
		// 5. Add sorted (and filtered) data to the table.
		table.setItems(sortedData);
    }
    @FXML
    private void supprimerMateriel(ActionEvent event) throws SQLException {
             table.setItems(data);

             ObservableList<Materiel> allmateriels,Singlemateriel ;
             allmateriels=table.getItems();
             Singlemateriel=table.getSelectionModel().getSelectedItems();
             Materiel A = Singlemateriel.get(0);
             ser.delete(A.getId());
             Singlemateriel.forEach(allmateriels::remove);
             Aff();
             RechercheAV();

             
    }

    @FXML
    private void modifierMateriel(ActionEvent event) throws SQLException, IOException {
                            File f = new File(affiche.getText());
                          Materiel a =  new Materiel(type.getText(),description.getText(),Float.parseFloat(prix.getText()),Boolean.parseBoolean(disponibilite.getText()),"C:\\Users\\seife\\Desktop\\Projet\\uploads\\"+f.getName());
                    ser.update(a,Integer.valueOf(id_label.getText()));
                   
                    Files.copy(Paths.get(affiche.getText()),Paths.get("C:\\Users\\seife\\Desktop\\Projet\\uploads\\"+f.getName()),REPLACE_EXISTING);
                Aff();
                RechercheAV();               
                id_label.setText("");
                type.setText("");
                description.setText("");
                prix.setText("");
                disponibilite.setText("");
                affiche.setText("");
    }
    
    @FXML
    private void ajout(MouseEvent event) throws SQLException, IOException {
                    File f = new File(affiche.getText());
                    ser.ajouter(new Materiel(type.getText(),description.getText(),Float.parseFloat(prix.getText()),Boolean.parseBoolean(disponibilite.getText()),"C:\\Users\\seife\\Desktop\\Projet\\uploads\\"+f.getName()));
                   
                    Files.copy(Paths.get(affiche.getText()),Paths.get("C:\\Users\\seife\\Desktop\\Projet\\uploads\\"+f.getName()),REPLACE_EXISTING);
                Aff();
                RechercheAV();
                id_label.setText("");
                type.setText("");
                description.setText("");
                prix.setText("");
                disponibilite.setText("");
                affiche.setText("");
    }
    
    @FXML
    private void Uploadfile(ActionEvent event) {
                FileChooser fc = new FileChooser();
        String path = fc.showOpenDialog(uploadbutton.getScene().getWindow()).getPath();
        affiche.setText(path);
    }
    
        @FXML
    private void showdetail(MouseEvent event) {
               Materiel tab_selected = table.getSelectionModel().getSelectedItem();
               
       id_label.setText(String.valueOf(tab_selected.getId()));
       type.setText(tab_selected.getType());
       description.setText(tab_selected.getDescription());
       prix.setText(String.valueOf(tab_selected.getPrix()));
       disponibilite.setText(Boolean.toString(tab_selected.isDisponibilite()));
       affiche.setText(tab_selected.getImage());
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
    private void camping(MouseEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Centre.fxml"));
        Parent root = loader.load();
        
        CentreController apc =loader.getController();
        btncamping.getScene().setRoot(root);
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
    private void search(KeyEvent event) {
    }


    @FXML
    private void exel(MouseEvent event) {
    }

    @FXML
    private void pdf(ActionEvent event) throws FileNotFoundException, SQLException, DocumentException {
                     Pdf p = new Pdf();
                  p.add("Materiel");
    }




    
}
