package com.rayan.messenger.rest.service;

import java.util.ArrayList;
import java.util.List;

import com.rayan.messenger.rest.model.Message;

/*
 * Mock service
 */
public class MessageService {
    
    public List<Message> getAllMessages(){
        Message m1 = new Message(1L,"Hello JAX-RS","Rayan");
        Message m2 = new Message(2L,"Hello OpenLiberty","Jax");
        Message m3 = new Message(3L,"Hello Java","James Gosling");
        Message m4 = new Message(4L,"Hello World","Dua");
        List<Message> messagesList = new ArrayList<>();
        messagesList.add(m1);
        messagesList.add(m2);
        messagesList.add(m3);
        messagesList.add(m4);
        return messagesList;
    }
}
