/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tuneasy.entities;

import java.sql.Date;
import java.time.LocalDate;

/**
 *
 * @author pc
 */
public class Comments {
    public int id,forum_id;
    public String author ;
    public String content;
    public Forum forum;
    public Date created_at ;
           
    public Comments( int forum_id, String author, String content) {
           
        this.forum_id = forum_id;            
        this.author = author;      
        this.content = content;
        this.created_at = Date.valueOf(LocalDate.now());
    }

    public Comments(String author, String content) {
        this.author = author;
        this.content = content;
        this.created_at = Date.valueOf(LocalDate.now());
        
    }

  

   

    public Comments() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getForum_id() {
        return forum_id;
    }

    public void setForum_id(int forum_id) {
        this.forum_id = forum_id;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Date created_at) {
        this.created_at = created_at;
    }

    public Forum getForum() {
        return forum;
    }

    public void setForum(Forum forum) {
        this.forum = forum;
    }

    
    @Override
    public String toString() {
        return "Comments{" + "id=" + id + ", forum_id=" + forum_id + ", author=" + author + ", content=" + content + ", created_at=" + created_at + '}';
    }
    
    
}
