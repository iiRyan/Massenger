package com.rayan.messenger.rest.service;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ibm.cloud.cloudant.v1.Cloudant;
import com.ibm.cloud.cloudant.v1.model.AllDocsResult;
import com.ibm.cloud.cloudant.v1.model.DeleteDocumentOptions;
import com.ibm.cloud.cloudant.v1.model.Document;
import com.ibm.cloud.cloudant.v1.model.DocumentResult;
import com.ibm.cloud.cloudant.v1.model.GetDocumentOptions;
import com.ibm.cloud.cloudant.v1.model.PostAllDocsOptions;
import com.ibm.cloud.cloudant.v1.model.PostDocumentOptions;
import com.ibm.cloud.cloudant.v1.model.PutDocumentOptions;
import com.ibm.cloud.sdk.core.service.exception.ServiceResponseException;
import com.rayan.messenger.rest.database.cloudant.CloudantClient;
import com.rayan.messenger.rest.database.cloudant.CloudantDBManager;
import com.rayan.messenger.rest.mapper.ProfileMapper;
import com.rayan.messenger.rest.model.Profile;

public class ProfileService {
   
    
    private Cloudant client;
    private ObjectMapper objectMapper = new ObjectMapper();
    private CloudantDBManager cloudantDBManager = new CloudantDBManager();


    
    public ProfileService() {
        cloudantDBManager.init();
    }

    // lazy initialization
    private Cloudant getClient() {
        if (client == null) {
            client = CloudantClient.INSTANCE.getCloudantClient();
        }
        return client;
    }

    public Profile getProfileById(String _id) {
        if (_id == null) {
            throw new IllegalArgumentException("_id must not be null!");
        }
        try {

            GetDocumentOptions documentOptions = new GetDocumentOptions.Builder()
                    .db(CloudantDBManager.TABLE_PROFILE)
                    .docId(_id)
                    .build();

            Document response = getClient().getDocument(documentOptions).execute()
                    .getResult();
            Profile message = ProfileMapper.toProfile(response);

            return message;
        } catch (ServiceResponseException e) {
            System.out.println("Err: " + e.getMessage());
            return null;
        }

    }

    public List<Profile> getAllProfiles() {

        List<Profile> profileList = new ArrayList<>();
        try {
            // Create the options for fetching all documents
            PostAllDocsOptions options = new PostAllDocsOptions.Builder()
                    .db(CloudantDBManager.TABLE_PROFILE)
                    .includeDocs(true)
                    .build();

            // Execute the query
            AllDocsResult result = getClient()
                    .postAllDocs(options)
                    .execute()
                    .getResult();

            // Convert the results into Message objects
            result.getRows().forEach(row -> {
                if (row.getDoc() != null) {
                    try {
                        // Map document JSON to Profile object
                        Profile profile = objectMapper.readValue(row.getDoc().toString(), Profile.class);
                        profileList.add(profile);
                    } catch (Exception e) {
                        System.out.println("Error mapping document to Profile: " + e.getMessage());
                    }
                }
            });

        } catch (Exception e) {
            System.out.println("Error fetching all messages: " + e.getMessage());
        }

        return profileList;
    }

    public Profile insertProfile(Profile profile) {
        if (profile == null) {
            throw new IllegalArgumentException("Profile must not be null!");
        }

        PostDocumentOptions documentOptions = new PostDocumentOptions.Builder()
                .db(CloudantDBManager.TABLE_PROFILE)
                .document(ProfileMapper.toDocument(profile))
                .build();

        DocumentResult response = getClient().postDocument(documentOptions)
                .execute()
                .getResult();

        return getProfileById(response.getId());

    }

    public Profile updateProfile(Profile profile, String _id) {
        if (client == null) {
            client = CloudantClient.INSTANCE.getCloudantClient();
        }

        Profile currentProfile = getProfileById(_id);

        Document document = ProfileMapper.toDocument(profile);
        if (currentProfile != null) {
            String _rev = currentProfile.get_rev();
            PutDocumentOptions putDocumentOptions = new PutDocumentOptions.Builder()
                    .db(CloudantDBManager.TABLE_PROFILE)
                    .docId(_id)
                    .rev(_rev)
                    .document(document)
                    .build();

            DocumentResult response = getClient().putDocument(putDocumentOptions)
                    .execute()
                    .getResult();

        } else {
            insertProfile(profile);
        }

        return profile;
    }

    public void deleteProfile(String _id) {
        if (client == null) {
            client = CloudantClient.INSTANCE.getCloudantClient();
        }
        Profile currentProfile = getProfileById(_id);

        if (currentProfile != null) {
            String _rev = currentProfile.get_rev();

            DeleteDocumentOptions documentOptions = new DeleteDocumentOptions.Builder()
                    .db(CloudantDBManager.TABLE_PROFILE)
                    .docId(_id)
                    .rev(_rev)
                    .build();

            DocumentResult response = getClient().deleteDocument(documentOptions).execute()
                    .getResult();

            System.out.println(response);
        } else {
            System.out.println("Profile not found!");
        }

    }
}
