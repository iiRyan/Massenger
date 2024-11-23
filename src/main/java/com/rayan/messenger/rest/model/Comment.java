package com.rayan.messenger.rest.model;

import java.util.Date;

public class Comment {

    private String _id;
    private int _rev;
    private String comment;
    private Date created;
    private String author;

    public Comment() {
    }

    public Comment(String comment, Date created, String author) {
        this.comment = comment;
        this.created = created;
        this.author = author;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }


    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
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

    @Override
    public String toString() {
        return "Comment [_id=" + _id + ", _rev=" + _rev + ", comment=" + comment + ", created=" + created + ", author="
                + author + "]";
    }

    public int get_rev() {
        return _rev;
    }

    public void set_rev(int _rev) {
        this._rev = _rev;
    }

    

}