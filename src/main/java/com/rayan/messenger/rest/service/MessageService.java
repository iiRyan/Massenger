package com.rayan.messenger.rest.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.rayan.messenger.rest.database.Database;
import com.rayan.messenger.rest.model.Message;

/*
 * Mock service
 */
public class MessageService {
 
    private Map<Long,Message> messages = Database.getMessages();
    
    public List<Message> getAllMessages(){
        return new ArrayList<Message>(messages.values());
    }

    public Message getMessage(long id){
        return messages.get(id);
    }

    public Message addMessage(Message theMessage){
        theMessage.setId(messages.size() + 1);
        messages.put(theMessage.getId(), theMessage);
        return theMessage;
    }

    public Message updateMessage(Message theMessage){
        if(theMessage.getId() <= 0){
            return null;
        }
        messages.put(theMessage.getId(), theMessage);
        return theMessage;
    }

    public Message removeMessage(long id){
        return messages.remove(id);
    }
}
