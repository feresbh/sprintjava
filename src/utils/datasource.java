
package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class datasource {
    private static datasource data;
    private Connection con;
    public String user = "root";
    public String password = "";
    public String url = "jdbc:mysql://localhost:3306/jihene";
    private datasource() {
        try {
            con = DriverManager.getConnection(url, user, password);
            System.out.println("connecting...");
        } catch (SQLException ex) {
            Logger.getLogger(datasource.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public static datasource getInstance() {
        if (data == null) data = new datasource();
        return data;
    }
    public Connection getConnection(){
        return con;
    }
         
    }