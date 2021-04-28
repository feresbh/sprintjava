/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

import entities.vol;
import java.sql.SQLException;
import javafx.collections.ObservableList;

/**
 *
 * @author Admin
 */
public interface volInterface {
     public void DeleteVol(vol ex) throws SQLException;
      public void UpdatVol(vol vs) throws SQLException;
       public ObservableList<vol> afficheVol() throws SQLException;
       public void AddVol(vol v) throws SQLException;
    
}
