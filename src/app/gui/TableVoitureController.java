/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.gui;

import app.entities.Voiture;
import app.services.VoitureCrud;
import app.tools.MyConnection;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import app.tools.MyConnection;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import static com.itextpdf.text.Element.ALIGN_CENTER;
import com.itextpdf.text.Font;
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.awt.Desktop;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import static java.lang.Integer.parseInt;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Hyperlink;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.Effect;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.InputMethodEvent;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 * FXML Controller class
 *
 * @author HP
 */
public class TableVoitureController implements Initializable {

    static MyConnection ds = MyConnection.getInstance();
    @FXML
    private TextField resmarque;
    private TextField resmodel;
    @FXML
    private TextField rescouleur;
    @FXML
    private TextField resdisponibilite;
    @FXML
    private TextField resserie;
    @FXML
    private TextField resprix;
    @FXML
    private TextField resimage;
    @FXML
    public TableView<Voiture> table;
    @FXML
    private TableColumn<Voiture, String> col_id;
    @FXML
    private TableColumn<Voiture, String> col_marque;
    @FXML
    private TableColumn<Voiture, String> col_model;
    @FXML
    private TableColumn<Voiture, String> col_couleur;
    @FXML
    private TableColumn<Voiture, String> col_disponibilite;
    @FXML
    private TableColumn<Voiture, String> col_serie;
    @FXML
    private TableColumn<Voiture, String> col_prix;
    @FXML
    private TableColumn<Voiture, ImageView> col_image;

