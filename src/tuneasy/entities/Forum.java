/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tuneasy.entities;

import java.io.File;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 *
 * @author pc
 */
public class Forum {
    public int id;
    public String nom;
    public String email;
    public String description;
private String image;
  private ImageView imageView;

    public Forum( String nom, String email, String description) {
      
        this.nom = nom;
        this.email = email;
        this.description = description;
    }

    public Forum(int id, String nom, String email, String description, String image, ImageView imageView) {
        this.id = id;
        this.nom = nom;
        this.email = email;
        this.description = description;
        this.image = image;
        this.imageView = imageView;
    }

    
    

    public Forum() {
    }

    public Forum(String string, String string0, String string1, String string2, String string3) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

   

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

   

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public ImageView getImageView() {
        return imageView;
    }

    public void setImageView(ImageView imageView) {
        this.imageView = imageView;
    }
    

    @Override
    public String toString() {
        return "Forum{" + "id=" + id + ", nom=" + nom + ", email=" + email + ", description=" + description + '}';
    }
    
    
       public void initializeImageView() {
        File file = new File("src/assets/" + image);
        try {
            Image img = new Image(file.toURI().toString());
            imageView.setImage(img);
            imageView.setFitWidth(70);
            imageView.setFitHeight(70);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    
}
