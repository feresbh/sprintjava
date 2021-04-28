/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import javafx.scene.image.ImageView;

/**
 *
 * @author asus
 */
public class Materiel {
    private int id;
    private String type;
    private String description;
    private float prix;
    private boolean disponibilite;
    private String image;
    private ImageView img;
    
    
    public Materiel() {
    }

    public Materiel(int id, String type, String description, float prix, boolean disponibilite,String image) {
        this.id = id;
        this.type = type;
        this.description = description;
        this.prix = prix;
        this.disponibilite = disponibilite;
        this.image = image;
    }

    public Materiel(String type, String description, float prix, boolean disponibilite,String image) {
        this.type = type;
        this.description = description;
        this.prix = prix;
        this.disponibilite = disponibilite;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public float getPrix() {
        return prix;
    }

    public void setPrix(float prix) {
        this.prix = prix;
    }

    public boolean isDisponibilite() {
        return disponibilite;
    }

    public void setDisponibilite(boolean disponibilite) {
        this.disponibilite = disponibilite;
    }

    @Override
    public String toString() {
        return "Materiel{" + "id=" + id + ", type=" + type + ", description=" + description + ", prix=" + prix + ", disponibilite=" + disponibilite + ", img=" + img + ", image=" + image + '}';
    }

    
    
}
