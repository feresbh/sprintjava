/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tuneasy.entities;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
 
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.List;
import com.itextpdf.text.ListItem;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import java.util.ArrayList;
import tuneasy.entities.Materiel;
import tuneasy.utils.DataBase;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.collections.ObservableList;
import tuneasy.services.ServiceMateriel;

/**
 *
 * @author asus
 */
public class Pdf {
        private Connection con;
        private Statement ste;
    public Pdf()  {
        con = DataBase.getInstance().getConnection();
          
    
}
    public void add(String file) throws FileNotFoundException, SQLException, DocumentException{
        
        /* Create Connection objects */
//                con = DataBase.getInstance().getConnection();
                Document my_pdf_report = new Document();
                PdfWriter.getInstance(my_pdf_report, new FileOutputStream(file));
                my_pdf_report.open();            
                //we have four columns in our table
                PdfPTable my_report_table = new PdfPTable(2);
                //create a cell object
                PdfPCell table_cell;
                ServiceMateriel ser = new ServiceMateriel();
                ObservableList<Materiel> list = ser.readAlll();
                for (Materiel aux : list){  
                                table_cell=new PdfPCell(new Phrase("Type"));
                                my_report_table.addCell(table_cell);
                                table_cell=new PdfPCell(new Phrase(aux.getType()));
                                my_report_table.addCell(table_cell);
                                table_cell=new PdfPCell(new Phrase("Description"));
                                my_report_table.addCell(table_cell);
                                table_cell=new PdfPCell(new Phrase(aux.getDescription()));
                                my_report_table.addCell(table_cell);
                                table_cell=new PdfPCell(new Phrase("Prix"));
                                my_report_table.addCell(table_cell);
                                table_cell=new PdfPCell(new Phrase(String.valueOf(aux.getPrix())));
                                my_report_table.addCell(table_cell);
                                table_cell=new PdfPCell(new Phrase("Disponibiliter"));
                                my_report_table.addCell(table_cell);
                                if(aux.isDisponibilite())
                                table_cell=new PdfPCell(new Phrase("Disponible"));
                                else
                                table_cell=new PdfPCell(new Phrase("Indisponible"));
                                my_report_table.addCell(table_cell);              
                }                           
                               
                                
                /* Attach report table to PDF */
                my_pdf_report.add(my_report_table);                       
                my_pdf_report.close();
                
               /* Close all DB related objects */

        
    }
     
}
