package com.rayan.messenger.rest.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;



@JsonIgnoreProperties(ignoreUnknown = true)
public class Message {
    private String _id;
    private String _rev;
    private String message;
    private Date created;
    private String author;
    private List<Comment> comments = new ArrayList<>();
    private List<Link> links = new ArrayList<>();

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

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    
    public void addLinks(String url,String rel){
        Link link = new Link();
        link.setLink(url);
        link.setRel(rel);
        links.add(link);
    }

    public List<Link> getLinks() {
        return links;
    }

    public void setLinks(List<Link> links) {
        this.links = links;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
    

}
