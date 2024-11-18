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

    
    
    // public MessageService() {
    //     messages.put(1L, new Message(1L,"Hello Java","James Gosling"));
    //     messages.put(2L, new Message(2L,"Hello World","Rayan"));
    //     messages.put(3L, new Message(3L,"Hello World","Rayan"));
    // }

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
