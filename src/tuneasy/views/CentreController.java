/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tuneasy.views;


import tuneasy.entities.Centre;
import tuneasy.entities.Materiel;
import tuneasy.entities.SendMail;
import java.io.File;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import tuneasy.services.ServiceCentre;
import tuneasy.utils.DataBase;

/**
 * FXML Controller class
 *
 * @author asus
 */
public class CentreController implements Initializable {

    @FXML
    private TextField nom;
    @FXML
    private TextField lieu;
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
    private TableView<Centre> table;
    @FXML
    private TableColumn<Centre, Integer> col_id;
    @FXML
    private TableColumn<Centre, String> col_nom;
    @FXML
    private TableColumn<Centre, String> col_lieu;
    @FXML
    private TableColumn<Centre, String> col_description;
    @FXML
    private TableColumn<Centre, ImageView> col_image;
    @FXML
    private Button btnadd;
    @FXML
    private TextField affiche;
    @FXML
    private Button uploadbutton;
    @FXML
    private Label id_label;

    private final ObservableList<Centre> data = FXCollections.observableArrayList();
    private Statement ste;
    private Connection con;
    ServiceCentre ser =new ServiceCentre();
    @FXML
    private Label msg;
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

            ResultSet rs = ste.executeQuery("select * from centre");
            while(rs.next()){
                    Centre a = new Centre(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5));
                
                File file = new File(rs.getString(5));
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
            col_nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
            col_description.setCellValueFactory(new PropertyValueFactory<>("description"));
            col_lieu.setCellValueFactory(new PropertyValueFactory<>("lieu"));
            col_image.setCellValueFactory(new PropertyValueFactory<>("img"));
            
            table.setItems(data);
          
   }
   
   
    public void RechercheAV(){
                // Wrap the ObservableList in a FilteredList (initially display all data).
        FilteredList<Centre> filteredData = new FilteredList<>(data, b -> true);
		
		// 2. Set the filter Predicate whenever the filter changes.
		recherche.textProperty().addListener((observable, oldValue, newValue) -> {
			filteredData.setPredicate(centre -> {
				// If filter text is empty, display all persons.
								
				if (newValue == null || newValue.isEmpty()) {
					return true;
				}
				
				// Compare first name and last name of every person with filter text.
				String lowerCaseFilter = newValue.toLowerCase();
				
				if (centre.getNom().toLowerCase().indexOf(lowerCaseFilter) != -1 ) {
					return true; // Filter matches first name.
				} else if (centre.getDescription().toLowerCase().indexOf(lowerCaseFilter) != -1) {
					return true; // Filter matches last name.
				}
				else if (String.valueOf(centre.getId()).indexOf(lowerCaseFilter)!=-1)
				     return true;
				     else  
				    	 return false; // Does not match.
			});
		});
		
		// 3. Wrap the FilteredList in a SortedList. 
		SortedList<Centre> sortedData = new SortedList<>(filteredData);
		
		// 4. Bind the SortedList comparator to the TableView comparator.
		// 	  Otherwise, sorting the TableView would have no effect.
		sortedData.comparatorProperty().bind(table.comparatorProperty());
		
		// 5. Add sorted (and filtered) data to the table.
		table.setItems(sortedData);
    }
    @FXML
    private void supprimercentre(ActionEvent event) throws SQLException {
             table.setItems(data);

             ObservableList<Centre> allcentres,Singlecentre ;
             allcentres=table.getItems();
             Singlecentre=table.getSelectionModel().getSelectedItems();
             Centre A = Singlecentre.get(0);
             ser.delete(A.getId());
             Singlecentre.forEach(allcentres::remove);
             Aff();
             RechercheAV();
    }

    @FXML
    private void modifiercentre(ActionEvent event) throws SQLException, IOException {
                            File f = new File(affiche.getText());
                          Centre a =  new Centre(nom.getText(),lieu.getText(),description.getText(),"C:\\Users\\seife\\Desktop\\Projet\\uploads\\"+f.getName());
                    ser.update(a,Integer.valueOf(id_label.getText()));
                   
                    Files.copy(Paths.get(affiche.getText()),Paths.get("C:\\Users\\seife\\Desktop\\Projet\\uploads\\"+f.getName()),REPLACE_EXISTING);
                Aff();
                RechercheAV();
               
                id_label.setText("");
                nom.setText("");
                description.setText("");
                lieu.setText("");
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
       Centre tab_selected = table.getSelectionModel().getSelectedItem();
               
       id_label.setText(String.valueOf(tab_selected.getId()));
       nom.setText(tab_selected.getNom());
       description.setText(tab_selected.getDescription());
       lieu.setText(tab_selected.getLieu());
       affiche.setText(tab_selected.getImage());
    }
        private boolean Validchamp(TextField T){
        return !T.getText().isEmpty() && T.getLength() > 2;
    }
    @FXML
    private void ajout(MouseEvent event) throws SQLException, IOException {
        if(Validchamp(nom)&&Validchamp(lieu)&&Validchamp(description)){
                                File f = new File(affiche.getText());
                    ser.ajouter(new Centre(nom.getText(),lieu.getText(),description.getText(),"C:\\Users\\seife\\Desktop\\Projet\\uploads\\"+f.getName()));
                   
                    Files.copy(Paths.get(affiche.getText()),Paths.get("C:\\Users\\seife\\Desktop\\Projet\\uploads\\"+f.getName()),REPLACE_EXISTING);
                Aff();
                RechercheAV();
                id_label.setText("");
                nom.setText("");
                description.setText("");
                lieu.setText("");
                affiche.setText(""); 
                msg.setText("Centre ajoutée.");
                 
                SendMail.sendMail("seifeddine.saidi@esprit.tn", "Centre Ajouter", "Un Centre à etait ajoutée");
        }
        else
        {
            msg.setText("Verifier les champs.");
        }

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
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Materiel.fxml"));
        Parent root = loader.load();
        
        MaterielController apc =loader.getController();
        btncamping.getScene().setRoot(root);    }

    @FXML
    private void bon_plans(MouseEvent event) {
    }

    @FXML
    private void blog(MouseEvent event) {
    }

    @FXML
    private void utilisateurs(MouseEvent event) {
    }





    
}

