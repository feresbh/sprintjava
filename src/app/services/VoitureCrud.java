/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.services;

import app.entities.Voiture;
import app.tools.MyConnection;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.Effect;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 *
 * @author HP
 */
public class VoitureCrud {
    
    public void addVoiture(Voiture v){
        
        try {
            String requete ="INSERT INTO voiture(marque,model,couleur,serie,prix,image)"
                    + " VALUES ('"+v.getMarque()+"','"+v.getModel()+"','"+v.getCouleur()+"','"+v.getSerie()+"','"+v.getPrix()+"','"+v.getImage()+"') ";
            Statement st = MyConnection.getInstance().getCnx().createStatement();
            st.executeUpdate(requete);
            System.out.println("voiture ajouter!");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    public void addVoiture2(Voiture v){
        
        try {
            String requete ="INSERT INTO voiture(marque,model,couleur,diponibilite,serie,prix,image)"
                    + " VALUES (?,?,?,?,?,?,?) ";
            PreparedStatement pst = MyConnection.getInstance().getCnx().prepareStatement(requete);
            pst.setString(1, v.getMarque());
            pst.setString(2, v.getModel());
            pst.setString(3, v.getCouleur());            
            pst.setBoolean(4, v.isDiponibilite());
            pst.setString(5, v.getSerie());
            pst.setInt(6, v.getPrix());
            pst.setString(7, v.getImage());
            pst.executeUpdate();
            System.out.println("voiture ajouter!");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    public ObservableList<Voiture> getVoitures(){
        
        ObservableList<Voiture> mylist = FXCollections.observableArrayList();
        try {
            String requete ="SELECT *FROM voiture";
            Statement st = MyConnection.getInstance().getCnx().createStatement();
            ResultSet rs = st.executeQuery(requete);
            while(rs.next()){
                Voiture v = new Voiture();
                ImageView img=new ImageView();
                Image image;
                try{
                    image= new Image(new FileInputStream("C:\\Users\\HP\\Documents\\school\\SEM2\\PiDev\\SprintWeb\\public\\images\\voiture\\"+rs.getString("image")));
                    img.setImage(image);
                    Effect DropShadow = new DropShadow();
                    img.setEffect(DropShadow);
                    img.setPreserveRatio(true);
                    img.setFitWidth(70);
                    img.setFitHeight(70);

                }
                catch(FileNotFoundException ex){
                    System.out.println(ex.getMessage());
                }
                v.setId(rs.getInt(1));
                v.setMarque(rs.getString(2));
                v.setModel(rs.getString(3));
                v.setCouleur(rs.getString(4));
                v.setDiponibilite(rs.getBoolean(5));
                v.setSerie(rs.getString(6));
                v.setPrix(rs.getInt(7));
                v.setImage(rs.getString(8));
                v.setImg(img);
                mylist.add(v);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
        return mylist;
    }
    public ObservableList<Voiture> getVoiture( int x){
        ObservableList<Voiture> mylist = FXCollections.observableArrayList();
        try {
            String requete ="SELECT * FROM voiture WHERE id = "+x+";";
            Statement st = MyConnection.getInstance().getCnx().createStatement();
            ResultSet rs = st.executeQuery(requete);
            while(rs.next()){
                Voiture v = new Voiture();
                v.setId(rs.getInt(1));
                v.setMarque(rs.getString(2));
                v.setModel(rs.getString(3));
                v.setCouleur(rs.getString(4));
                v.setDiponibilite(rs.getBoolean(5));
                v.setSerie(rs.getString(6));
                v.setPrix(rs.getInt(7));
                v.setImage(rs.getString(8));
                mylist.add(v);
            }
                
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
        return mylist;
    }
    
    public ObservableList<Voiture> search(String x){
        
        ObservableList<Voiture> mylist = FXCollections.observableArrayList();
        try {
            String requete ="SELECT * FROM voiture WHERE  marque = '"+x+"' ";
            Statement st = MyConnection.getInstance().getCnx().createStatement();
            ResultSet rs = st.executeQuery(requete);
            
            while(rs.next()){
                Voiture v = new Voiture();
                v.setId(rs.getInt(1));
                v.setMarque(rs.getString(2));
                v.setModel(rs.getString(3));
                v.setCouleur(rs.getString(4));
                v.setDiponibilite(rs.getBoolean(5));
                v.setSerie(rs.getString(6));
                v.setPrix(rs.getInt(7));
                v.setImage(rs.getString(8));
                mylist.add(v);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
        return mylist;
    }
    
    
    public void DeleteVoiture(Voiture ex) throws SQLException {

        String requete = "Delete from voiture where voiture.id = "+ex.getId()+";";
        Statement st = MyConnection.getInstance().getCnx().createStatement();
        st.executeUpdate(requete);
        System.out.println("voiture supprimer!");

    }
    
    
    public void updatevoiture(Voiture v)throws SQLException {
        String requete ="UPDATE voiture SET marque=?,model=?,couleur=?,diponibilite=?,serie=?,prix=?,image=? WHERE id="+v.getId()+"";
        
            PreparedStatement pst = MyConnection.getInstance().getCnx().prepareStatement(requete);
            
            pst.setString(1, v.getMarque());
            pst.setString(2, v.getModel());
            pst.setString(3, v.getCouleur());            
            pst.setBoolean(4, v.isDiponibilite());
            pst.setString(5, v.getSerie());
            pst.setInt(6, v.getPrix());
            pst.setString(7, v.getImage());
            pst.executeUpdate();
            System.out.println("voiture modifier!");
    }
}
