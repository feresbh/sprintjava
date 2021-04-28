/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.services;

import app.entities.Voiture;
import app.entities.voiture_louee;
import app.tools.MyConnection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.Month;
import static java.time.temporal.ChronoUnit.DAYS;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author HP
 */
public class voiture_loueeCrud {
    
    public void addlocation(voiture_louee vl,Voiture v ){
        try {
            boolean dispo= false;
            /*System.out.println("les dates");
            System.out.println(vl.getDatedebut());
            System.out.println(vl.getId());*/
            
            //calcul de prix du location
            VoitureCrud vc = new VoitureCrud();
            v=vc.getVoiture(v.getId()).get(0);
            LocalDate debut =vl.getDatedebut().toLocalDate();
            LocalDate fin =vl.getDatefin().toLocalDate();
            long days = DAYS.between(debut, fin);    
            System.out.println("le nombre de jours est");
            System.out.println(days);
            long prix = v.getPrix()*days;
            System.out.println("le prix total du location est");
            System.out.println(prix);
            
            
            String requete ="INSERT INTO voiture_louee(datedebut,datefin,serie,clientid,voiture_id,prix)"
                    + "VALUES ('"+vl.getDatedebut()+"','"+vl.getDatefin()+"','"+v.getSerie()+"','"+vl.getClientid()+"','"+vl.getVoiture_id()+"','"+prix+"') ";
            Statement st = MyConnection.getInstance().getCnx().createStatement();
            
            st.executeUpdate(requete);
            //modification de la disponibilite de la voiture 
            requete ="UPDATE voiture SET diponibilite="+dispo+" WHERE id="+vl.getVoiture_id()+" ";
            st.executeUpdate(requete);
            
            
            System.out.println("location ajouter!");
        } catch (SQLException ex) {
            Logger.getLogger(voiture_loueeCrud.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void updatelocation(voiture_louee vl)throws SQLException {
        String requete ="UPDATE voiture_louee SET datedebut=?,datefin=? WHERE id="+vl.getId()+"";
        
            PreparedStatement pst = MyConnection.getInstance().getCnx().prepareStatement(requete);
            
            pst.setDate(1, vl.getDatedebut());
            pst.setDate(2, vl.getDatefin());
            pst.executeUpdate();
            System.out.println("location modifier!");
    }
    public void Deletelocation(voiture_louee vl)throws SQLException{
        boolean dispo= true;
        System.out.println(vl.getId());
        String requete = "Delete from voiture_louee WHERE id = "+vl.getId()+"";
        Statement st = MyConnection.getInstance().getCnx().createStatement();
        st.executeUpdate(requete);
        
        //modification de la disponibilite de la voiture 
        requete ="UPDATE voiture SET diponibilite="+dispo+" WHERE id="+vl.getVoiture_id()+" ";
        st.executeUpdate(requete);
            
        System.out.println("location supprimer!");
        
    }

    public ObservableList<voiture_louee> getlocations() {
        ObservableList<voiture_louee> mylist = FXCollections.observableArrayList();
        try {
            String requete ="SELECT *FROM voiture_louee";
            Statement st = MyConnection.getInstance().getCnx().createStatement();
            ResultSet rs = st.executeQuery(requete);
            while(rs.next()){
                voiture_louee vl = new voiture_louee();
                vl.setId(rs.getInt(1));
                vl.setDatedebut(rs.getDate(2));
                vl.setDatefin(rs.getDate(3));
                vl.setSerie(rs.getString(4));
                vl.setClientid(rs.getInt(5));
                vl.setVoiture_id(rs.getInt(6));
                vl.setPrix(rs.getInt(7));
                mylist.add(vl);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(voiture_loueeCrud.class.getName()).log(Level.SEVERE, null, ex);
        }
        return mylist;
    }
    
    
    
}
