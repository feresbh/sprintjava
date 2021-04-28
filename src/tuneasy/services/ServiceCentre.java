/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tuneasy.services;

import tuneasy.entities.Centre;
import java.sql.SQLException;
import java.util.List;
import tuneasy.utils.DataBase;

import java.sql.SQLException;
import java.util.List;
import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;


public class ServiceCentre implements IService<Centre> {

    private Connection con;
    private Statement ste;
    private PreparedStatement pst ;
    private ResultSet res ;

    public ServiceCentre() {
        con = DataBase.getInstance().getConnection();
    }
    
    
    @Override
    public void ajouter(Centre a) throws SQLException {
        PreparedStatement PS = con.prepareStatement("INSERT INTO `centre` ( `nom`, `lieu`, `description`,`image`) VALUES (?, ?, ?, ?);");
        PS.setString(1, a.getNom());
        PS.setString(2, a.getLieu());
        PS.setString(3, a.getDescription());
        PS.setString(4, a.getImage());

        PS.executeUpdate();
    }

    
    @Override
    public void delete(int id) throws SQLException {
        PreparedStatement PS = con.prepareStatement("DELETE FROM `centre` WHERE `id`=?");
        PS.setInt(1,id);
        PS.executeUpdate();
    }

    @Override
    public void update(Centre a,int id) throws SQLException {
        PreparedStatement PS=con.prepareStatement("UPDATE `centre` SET `nom`=? ,`lieu`=?,`description`=?,`image`=? WHERE `id`=?");
        PS.setString(1, a.getNom());
        PS.setString(2, a.getLieu());
        PS.setString(3, a.getDescription());
        PS.setString(4, a.getImage());
        PS.setInt(5, id);

        PS.executeUpdate();

    }

    

    @Override
    public List<Centre> readAll() throws SQLException {
        List<Centre> AL = new ArrayList<>();
        ste = con.createStatement();
        ResultSet rs = ste.executeQuery("select * from centre");
        while (rs.next()) {

            Centre a = new Centre(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5));
            AL.add(a);
        }
        return AL;
    }
    
  public Centre getById(int id) {
          Centre a = null;
         String requete = " select* from centre where id='"+id+"'" ;
        try {
           
            ste = con.createStatement();
            res=ste.executeQuery(requete);
            if (res.next())
            {a = new Centre(res.getInt(1), res.getString(2), res.getString(3), res.getString(4), res.getString(5));}
        } catch (SQLException ex) {
            Logger.getLogger(ServiceCentre.class.getName()).log(Level.SEVERE, null, ex);
        }
        return a ;
        
    }

   
}
