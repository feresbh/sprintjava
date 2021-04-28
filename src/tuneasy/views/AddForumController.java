/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tuneasy.views;

import tuneasy.entities.Forum;
import tuneasy.services.PiCRUD;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import static java.lang.System.console;
import java.net.URL;
import java.nio.file.Files;
import java.util.Iterator;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;
import javax.swing.JFileChooser;

/**
 * FXML Controller class
 *
 * @author pc
 */
public class AddForumController implements Initializable {

    private String name;
    private String email;
    
    @FXML
    private TextField tfnom;
    @FXML
    private TextField tfemail;
    @FXML
    private TextField tfdescription;
    @FXML
public Button imageButton;
    @FXML
    private Button btnsave;
   private String imageName = null;
    @FXML
     public ImageView imageView;
      private File imageFile;
    @FXML
    private TextField tfimg;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    


    @FXML
    private void save(ActionEvent event) throws IOException {
        //save in db
         System.out.println("forum added!");
        PiCRUD f1 = new PiCRUD();
          Forum f = new Forum();
          String tNom=tfnom.getText();
          String tEmail=tfemail.getText();
          String tDesc=tfdescription.getText();
          String tImg=tfimg.getText();
          f.setNom(tNom);
          f.setEmail(tEmail);
          f.setDescription(tDesc);
          f.setImage(tImg);
                   f1.addforum2(f); 
            if (imageName != null) {
                f.setImage(imageName);
                try {

                    ImageInputStream iis = ImageIO.createImageInputStream(imageFile);

                    Iterator<ImageReader> imageReaders = ImageIO.getImageReaders(iis);

                    while (imageReaders.hasNext()) {

                        ImageReader reader = imageReaders.next();
                        ImageIO.write(ImageIO.read(imageFile), reader.getFormatName(), new File("src/assets/" + imageFile.getName()).getAbsoluteFile());
                    }

                }
                catch (Exception ex) {
                    System.out.println(ex.getMessage());
                }
            } else
                f.setImage("");
    
           
          //redirect to
       FXMLLoader loader = new FXMLLoader(getClass().getResource("ForumDeatils.fxml"));
    Parent root = loader.load();
    ForumDeatilsController ds = loader.getController();
    
   // ds.setResnom(tfnom.getText());
   // ds.setResemail(tfemail.getText());
   // ds.setResdesc(tfdescription.getText());
    
    tfnom.getScene().setRoot(root);
    tfemail.getScene().setRoot(root);
    tfdescription.getScene().setRoot(root);
    }
 
        
    @FXML
    private void uploadImage(ActionEvent actionEvent) {
        
        Stage primary = new Stage();
        FileChooser fileChooser = new FileChooser();
       fileChooser.setTitle("Selectionner une image");
           fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif","*.jpeg"));
         File file = fileChooser.showOpenDialog(primary);
          String path = "C:\\User\\pc\\Descktop\\images";
          tfimg.setText(file.getName());
           if (file != null) {
            try {
                Files.copy(file.toPath(), new File(path + "\\" + file.getName()).toPath());
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
   
       // File selectedFile = fileChooser.showOpenDialog(null);
        //String fileName = selectedFile.getAbsolutePath();
       
	   
        
        // File f = fileChooser.
        /*fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("All Images", "*.*"));
        File selectedFile = fileChooser.show(null);

        if (selectedFile != null) {
            imageView.setImage(new Image(selectedFile.toURI().toString()));
            imageFile = selectedFile;
            imageName = selectedFile.getName();
        }*/
   // }
    
    
//}
