/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import entity.Materiel;
import java.sql.SQLException;
import java.util.List;
import entity.Materiel;
import utils.DataBase;

import java.sql.SQLException;
import java.util.List;
import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;


public class ServiceMateriel implements IService<Materiel> {

    private Connection con;
    private Statement ste;
    private PreparedStatement pst ;
    private ResultSet res ;

    public ServiceMateriel() {
        con = DataBase.getInstance().getConnection();
    }
    
    
    @Override
    public void ajouter(Materiel a) throws SQLException {
        PreparedStatement PS = con.prepareStatement("INSERT INTO `Materiel` ( `type`, `description`, `prix`, `disponibilite`, `image`) VALUES (?, ?, ?, ?,?);");
        PS.setString(1, a.getType());
        PS.setString(2, a.getDescription());
        PS.setFloat(3, a.getPrix());
        PS.setBoolean(4, a.isDisponibilite());
        PS.setString(5, a.getImage());

        PS.executeUpdate();
    }

    
    @Override
    public void delete(int id) throws SQLException {
        PreparedStatement PS = con.prepareStatement("DELETE FROM `Materiel` WHERE `id`=?");
        PS.setInt(1,id);
        PS.executeUpdate();
    }

    @Override
    public void update(Materiel a,int id) throws SQLException {
        PreparedStatement PS=con.prepareStatement("UPDATE `Materiel` SET `type`=? ,`description`=?,`prix`=?,`disponibilite`=?,`image`=? WHERE `id`=?");
        PS.setString(1, a.getType());
        PS.setString(2, a.getDescription());
        PS.setFloat(3, a.getPrix());
        PS.setBoolean(4, a.isDisponibilite());
        PS.setString(5, a.getImage());
        PS.setInt(6, id);

        PS.executeUpdate();

    }

    

    @Override
    public List<Materiel> readAll() throws SQLException {
        List<Materiel> AL = new ArrayList<>();
        ste = con.createStatement();
        ResultSet rs = ste.executeQuery("select * from Materiel");
        while (rs.next()) {

            Materiel a = new Materiel(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getFloat(4), rs.getBoolean(5), rs.getString(6));
            AL.add(a);
        }
        return AL;
    }
    
        public ObservableList<Materiel> readAlll() throws SQLException {
        ObservableList<Materiel> AL = FXCollections.observableArrayList();;
        ste = con.createStatement();
        ResultSet rs = ste.executeQuery("select * from Materiel");
        while (rs.next()) {

            Materiel a = new Materiel(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getFloat(4), rs.getBoolean(5), rs.getString(6));
            AL.add(a);
        }
        return AL;
    }
    
  public Materiel getById(int id) {
          Materiel a = null;
         String requete = " select* from Materiel where id='"+id+"'" ;
        try {
           
            ste = con.createStatement();
            res=ste.executeQuery(requete);
            if (res.next())
            {a = new Materiel(res.getInt(1), res.getString(2), res.getString(3), res.getFloat(4), res.getBoolean(5), res.getString(6));}
        } catch (SQLException ex) {
            Logger.getLogger(ServiceMateriel.class.getName()).log(Level.SEVERE, null, ex);
        }
        return a ;
        
    }

   
}
