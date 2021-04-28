/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tuneasy.services;

import tuneasy.entities.Comments;
import tuneasy.entities.Forum;
import tuneasy.utils.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author pc
 */
public class CommentCRUD {
    
    public void addComments(Comments c){

        try {
           /* String requete = "INSERT INTO post (text,post_id,image,user_id) " + "VALUES ('"+p.getText()+"','"+p.getPost().getId()+"','"+p.getImage()+"',2)";*/
           //'"+c.getAuthor()+"','"+c.getContent()+"','"+c.getCreated_at()+"' ,'"+c.getForum_id()+"'
           System.out.println("111");    
           String requete = "INSERT INTO comments (author,content,created_at,forum_id) VALUES (?,?,?,?)";

            PreparedStatement pst = Connection.getInstance().getConnection().prepareStatement(requete);
            
            pst.setString(1,c.getAuthor());
            pst.setString(2,c.getContent());
            pst.setDate(3,c.getCreated_at());
            pst.setInt(4,c.getForum_id());
            
            System.out.println(c.getCreated_at());  
            
            pst.executeUpdate();
           
            System.out.println("comment ajouté!");    
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }
    public List<Comments> getPosts(){
        List<Comments> myList = new ArrayList<>();

        try {
            String requete = "SELECT * FROM Comments";
            Statement st = Connection.getInstance().getConnection().createStatement();
            ResultSet rs = st.executeQuery(requete);
            while(rs.next()){
                Comments s =new Comments();
                s.setId(rs.getInt(1));
                s.setContent(rs.getString("content"));
               // s.setForum(new PiCRUD().findById(rs.getInt("forum_id")));
                
                myList.add(s);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return myList;
    }
    
     public void Supprimer(Comments t) {
        String requete = "DELETE FROM comments WHERE id=?";
        try {
            PreparedStatement pst
                    = Connection.getInstance().getConnection().prepareStatement(requete);
            pst.setInt(1, t.getId());
            pst.executeUpdate();
            System.out.println("Post supprimé !");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }
       public void modifier(Comments s) {
        String requete = "UPDATE post set text= ?, image= ? WHERE id= ?";
        try {
            PreparedStatement pst = Connection.getInstance().getConnection().prepareStatement(requete);
            pst.setString(1, s.getContent());
           
            pst.setInt(3, s.getId());

            pst.executeUpdate();
            System.out.println("Post Modifié !");


        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
}


