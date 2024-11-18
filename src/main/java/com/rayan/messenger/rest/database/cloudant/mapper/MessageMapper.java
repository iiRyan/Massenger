package com.rayan.messenger.rest.database.cloudant.mapper;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ibm.cloud.cloudant.v1.model.Document;
import com.rayan.messenger.rest.model.Message;

public class MessageMapper {

    public static Document toDocument(Message message) {
        Document document = new Document();
        document.put("author", message.getAuthor());
        document.put("message", message.getMessage());
        document.put("created", message.getCreated());
        return document;
    }

    public static Message toMessage(Document document) {
        ObjectMapper objectMapper = new ObjectMapper();
        Message message;
        try {
            message = objectMapper.readValue(document.toString(), Message.class);
            return message;
        } catch (JsonProcessingException e) {
            System.out.println("Err: " + e);
            e.printStackTrace();
            return null;
        }
    }
}
