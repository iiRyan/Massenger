package com.rayan.messenger.rest.service;

import java.util.List;

import com.rayan.messenger.rest.database.cloudant.CloudantOperation;
import com.rayan.messenger.rest.model.Message;

/*
 * Mock service
 */
public class MessageService {

    private CloudantOperation operation = new CloudantOperation();


    public List<Message> getAllMessages() {
        return operation.getAllMessages();
    }

    public Message getMessage(String _id) {
        return operation.getMessageById(_id);
    }

    public Message insertMessage(Message theMessage){
        if (theMessage.getAuthor() == null || theMessage.getMessage() == null) {
            throw new IllegalArgumentException("Author and Message cannot be null");
        }
        return operation.insertMessage(theMessage);
    }

    public Message updateMessage(Message theMessage,String _id) {
        if(_id == null){
            throw new IllegalArgumentException("_id must not be null!");
        }
        return operation.updateMessage(theMessage,_id);
    }

    public void deleteMessage(String _id) {
        if(_id == null){
            throw new IllegalArgumentException("_id must not be null!");
        }
         operation.deleteMessage(_id);
    }
}
