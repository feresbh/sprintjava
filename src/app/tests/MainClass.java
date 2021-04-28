/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.tests;

import app.entities.Voiture;
import app.entities.voiture_louee;
import app.services.VoitureCrud;
import app.services.voiture_loueeCrud;
import app.tools.MyConnection;
import com.restfb.FacebookClient;
import java.sql.Date;
import java.sql.SQLException;
import java.time.Instant;
import java.time.LocalDate;
import java.time.Month;
import java.time.ZoneId;
import static java.time.temporal.ChronoUnit.DAYS;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author HP
 */
public class MainClass {
    
    public static void main(String[] args) {
        
        String accessToken = "EAAnZCLfKVL3kBAFaoLf0EkUZC5D6ZBnephhs9J0qXvnwoiJIS3EsQipTJctGBf1pVSOltyJZC66h9rii8NMRVIIuzC4Ot2ToSDpjvXDm5xcJSXT8m3wmpcy8e4XeG6i6jxIiSwZAYBz9QZBBjodXV36ZCbK6Ctas6Bw5xRBQMJ6YaNYNOk0GYogtZASJ4NgEPdtGPZBqZA4fHe7jH2qLlMEFagRrt8K429syqrv3LPK1TVZBQZDZD";
        FacebookClient fbClient ;
        //fbClient.acc
        
        /*
        //MyConnection mc = new MyConnection();
        VoitureCrud vc = new VoitureCrud();
        voiture_loueeCrud vlc = new voiture_loueeCrud();
        String serie = "test";
        //converting date to local date so i can compare the number of days
        Date debut = new Date(2021-1900, 04, 19);
        Date fin = new Date(2021-1900, 04, 30);
        
        voiture_louee vl = new voiture_louee(debut, fin,serie, 1, 47,1);
        Voiture v = new Voiture();
        try {
            //vlc.addlocation(vl);
            vlc.Deletelocation(vl);
            
            /*v=vc.getVoitures().get(0);
            
            
            LocalDate d= debut.toLocalDate();
            LocalDate f= fin.toLocalDate();
            long days = DAYS.between(d, f);
            System.out.println("le nombre de jours est");
            System.out.println(days);
            
            // calcul de prix tot
            long prix = v.getPrix()*days;
            System.out.println(v.getPrix());
            System.out.println("le prix total du location est");
            System.out.println(prix);*/
            
            // now you need a way to select voiture.prix and multiply it by days to get the total cost of tha allocation
            
            
            /* try {
            vlc.Deletelocation(vl);
            } catch (SQLException ex) {
            Logger.getLogger(MainClass.class.getName()).log(Level.SEVERE, null, ex);
            }*/
            
            //Voiture v = new Voiture(0, "test3", "test3", "bleu", false, "224tun224", 100, "image");
            //vc.addVoiture2(v);
            //System.out.println(vc.getVoitures());
            /*
        } catch (SQLException ex) {
            Logger.getLogger(MainClass.class.getName()).log(Level.SEVERE, null, ex);
        }*/
    }
    
}
