package com.rayan.messenger.rest.model;

import java.util.Date;

public class Message {
    private long id = 1;
    private String message;
    private Date created;
    private String author;
    private static long counter;

    public Message() {
    }

    public Message(String message, Date created, String author) {
        this.message = message;
        this.created = created;
        this.author = author;
        this.id = counter;
        counter++;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    @Override
    public String toString() {
        return "Message [id=" + id + ", message=" + message + ", created=" + created + ", author=" + author + "]";
    }

    
}