    ObservableList<Voiture> oblist = FXCollections.observableArrayList();
    @FXML
    private ImageView imagebox;
    @FXML
    private Button btnvoitures;
    @FXML
    private Button btnhotels;
    @FXML
    private Button btnvols;
    @FXML
    private Button btncamping;
    @FXML
    private Button btnbon_plans;
    @FXML
    private Button btnblog;
    @FXML
    private Button btnutilisateurs;
    @FXML
    private ImageView logo;
    @FXML
    private TextField search_bar;
    @FXML
    private Button btnadd;
    @FXML
    private Button import_image;
    @FXML
    private Button btn_exel;
    @FXML
    private Hyperlink logoutLink;
    @FXML
    private Button btnpdf;
    @FXML
    private ColorPicker mycolorpicker;
    @FXML
    private Rectangle rectangle;
    @FXML
    private ComboBox cb_model;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        showCat();

    }

    public void setResmarque(String resmarque) {
        this.resmarque.setText(resmarque);
    }

    public void setResmodel(String resmodel) {
        this.resmodel.setText(resmodel);
    }

    public void setRescouleur(String rescouleur) {
        this.rescouleur.setText(rescouleur);
    }

    public void setResdisponibilite(String resdisponibilite) {
        this.resdisponibilite.setText(resdisponibilite);
    }

    public void setResserie(String resserie) {
        this.resserie.setText(resserie);
    }

    public void setResprix(String resprix) {
        this.resprix.setText(resprix);
    }

    public void setResimage(String resimage) {
        this.resimage.setText(resimage);
    }

    
    public void showCat() {
        VoitureCrud vc = new VoitureCrud();
        ObservableList<Voiture> voiturelist = vc.getVoitures();
        //ObservableList<Voiture> voiturelist= getVoitures();

        col_id.setCellValueFactory(new PropertyValueFactory<>("id"));
        col_marque.setCellValueFactory(new PropertyValueFactory<>("marque"));
        col_model.setCellValueFactory(new PropertyValueFactory<>("model"));
        col_couleur.setCellValueFactory(new PropertyValueFactory<>("couleur"));
        col_disponibilite.setCellValueFactory(new PropertyValueFactory<>("diponibilite"));
        col_serie.setCellValueFactory(new PropertyValueFactory<>("serie"));
        col_prix.setCellValueFactory(new PropertyValueFactory<>("prix"));
        col_image.setCellValueFactory(new PropertyValueFactory<>("img"));
        

        table.setItems(voiturelist);
    }

    @FXML
    private void voitures(MouseEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("tableVoiture.fxml"));
            Parent root = loader.load();
            TableVoitureController tv = loader.getController();
            btnvoitures.getScene().setRoot(root);
        } catch (IOException ex) {
            Logger.getLogger(Main_dashController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void hotels(MouseEvent event) {
    }

    @FXML
    private void vols(MouseEvent event) {
    }

    @FXML
    private void camping(MouseEvent event) {
    }

    @FXML
    private void bon_plans(MouseEvent event) {
    }

    @FXML
    private void blog(MouseEvent event) {
    }

    @FXML
    private void utilisateurs(MouseEvent event) {
    }

    @FXML
    private void supprimervoiture(ActionEvent event) {

        try {
            VoitureCrud vc = new VoitureCrud();
            Voiture v = table.getSelectionModel().getSelectedItem();
            vc.DeleteVoiture(v);
            table.getItems().clear();
            showCat();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    private void modifiervoiture(ActionEvent event) {
        try {

            Color mycolor = mycolorpicker.getValue();

            VoitureCrud vc = new VoitureCrud();
            Voiture v = table.getSelectionModel().getSelectedItem();
            v.setMarque(resmarque.getText());
            v.setModel(cb_model.getSelectionModel().getSelectedItem().toString());

            v.setCouleur(mycolor.toString());
            //v.setCouleur(rescouleur.getText());

            v.setDiponibilite(true);
            v.setSerie(resserie.getText());
            v.setPrix(parseInt(resprix.getText()));
            v.setImage(resimage.getText());
            vc.updatevoiture(v);
            showCat();
        } catch (SQLException ex) {
            Logger.getLogger(TableVoitureController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    private void showdetail(MouseEvent event) {
        InputStream stream = null;

        Voiture v = table.getSelectionModel().getSelectedItem();
        setResmarque(v.getMarque());
        cb_model.getSelectionModel().select(v.getModel());
        setRescouleur(v.getCouleur());
        setResdisponibilite(String.valueOf(v.isDiponibilite()));
        setResserie(v.getSerie());
        setResprix(String.valueOf(v.getPrix()));

        Color mycolor = Color.web(v.getCouleur());
        rectangle.setFill(mycolor);

        try {
            stream = new FileInputStream("C:\\Users\\HP\\Documents\\school\\SEM2\\PiDev\\SprintWeb\\public\\images\\voiture\\" + v.getImage() + "");
        } catch (FileNotFoundException ex) {
            Logger.getLogger(TableVoitureController.class.getName()).log(Level.SEVERE, null, ex);
        }
        Image image = new Image(stream);
        setResimage(v.getImage());
        imagebox.setImage(image);

    }


    @FXML
    private void search(KeyEvent event) {
        System.out.println("i'm searching");
        VoitureCrud vc = new VoitureCrud();
        String x = search_bar.getText();

        if (x.isEmpty()) {
            showCat();
        } else {
            ObservableList<Voiture> voiturelist = vc.search(x);
            col_id.setCellValueFactory(new PropertyValueFactory<>("id"));
            col_marque.setCellValueFactory(new PropertyValueFactory<>("marque"));
            col_model.setCellValueFactory(new PropertyValueFactory<>("model"));
            col_couleur.setCellValueFactory(new PropertyValueFactory<>("couleur"));
            col_disponibilite.setCellValueFactory(new PropertyValueFactory<>("diponibilite"));
            col_serie.setCellValueFactory(new PropertyValueFactory<>("serie"));
            col_prix.setCellValueFactory(new PropertyValueFactory<>("prix"));
            col_image.setCellValueFactory(new PropertyValueFactory<>("image"));

            table.setItems(voiturelist);
        }
    }

    @FXML
    private void ajout(MouseEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("AddVoiture.fxml"));
            Parent root = loader.load();
            AddVoitureController av = loader.getController();
            btnadd.getScene().setRoot(root);
        } catch (IOException ex) {
            Logger.getLogger(Main_dashController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void importerImageUpdate(ActionEvent event) {
        JFileChooser chooser = new JFileChooser();
        chooser.showOpenDialog(null);
        File f = chooser.getSelectedFile();
        String filename = f.getName();
        resimage.setText(filename);
        Image getAbsolutePath = null;
        ImageIcon icon = new ImageIcon(filename);
    }


    @FXML
    private void logout(ActionEvent event) {
    }

    @FXML
    private void export_pdf(MouseEvent event) {
        try {
            String file_name = "C:\\Users\\HP\\Documents\\school\\SEM2\\PiDev\\sprintjava\\MyApp\\tablevoitures.pdf";
            Document document = new Document();
            PdfWriter.getInstance(document, new FileOutputStream(file_name));
            document.open();
            Paragraph para = new Paragraph("Liste des voitures", new Font(FontFamily.HELVETICA, 18, Font.BOLDITALIC, new BaseColor(0, 0, 153)));
            para.setAlignment(ALIGN_CENTER);
            document.add(para);

            document.add(new Paragraph("  "));
            document.add(new Paragraph("  "));
            document.add(new Paragraph("  "));
            document.add(new Paragraph("  "));

            //ajout du table
            PdfPTable tablepdf = new PdfPTable(8);
            PdfPCell c1 = new PdfPCell(new Phrase("id", new Font(FontFamily.HELVETICA, 12, Font.BOLDITALIC, new BaseColor(240, 0, 0))));
            tablepdf.addCell(c1);

            c1 = new PdfPCell(new Phrase("marque", new Font(FontFamily.HELVETICA, 12, Font.BOLDITALIC, new BaseColor(240, 0, 0))));
            tablepdf.addCell(c1);

            c1 = new PdfPCell(new Phrase("model", new Font(FontFamily.HELVETICA, 12, Font.BOLDITALIC, new BaseColor(240, 0, 0))));
            tablepdf.addCell(c1);

            c1 = new PdfPCell(new Phrase("couleur", new Font(FontFamily.HELVETICA, 12, Font.BOLDITALIC, new BaseColor(240, 0, 0))));
            tablepdf.addCell(c1);

            c1 = new PdfPCell(new Phrase("disponibilite", new Font(FontFamily.HELVETICA, 12, Font.BOLDITALIC, new BaseColor(240, 0, 0))));
            tablepdf.addCell(c1);

            c1 = new PdfPCell(new Phrase("seire", new Font(FontFamily.HELVETICA, 12, Font.BOLDITALIC, new BaseColor(240, 0, 0))));
            tablepdf.addCell(c1);
            c1 = new PdfPCell(new Phrase("prix", new Font(FontFamily.HELVETICA, 12, Font.BOLDITALIC, new BaseColor(240, 0, 0))));
            tablepdf.addCell(c1);
            c1 = new PdfPCell(new Phrase("image", new Font(FontFamily.HELVETICA, 12, Font.BOLDITALIC, new BaseColor(240, 0, 0))));
            tablepdf.addCell(c1);

            tablepdf.setHeaderRows(1);

            String requete = "SELECT *FROM voiture";
            Statement st = MyConnection.getInstance().getCnx().createStatement();
            ResultSet rs = st.executeQuery(requete);

            while (rs.next()) {

                tablepdf.addCell(String.valueOf(rs.getInt(1)));
                tablepdf.addCell(rs.getString(2));
                tablepdf.addCell(rs.getString(3));
                tablepdf.addCell(rs.getString(4));
                tablepdf.addCell(String.valueOf(rs.getBoolean(5)));
                tablepdf.addCell(rs.getString(6));
                tablepdf.addCell(String.valueOf(rs.getInt(7)));
                tablepdf.addCell(rs.getString(8));

            }
            //adding the table
            document.add(tablepdf);

            document.close();
            File pdffile = new File("C:\\Users\\HP\\Documents\\school\\SEM2\\PiDev\\sprintjava\\MyApp\\tablevoitures.pdf");
            Desktop desktop = Desktop.getDesktop();
            desktop.open(pdffile);
            System.err.println("pdf exported succesfully");

        } catch (Exception e) {
            System.err.println(e);
        }

    }

    @FXML
    private void excel(ActionEvent event) {
        try {
            FileOutputStream file = null;

            Workbook wb = new HSSFWorkbook();
            Sheet sheet = wb.createSheet("Détails Activités");
            Row header = sheet.createRow(0);

            header.createCell(0).setCellValue("ID");
            header.createCell(1).setCellValue("Marque");
            header.createCell(2).setCellValue("Model");
            header.createCell(3).setCellValue("Couleur");
            header.createCell(4).setCellValue("Disponibilite");
            header.createCell(5).setCellValue("Serie");
            header.createCell(6).setCellValue("Prix");
            header.createCell(7).setCellValue("Image");
            header.createCell(8).setCellValue("updated at");

            String requete = "SELECT *FROM voiture";
            Statement st = MyConnection.getInstance().getCnx().createStatement();
            ResultSet rs = st.executeQuery(requete);
            int index = 1;
            while (rs.next()) {
                Row row = sheet.createRow(index);
                row.createCell(0).setCellValue(rs.getInt(1));
                row.createCell(1).setCellValue(rs.getString(2));
                row.createCell(2).setCellValue(rs.getString(3));
                row.createCell(3).setCellValue(rs.getString(4));

                row.createCell(4).setCellValue(rs.getBoolean(5));
                row.createCell(5).setCellValue(rs.getString(6));
                row.createCell(6).setCellValue(rs.getInt(7));
                row.createCell(7).setCellValue(rs.getString(8));
                row.createCell(8).setCellValue(rs.getString(9));
                index++;
            }

            try {
                file = new FileOutputStream("liste_des_voitures.xls");
                wb.write(file);
                file.close();
                File excelfile = new File("C:\\Users\\HP\\Documents\\school\\SEM2\\PiDev\\sprintjava\\MyApp\\liste_des_voitures.xls");
                Desktop desktop = Desktop.getDesktop();
                desktop.open(excelfile);

            } catch (IOException ex) {
                System.out.println("could not execute");
                Logger.getLogger(TableVoitureController.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (SQLException ex) {
            Logger.getLogger(TableVoitureController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void cbset(MouseEvent event) {
        String marque =resmarque.getText();
         

        ObservableList<String> list;
        if (marque.equalsIgnoreCase("mercedes")){
            list = FXCollections.observableArrayList("classe a","classe b","classe c","classe e","classe s");
            cb_model.setItems(list);
        }
        else if (marque.equalsIgnoreCase("bmw")){
            list = FXCollections.observableArrayList("serie 1","serie 3","serie 4","serie 5","x2","x3","x5");
            cb_model.setItems(list);
        }
        else if (marque.equalsIgnoreCase("audi")){
            list = FXCollections.observableArrayList("Q2","Q3","A3","A4","A5");
            cb_model.setItems(list);
        }
        else if (marque.equalsIgnoreCase("citroen")){
            list = FXCollections.observableArrayList("C3","c elysee","C4");
            cb_model.setItems(list);
        }
        else if (marque.equalsIgnoreCase("ford")){
            list = FXCollections.observableArrayList("fiesta","focus","ecosport");
            cb_model.setItems(list);
        }
        else if (marque.equalsIgnoreCase("hyundai")){
            list = FXCollections.observableArrayList("Grand I10","I20","Elentra");
            cb_model.setItems(list);
        }
        else if (marque.equalsIgnoreCase("kia")){
            list = FXCollections.observableArrayList("Picanto","Rio","Sportage","Seltos");
            cb_model.setItems(list);
        }
        else if (marque.equalsIgnoreCase("nissan")){
            list = FXCollections.observableArrayList("Micra","Juke","Qashqai");
            cb_model.setItems(list);
        }
        else if (marque.equalsIgnoreCase("peugeot")){
            list = FXCollections.observableArrayList("208","301","308","2008","3008");
            cb_model.setItems(list);
        }
        else if (marque.equalsIgnoreCase("renault")){
            list = FXCollections.observableArrayList("Symbol","Clio 4","Megane","Captur","Kadjar","Talisman");
            cb_model.setItems(list);
        }
        else if (marque.equalsIgnoreCase("seat")){
            list = FXCollections.observableArrayList("Ibiza","Leon","Arona","Ateca","Tarraco");
            cb_model.setItems(list);
        }
        else if (marque.equalsIgnoreCase("Skoda")){
            list = FXCollections.observableArrayList("Fabia","Scala","Kamiq","Octavia");
            cb_model.setItems(list);
        }
        else if (marque.equalsIgnoreCase("suzuki")){
            list = FXCollections.observableArrayList("Celerio","Dzire","Swift","Baleno");
            cb_model.setItems(list);
        }
        else if (marque.equalsIgnoreCase("toyota")){
            list = FXCollections.observableArrayList("Yaris","Corolla","C-HR","Rav 4","Land Cruiser");
            cb_model.setItems(list);
        }
        else if (marque.equalsIgnoreCase("volkswagen")){
            list = FXCollections.observableArrayList("Polo","Golf 7","Passat","Tguan","Touareg");
            cb_model.setItems(list);
        }
        else {
            list = FXCollections.observableArrayList(cb_model.getEditor().getText());
            cb_model.setItems(list);
        }
    }

}
