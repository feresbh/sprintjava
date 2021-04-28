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
public class reservation {
    private int id;
    private int vol_id;
    private int cin;
    private Date date_v;
    private int num_p;
    private String email;
    private int user_id;
    private Double prix_res;

    public reservation(int id, int vol_id, int cin, Date date_v, int num_p, String email, int user_id, Double prix_res) {
        this.id = id;
        this.vol_id = vol_id;
        this.cin = cin;
        this.date_v = date_v;
        this.num_p = num_p;
        this.email = email;
        this.user_id = user_id;
        this.prix_res = prix_res;
    }

    public Double getPrix_res() {
        return prix_res;
    }

    public void setPrix_res(Double prix_res) {
        this.prix_res = prix_res;
    }

   

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public reservation() {
    }

    public reservation(int id, int vol_id, int cin, Date date_v, int num_p, String email) {
        this.id = id;
        this.vol_id = vol_id;
        this.cin = cin;
        this.date_v = date_v;
        this.num_p = num_p;
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public int getVol_id() {
        return vol_id;
    }

    public int getCin() {
        return cin;
    }

    public Date getDate_v() {
        return date_v;
    }

    public int getNum_p() {
        return num_p;
    }

    public String getEmail() {
        return email;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setVol_id(int vol_id) {
        this.vol_id = vol_id;
    }

    public void setCin(int cin) {
        this.cin = cin;
    }

    public void setDate_v(Date date_v) {
        this.date_v = date_v;
    }

    public void setNum_p(int num_p) {
        this.num_p = num_p;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 47 * hash + this.id;
        hash = 47 * hash + this.vol_id;
        hash = 47 * hash + this.cin;
        hash = 47 * hash + Objects.hashCode(this.date_v);
        hash = 47 * hash + this.num_p;
        hash = 47 * hash + Objects.hashCode(this.email);
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
        final reservation other = (reservation) obj;
        if (this.id != other.id) {
            return false;
        }
        if (this.vol_id != other.vol_id) {
            return false;
        }
        if (this.cin != other.cin) {
            return false;
        }
        if (this.num_p != other.num_p) {
            return false;
        }
        if (!Objects.equals(this.email, other.email)) {
            return false;
        }
        if (!Objects.equals(this.date_v, other.date_v)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "reservation{" + "id=" + id + ", vol_id=" + vol_id + ", cin=" + cin + ", date_v=" + date_v + ", num_p=" + num_p + ", email=" + email + '}';
    }
    
    
    
    
}
