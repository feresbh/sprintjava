/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import services.volservices;
import java.sql.SQLException;

public class AutoComplete {

    public static  String[] MotsAutoComplete() throws SQLException {
        
        volservices expS = new volservices();
        
        String[] data = expS.getData();

        return data;
    };
    
    public static  String[] MotsAutoAero() throws SQLException {
        
        volservices expS = new volservices();
        
        String[] data = expS.getaero();

        return data;
    };

}
