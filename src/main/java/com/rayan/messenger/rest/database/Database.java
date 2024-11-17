package com.rayan.messenger.rest.database;

import java.util.HashMap;
import java.util.Map;
import com.rayan.messenger.rest.model.Message;


/*
 * This class simulate Database 
 */

public class Database {
    private static Map<Long,Message> messages = new HashMap<>();

    public static Map<Long, Message> getMessages() {
        return messages;
    }

    
}
