package com.rayan.messenger.rest.mapper;

import java.util.Date;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ibm.cloud.cloudant.v1.model.Document;
import com.rayan.messenger.rest.model.Message;

public class MessageMapper {

    public static Document toDocument(Message message) {
        if (message.getCreated() == null) {
            message.setCreated(new Date());
        }
        Document document = new Document();
        document.put("author", message.getAuthor());
        document.put("message", message.getMessage());
        document.put("created", message.getCreated());
        if (message.getComments() != null) {
            document.put("comments", message.getComments());
        }
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
