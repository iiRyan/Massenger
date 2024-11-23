package com.rayan.messenger.rest.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.xml.bind.annotation.XmlTransient;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Message {
    private String _id;
    private String _rev;
    private String message;
    private Date created;
    private String author;
    private List<Comment> comments = new ArrayList<>();

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

    public String get_rev() {
        return _rev;
    }

    public void set_rev(String _rev) {
        this._rev = _rev;
    }

    @Override
    public String toString() {
        return "Message [_id=" + _id + ", _rev=" + _rev + ", message=" + message + ", created=" + created + ", author="
                + author + ", comments=" + comments + "]";
    }

    // @JsonIgnore // Prevent Comment data from show up when call Message object only.
    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    

}
