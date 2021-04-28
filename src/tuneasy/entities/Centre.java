/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tuneasy.entities;

import javafx.scene.image.ImageView;

/**
 *
 * @author asus
 */
public class Centre {
    private int id;
    private String nom;
    private String lieu;
    private String description;
    private String image;
    private ImageView img;

    public Centre() {
    }

    public Centre(int id, String nom, String lieu, String description, String image) {
        this.id = id;
        this.nom = nom;
        this.lieu = lieu;
        this.description = description;
        this.image = image;
    }

    public Centre(String nom, String lieu, String description, String image) {
        this.nom = nom;
        this.lieu = lieu;
        this.description = description;
        this.image = image;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public ImageView getImg() {
        return img;
    }

    public void setImg(ImageView img) {
        this.img = img;
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

    public String getLieu() {
        return lieu;
    }

    public void setLieu(String lieu) {
        this.lieu = lieu;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Camping{" + "id=" + id + ", nom=" + nom + ", lieu=" + lieu + ", description=" + description + '}';
    }
    
    
}
