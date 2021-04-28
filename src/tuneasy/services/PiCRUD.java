/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tuneasy.services;

import tuneasy.entities.Forum;
import tuneasy.utils.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.ResultSet;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
//import java.beans.Statement;

/**
 *
 * @author pc
 */
public class PiCRUD {
    
    public void addforum(Forum f){
       
            
        try {
            String requet = "INSERT INTO forum (name,email,description) VALUES ('"+f.getNom()+"','"+f.getEmail()+"','"+f.getDescription()+"')";
            
            Statement st=  Connection.getInstance().getConnection().createStatement();
            st.executeUpdate(requet);
            System.out.println("forum ajoute!");
        } catch (SQLException ex) {
              System.out.println(ex.getMessage());
        }
     
           
                    
    }
    
    public void addforum2(Forum f){
        
        try {
            String requete = "INSERT INTO forum (name,email,description,image)"+"VALUES(?,?,?,?)";
          PreparedStatement  pst =  Connection.getInstance().getConnection().prepareStatement(requete);
          pst.setString(1,f.getNom());
          pst.setString(2,f.getEmail());
          pst.setString(3,f.getDescription());
           pst.setString(4,f.getImage());
          pst.executeUpdate();
            System.out.println("forum added!");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }  
    }
    
    public void delete (int id){
 
             
        
                try {
                         String requete = "DELETE FROM forum WHERE id=?";
                 PreparedStatement pst = Connection.getInstance().getConnection().prepareStatement(requete);
                     pst.setInt(1, id);
          pst.executeUpdate();
                    System.out.println("DELETE FROM forum WHERE id="+id);
                } catch (SQLException ex) {
                     System.out.println(ex.getMessage());
                }
         
            }
    public void update (Forum forum){
            try {
                         String requete = "UPDATE `forum` SET  image= ?,description=? WHERE id=?";
                         
                 PreparedStatement pst = Connection.getInstance().getConnection().prepareStatement(requete);
                     pst.setInt(3, forum.getId()); 
                   pst.setString(2,forum.getDescription());
               //  pst.setString(2,forum.getNom());
                        pst.setString(1, forum.getImage());
        //  pst.setString(3,forum.getEmail());
         
         
         // pst.setInt(5, id);
         
                System.out.println("UPDATE `forum` SET"+"name=?,email=?,description=?");
          pst.executeUpdate();
                    System.out.println("updated");
                } catch (SQLException ex) {
                     System.out.println(ex.getMessage());
                }
        
    }
        public Forum findById(int id){

        try {
            String request = "SELECT * FROM sujet where id = "+ id;
            PreparedStatement pst = Connection.getInstance().getConnection().prepareStatement(request);
            ResultSet rs = pst.executeQuery(request);
            Forum sujet = new Forum();
            while (rs.next()) {
                sujet.setId(rs.getInt("id"));
                     sujet.setEmail(rs.getString("text"));
                sujet.setDescription(rs.getString("text"));
                sujet.setImage(rs.getString("image"));
            }
            return sujet;

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return null;
    }

    public List<Forum> getForums (){
                    List<Forum> mylist =new ArrayList();
                    
                   
        try {
            String requet ="select * FROM forum";
            Statement st =  Connection.getInstance().getConnection().createStatement();
            ResultSet rs= st.executeQuery(requet);
            while(rs.next()){
                Forum f = new Forum();
                f.setId(rs.getInt(1));
                f.setNom(rs.getString("name"));
                f.setEmail(rs.getString("email"));
                f.setDescription(rs.getString("description"));
                mylist.add(f);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            
        }
        return mylist ; 
    }
}
