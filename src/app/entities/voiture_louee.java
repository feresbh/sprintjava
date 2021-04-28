/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.entities;

import java.sql.Date;

/**
 *
 * @author HP
 */
public class voiture_louee {
    
    private int id;
    private Date datedebut;
    private Date datefin;
    private String serie;
    private int clientid;
    private int voiture_id;
    private int prix;

    public voiture_louee( Date datedebut, Date datefin, String serie, int clientid, int voiture_id, int prix) {
        this.datedebut = datedebut;
        this.datefin = datefin;
        this.serie = serie;
        this.clientid = clientid;
        this.voiture_id = voiture_id;
        this.prix = prix;
    }

    public voiture_louee(int id, Date datedebut, Date datefin, String serie, int clientid, int voiture_id, int prix) {
        this.id = id;
        this.datedebut = datedebut;
        this.datefin = datefin;
        this.serie = serie;
        this.clientid = clientid;
        this.voiture_id = voiture_id;
        this.prix = prix;
    }
    

    public voiture_louee() {//To change body of generated methods, choose Tools | Templates.
    }

    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDatedebut() {
        return datedebut;
    }

    public void setDatedebut(Date datedebut) {
        this.datedebut = datedebut;
    }

    public Date getDatefin() {
        return datefin;
    }

    public void setDatefin(Date datefin) {
        this.datefin = datefin;
    }

    public String getSerie() {
        return serie;
    }

    public void setSerie(String serie) {
        this.serie = serie;
    }

    public int getClientid() {
        return clientid;
    }

    public void setClientid(int clientid) {
        this.clientid = clientid;
    }

    public int getVoiture_id() {
        return voiture_id;
    }

    public void setVoiture_id(int voiture_id) {
        this.voiture_id = voiture_id;
    }
    public int getPrix() {
        return prix;
    }

    public void setPrix(int prix) {
        this.prix = prix;
    }
    
    
}
