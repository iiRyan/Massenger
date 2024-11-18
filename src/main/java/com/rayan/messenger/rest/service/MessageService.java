package com.rayan.messenger.rest.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.rayan.messenger.rest.database.Database;
import com.rayan.messenger.rest.database.cloudant.CloudantOperation;
import com.rayan.messenger.rest.model.Message;

/*
 * Mock service
 */
public class MessageService {
 
    private CloudantOperation operation = new CloudantOperation();
    private Map<Long,Message> messages = Database.getMessages();

    public List<Message> getMessages(){
        return null;
    }
    
    // public MessageService() {
    //     messages.put(1L, new Message(1L,"Hello Java","James Gosling"));
    //     messages.put(2L, new Message(2L,"Hello World","Rayan"));
    //     messages.put(3L, new Message(3L,"Hello World","Rayan"));
    // }

    public List<Message> getAllMessages(){
        return operation.getAllMessages();
    }

    public Message getMessage(String _id){
        return operation.getMessageById(_id);
    }

    public Message addMessage(Message theMessage){
        // theMessage.set_id(messages.size() + 1);
        // messages.put(theMessage.get_id(), theMessage);
        return theMessage;
    }

    public Message updateMessage(Message theMessage){
        // if(theMessage.get_id() <= 0){
        //     return null;
        // }
        // messages.put(theMessage.get_id(), theMessage);
        return theMessage;
    }

    public Message removeMessage(long id){
        return messages.remove(id);
    }
}
