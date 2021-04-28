/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.SendMail;
import entities.reservation;
import entities.stats;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import utils.datasource;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Admin
 */
public class ReservationServices {
     Statement st;
    PreparedStatement pst;
    ResultSet res;
     private static final String URL = "https://api.smsmode.com/http/1.6/";
 private static final String PATH_SEND_SMS = "sendSMS.do";
 private static final String PATH_SEND_SMS_BATCH = "sendSMSBatch.do";
 private static final String ERROR_FILE = "The specified file does not exist";

    static datasource ds = datasource.getInstance();
     public ObservableList<reservation> afficheReservation() throws SQLException {

        String requete = "SELECT * FROM reservation ";
        ObservableList<reservation> rec = FXCollections.observableArrayList();
        PreparedStatement ste = ds.getConnection().prepareStatement(requete);
        res = ste.executeQuery(requete);

        while (res.next()) {
            reservation re = new reservation(res.getInt("id"),res.getInt("vol_id"),res.getInt("cin"),res.getDate("date_v"),res.getInt("num_p"), res.getString("email"),res.getInt("user_id"),res.getDouble("prix_res") );
            rec.add(re);
        }
        return rec;
    }
    public void AddReservation(reservation re) throws SQLException, MalformedURLException, UnsupportedEncodingException, IOException {
     
        String req = "INSERT INTO reservation( vol_id, cin, date_v, num_p, email,prix_res,user_id) VALUES (?,?,?,?,?,?,?)";
        PreparedStatement ste = ds.getConnection().prepareStatement(req);
           
          
           
        ste.setInt(1, re.getVol_id());
        ste.setInt(2,re.getCin());
        ste.setDate(3,re.getDate_v());
        ste.setInt(4, re.getNum_p());
        ste.setString(5, re.getEmail());
        ste.setDouble(6, re.getPrix_res());
        ste.setInt(7, re.getUser_id());
        SendMail m=new SendMail() ;
           m.send(re.getEmail(),"Activation du compte","jihenedorgham72@gmail.com","181JMT1043");
    

        ste.executeUpdate();
        
        String message = "Junk characters? method sendMultipartTextMessage only send text message. If you want to send non text message, you should look to method sendDataMessage. Below is the code excerpt from android cts. It has example on how to send long messages.";		
		String phone = "55839979";
		String username = "abcd";
		String password = "1234";
		String address = "http://192.168.1.101";
		String port = "8090";
		
		URL url = new URL(
				address+":"+port+"/SendSMS?username="+username+"&password="+password+
				"&phone="+phone+"&message="+URLEncoder.encode(message,"UTF-8"));
		
		URLConnection connection = url.openConnection();
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
		String inputLine;
		while((inputLine = bufferedReader.readLine()) !=null){
			System.out.println(inputLine);
		}
		bufferedReader.close();
         
       
    }
      public void DeleteReservation(reservation re) throws SQLException {

        String requete = "Delete from reservation where reservation.id=?";
        
        PreparedStatement ste = ds.getConnection().prepareStatement(requete);


        ste.setInt(1, re.getId());
        

        ste.executeUpdate();

    }
     public void UpdatReservation(reservation re) throws SQLException {

        String requete = "UPDATE reservation SET vol_id=?,cin=?,date_v=?,num_p=?,email=?,user_id=?,prix_res=? WHERE id=?";
        PreparedStatement ste = ds.getConnection().prepareStatement(requete);
        ste.setInt(1, re.getVol_id());
         ste.setInt(2, re.getCin());
         ste.setDate(3, re.getDate_v());
        ste.setInt(4, re.getNum_p());
         ste.setString(5, re.getEmail());
         ste.setInt(6, re.getUser_id());
          ste.setDouble(7, re.getPrix_res());
         ste.setInt(8, re.getId());
          
          
         
       ste.executeUpdate();
     }
     public int userres(reservation re) throws SQLException{
               int a=0;
           String req = "SELECT COUNT(*) AS rowcount FROM reservation WHERE user_id="+re.getUser_id();
           PreparedStatement ste = ds.getConnection().prepareStatement(req);
           
     
         
           res=ste.executeQuery(req);
           res.next();
           a=res.getInt("rowcount");
           System.out.println(a);
           return a;
     }
     
          public List<stats> StatsData() throws SQLException{
          String requete = "SELECT count(*),date_v FROM reservation GROUP BY date_v order by date_v ASC";
        List<stats> rec = new ArrayList<>();
          Statement ste = ds.getConnection().createStatement();
            res = ste.executeQuery(requete);
            while (res.next()) {
                 stats s=new stats(res.getInt(1),res.getDate(2));
                rec.add(s);
            }
            return rec;
            }

 
 

}
