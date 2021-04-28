/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.vol;
import interfaces.volInterface;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import utils.datasource;

/**
 *
 * @author Admin
 */
public class volservices implements volInterface {

    Statement st;
    PreparedStatement pst;
    ResultSet res;

    static datasource ds = datasource.getInstance();

    @Override
    public void AddVol(vol v) throws SQLException {
        String req = "INSERT INTO vol ( id_v, aero_d, aero_a, date_d, date_a, place_d, prix, brochure_filename) VALUES (?,?,?,?,?,?,?,?)";
        PreparedStatement ste = ds.getConnection().prepareStatement(req);
  
        ste.setInt(1, v.getId_v());
        ste.setString(2, v.getAero_d());
        ste.setString(3, v.getAero_a());
        ste.setDate(4, v.getDate_d());
        ste.setDate(5, v.getDate_a());
        ste.setInt(6, v.getPlace());
        ste.setDouble(7, v.getPrix());
        ste.setString(8, v.getBrochure_filename());

        ste.executeUpdate();
    }
    @Override
    public ObservableList<vol> afficheVol() throws SQLException {

        String requete = "SELECT * FROM vol";
        ObservableList<vol> rec = FXCollections.observableArrayList();
        PreparedStatement ste = ds.getConnection().prepareStatement(requete);
        res = ste.executeQuery(requete);

        while (res.next()) {
            vol v = new vol(res.getInt("id"),res.getInt("id_v"),res.getString("aero_d"),res.getString("aero_a"), res.getDate("date_d"), res.getDate("date_a"),res.getInt("place_d"),res.getDouble("prix"), res.getString("brochure_filename"));
            rec.add(v);
        }
        return rec;
    }
    @Override
     public void UpdatVol(vol vs) throws SQLException {

        String requete = "UPDATE vol SET id_v=?,aero_d=?,aero_a=?,date_d=?,date_a=?,place_d=?,prix=?,brochure_filename=? WHERE id=?";
        PreparedStatement ste = ds.getConnection().prepareStatement(requete);
        ste.setInt(1, vs.getId_v());
         ste.setString(2, vs.getAero_d());
         ste.setString(3, vs.getAero_a());
        ste.setDate(4, vs.getDate_d());
         ste.setDate(5, vs.getDate_a());
         ste.setInt(6, vs.getPlace());
         ste.setDouble(7, vs.getPrix());
        ste.setString(8, vs.getBrochure_filename());
        ste.setInt(9, vs.getId());
       ste.executeUpdate();
     }
    @Override
         public void DeleteVol(vol ex) throws SQLException {

        String requete = "Delete from vol where vol.id=?";
        
        PreparedStatement ste = ds.getConnection().prepareStatement(requete);


        ste.setInt(1, ex.getId());
        

        ste.executeUpdate();

    }
         
         public String[] getData() throws SQLException {
        ArrayList<String> a = new ArrayList<String>();
        String requete = "SELECT * FROM vol";

        Statement ste = ds.getConnection().createStatement();
        res = ste.executeQuery(requete);

        while (res.next()) {
            a.add(res.getString(3));
            
        }

        return (String[]) a.toArray(new String[a.size()]);

    }
         
         public String[] getaero() throws SQLException {
        ArrayList<String> a = new ArrayList<String>();
        String requete = "SELECT aero_d FROM vol";

        Statement ste = ds.getConnection().createStatement();
        res = ste.executeQuery(requete);

        while (res.next()) {
            a.add(res.getString("aero_d"));
            
        }

        return (String[]) a.toArray(new String[a.size()]);

    }
         
         
             public ObservableList<vol> MyProductFilteredByName(String n) throws SQLException {

        ObservableList<vol> rec = FXCollections.observableArrayList();
        String requete = "Select * from vol where aero_d like '%" + n + "%'";
        Statement ste = ds.getConnection().createStatement();
        res = ste.executeQuery(requete);

        while (res.next()) {
            vol v = new vol(res.getInt("id"),res.getInt("id_v"),res.getString("aero_d"),res.getString("aero_a"), res.getDate("date_d"), res.getDate("date_a"),res.getInt("place_d"),res.getDouble("prix"), res.getString("brochure_filename"));
            rec.add(v);
        }
        return rec;
    }
             
             public List<vol> displayAllExposee() throws SQLException {

        String requete = "SELECT * FROM vol";
        List<vol> rec = new ArrayList<>();
        PreparedStatement ste = ds.getConnection().prepareStatement(requete);
        res = ste.executeQuery(requete);

        while (res.next()) {
            vol v = new vol(res.getInt("id"),res.getInt("id_v"),res.getString("aero_d"),res.getString("aero_a"), res.getDate("date_d"), res.getDate("date_a"),res.getInt("place_d"),res.getDouble("prix"), res.getString("brochure_filename"));
            rec.add(v);
        }
        return rec;
    }
}

    

