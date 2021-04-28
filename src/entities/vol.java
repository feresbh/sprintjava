/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.sql.Date;
import java.util.Objects;

/**
 *
 * @author Admin
 */
public class vol {

    private int id;
    private int id_v;
    private String aero_d;
    private String aero_a;
    private Date date_d;
    private Date date_a;
    private int place;
    private Double prix;
    private String brochure_filename;

    public vol() {
    }

    public vol(int id, int id_v, String aero_d, String aero_a, Date date_d, Date date_a, int place, Double prix, String brochure_filename) {
        this.id = id;
        this.id_v = id_v;
        this.aero_d = aero_d;
        this.aero_a = aero_a;
        this.date_d = date_d;
        this.date_a = date_a;
        this.place = place;
        this.prix = prix;
        this.brochure_filename = brochure_filename;
    }

    public int getId() {
        return id;
    }

    public int getId_v() {
        return id_v;
    }

    public String getAero_d() {
        return aero_d;
    }

    public String getAero_a() {
        return aero_a;
    }

    public Date getDate_d() {
        return date_d;
    }

    public Date getDate_a() {
        return date_a;
    }

    public int getPlace() {
        return place;
    }

    public Double getPrix() {
        return prix;
    }

    public String getBrochure_filename() {
        return brochure_filename;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setId_v(int id_v) {
        this.id_v = id_v;
    }

    public void setAero_d(String aero_d) {
        this.aero_d = aero_d;
    }

    public void setAero_a(String aero_a) {
        this.aero_a = aero_a;
    }

    public void setDate_d(Date date_d) {
        this.date_d = date_d;
    }

    public void setDate_a(Date date_a) {
        this.date_a = date_a;
    }

    public void setPlace(int place) {
        this.place = place;
    }

    public void setPrix(Double prix) {
        this.prix = prix;
    }

    public void setBrochure_filename(String brochure_filename) {
        this.brochure_filename = brochure_filename;
    }

    @Override
    public String toString() {
        return "vol{" + "id=" + id + ", id_v=" + id_v + ", aero_d=" + aero_d + ", aero_a=" + aero_a + ", date_d=" + date_d + ", date_a=" + date_a + ", place=" + place + ", prix=" + prix + ", brochure_filename=" + brochure_filename + '}';
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 23 * hash + this.id;
        hash = 23 * hash + this.id_v;
        hash = 23 * hash + Objects.hashCode(this.aero_d);
        hash = 23 * hash + Objects.hashCode(this.aero_a);
        hash = 23 * hash + Objects.hashCode(this.date_d);
        hash = 23 * hash + Objects.hashCode(this.date_a);
        hash = 23 * hash + this.place;
        hash = 23 * hash + Objects.hashCode(this.prix);
        hash = 23 * hash + Objects.hashCode(this.brochure_filename);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final vol other = (vol) obj;
        if (this.id != other.id) {
            return false;
        }
        if (this.id_v != other.id_v) {
            return false;
        }
        if (this.place != other.place) {
            return false;
        }
        if (!Objects.equals(this.aero_d, other.aero_d)) {
            return false;
        }
        if (!Objects.equals(this.aero_a, other.aero_a)) {
            return false;
        }
        if (!Objects.equals(this.brochure_filename, other.brochure_filename)) {
            return false;
        }
        if (!Objects.equals(this.date_d, other.date_d)) {
            return false;
        }
        if (!Objects.equals(this.date_a, other.date_a)) {
            return false;
        }
        if (!Objects.equals(this.prix, other.prix)) {
            return false;
        }
        return true;
    }

}
