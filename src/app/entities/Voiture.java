/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.entities;

import javafx.scene.image.ImageView;

/**
 *
 * @author HP
 */
public class Voiture {
    
    private int id;
    private String marque;
    private String model;
    private String couleur;
    private boolean diponibilite;
    private String serie;
    private int prix;
    private String image;
    private ImageView img;
    
    public Voiture(int id, String marque, String model, String couleur, boolean diponibilite, String serie, int prix, String image) {
        this.id = id;
        this.marque = marque;
        this.model = model;
        this.couleur = couleur;
        this.diponibilite = diponibilite;
        this.serie = serie;
        this.prix = prix;
        this.image = image;
    }
    
    //show

    public Voiture(int id, String marque, String model, String couleur, boolean diponibilite, String serie, int prix, ImageView img) {
        this.id = id;
        this.marque = marque;
        this.model = model;
        this.couleur = couleur;
        this.diponibilite = diponibilite;
        this.serie = serie;
        this.prix = prix;
        this.img = img;
    }
            
    

    public Voiture() {
            //To change body of generated methods, choose Tools | Templates.
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMarque() {
        return marque;
    }

    public void setMarque(String marque) {
        this.marque = marque;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getCouleur() {
        return couleur;
    }

    public void setCouleur(String couleur) {
        this.couleur = couleur;
    }

    public boolean isDiponibilite() {
        return diponibilite;
    }

    public void setDiponibilite(boolean diponibilite) {
        this.diponibilite = diponibilite;
    }

    public String getSerie() {
        return serie;
    }

    public void setSerie(String serie) {
        this.serie = serie;
    }

    public int getPrix() {
        return prix;
    }

    public void setPrix(int prix) {
        this.prix = prix;
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
    
    

    @Override
    public String toString() {
        return "Voiture{" + "id=" + id + ", marque=" + marque + ", model=" + model + ", couleur=" + couleur + ", diponibilite=" + diponibilite + ", serie=" + serie + ", prix=" + prix + ", image=" + image + '}';
    }
    
    
    
}
