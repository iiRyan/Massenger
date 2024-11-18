package com.rayan.messenger.rest.model;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Message {
    private String _id;
    private String message;
    private Date created;
    private String author;

    public Message() {
    }

    public Message(String message, String author) {
       
        this.message = message;
        this.created = new Date();
        this.author = author;
        
    }


    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    @Override
    public String toString() {
        return "Message [_id=" + _id + ", message=" + message + ", created=" + created + ", author=" + author + "]";
    }




    
}
