/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tuneasy.views;

//import GUI.Post.PostsListController;
import tuneasy.entities.Forum;
import tuneasy.services.PiCRUD;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Iterator;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;

/**
 * FXML Controller class
 *
 * @author pc
 */
public class UpdateForumController_1 implements Initializable {

    @FXML
    private Button btnModifier;
    @FXML
    private TextField tftext;
    @FXML
    private Button imageButton;
    @FXML
    private ImageView imageView;
    private String imageName = null;
      private File imageFile;
    private Forum forum ;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void modifier(ActionEvent event) throws IOException {
        try {
            PiCRUD th = new PiCRUD();
            String tText = tftext.getText();
             forum.setDescription(tText);
              if (imageName != null) {
                   forum.setImage(imageName);
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
            }

            th.update(forum);
            //redirect 
        FXMLLoader loader = new FXMLLoader(getClass().getResource("ForumDetails.fxml"));
    Parent root = loader.load();
    ForumDeatilsController ds = loader.getController();
    
   
    } catch(Exception ex){
            System.out.println("ddd");
        
        
    }
    }
}
        

            
  

    
    

    
    

