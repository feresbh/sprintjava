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
public class stats {
    private int count;
    private Date date;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public stats(int count, Date date) {
        this.count = count;
        this.date = date;
    }

    public stats() {
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 79 * hash + this.count;
        hash = 79 * hash + Objects.hashCode(this.date);
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
        final stats other = (stats) obj;
        if (this.count != other.count) {
            return false;
        }
        if (!Objects.equals(this.date, other.date)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "stats{" + "count=" + count + ", date=" + date + '}';
    }
    
    
    
    
}
