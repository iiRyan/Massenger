package com.rayan.messenger.rest.mapper;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ibm.cloud.cloudant.v1.model.Document;

import com.rayan.messenger.rest.model.Profile;

public class ProfileMapper {

    public static Document toDocument(Profile profile) {

        Document document = new Document();
        document.put("firstName", profile.getFirstName());
        document.put("lastName", profile.getLastName());
        document.put("email", profile.getEmail());
        document.put("age", profile.getAge());
        return document;
    }

    public static Profile toProfile(Document document) {
        ObjectMapper objectMapper = new ObjectMapper();
        Profile profile;
        try {
            profile = objectMapper.readValue(document.toString(), Profile.class);
            return profile;
        } catch (JsonProcessingException e) {
            System.out.println("Err: " + e);
            e.printStackTrace();
            return null;
        }
    }
}
