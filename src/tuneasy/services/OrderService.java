/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tuneasy.services;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import tuneasy.entities.Order;
import tuneasy.utils.Connection;
import static tuneasy.utils.Connection.connection;

/**
 *
 * @author weixin
 */
public class OrderService {

    private Connection connection = new Connection();

    public void addOrder(Order order) {
        //String query = "INSERT INTO commande (idUser, description, total) VALUES (?,?,?)";
        
        String query = "INSERT INTO `order`(`idUser`, `description`, `total`) VALUES (?,?,?)";
        try {
            PreparedStatement preparedStatement = connection.getConnection().prepareStatement(query);
            preparedStatement.setInt(1, order.getIdUser());
            preparedStatement.setString(2, order.getDescription());
            preparedStatement.setDouble(3, order.getTotal());
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(OrderService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
  
    public void updateOrderStatus(int id) {
        //String query = "UPDATE order SET status= 'delivered' WHERE id = ?";
        String query="UPDATE `order` SET `status`= 'delivered',`is_paid`= 'delivered' WHERE `id`=?";
        try {
            PreparedStatement preparedStatement = connection.getConnection().prepareStatement(query);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
            System.out.println("Update Done");
        } catch (SQLException ex) {
            Logger.getLogger(OrderService.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
    

    public ObservableList<Order> showOrders() {
        ObservableList<Order> orderObservableList = FXCollections.observableArrayList();
        //String query = "SELECT (idUser, description, total, status) FROM order";
              
        String query = "  SELECT `id`, `idUser`, `description`, `total`, `status` FROM `order`";

                
        PreparedStatement preparedStatement;
        try {
            preparedStatement = connection.getConnection().prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                int idUser = resultSet.getInt("idUser");
                String description = resultSet.getString("description");
                double total = resultSet.getDouble("total");
                String status = resultSet.getString("status");
                Order order = new Order(id, idUser, description, total, status);
                orderObservableList.add(order);
            }
        } catch (SQLException ex) {
            Logger.getLogger(OrderService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return orderObservableList;
    }

    public int findLastOrder() {
        String query = "SELECT * FROM order";
        PreparedStatement preparedStatement;
        try {
            preparedStatement = connection.getConnection().prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.last();
            int id = resultSet.getInt("id");
            return id;
        } catch (SQLException ex) {
            Logger.getLogger(OrderService.class.getName()).log(Level.SEVERE, null, ex);
            return 0;
        }
    }
    
    
    public void exc(){ 
        String query = "  SELECT `id`, `idUser`, `description`, `total`, `status` FROM `order`";
           PreparedStatement preparedStatement;
        try {
           
            preparedStatement = connection.getConnection().prepareStatement(query);
                      

            ResultSet resultSet = preparedStatement.executeQuery();
          
           
            
                
           XSSFWorkbook wb= new XSSFWorkbook();
           XSSFSheet sheet= wb.createSheet("Order Details");
           XSSFRow header= sheet.createRow(0);
           header.createCell(0).setCellValue("ID");
           header.createCell(1).setCellValue("ID_User");
           header.createCell(2).setCellValue("Description");
           header.createCell(3).setCellValue("Total");
           header.createCell(4).setCellValue("Status");
           int index=1;
           
           while (resultSet.next()) {
              XSSFRow row=sheet.createRow(index);
              row.createCell(0).setCellValue(resultSet.getString("id"));
              row.createCell(1).setCellValue(resultSet.getString("idUser"));
              row.createCell(2).setCellValue(resultSet.getString("description"));
              row.createCell(3).setCellValue(resultSet.getString("total"));
              row.createCell(4).setCellValue(resultSet.getString("status"));
              index ++;
           }
           
              try (FileOutputStream fileOut = new FileOutputStream("C:\\Users\\MY HP\\Desktop\\JAVA_Pidev\\Final\\TunEasy2\\TunEasy2\\EXCEL\\OrderDetails.xlsx")) {
                  wb.write(fileOut);
                  
              }
           
             
                
                
              
           
           
        } catch (SQLException | FileNotFoundException ex) {
                  System.out.println("famma erreur excel 1");
          
        }   catch (IOException ex) {
                  System.out.println("famma erreur excel 2");
            }
        
    }


}
