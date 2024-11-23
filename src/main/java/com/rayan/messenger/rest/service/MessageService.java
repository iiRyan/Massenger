package com.rayan.messenger.rest.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
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
import com.rayan.messenger.rest.mapper.MessageMapper;
import com.rayan.messenger.rest.model.Message;

public class MessageService {

    private Cloudant client;
    private ObjectMapper objectMapper = new ObjectMapper();
    private CloudantDBManager cloudantDBManager = new CloudantDBManager();
    
    public MessageService() {
        cloudantDBManager.init();
    }

    // lazy initialization
    private Cloudant getClient() {
        if (client == null) {
            client = CloudantClient.INSTANCE.getCloudantClient();
        }
        return client;
    }

    public Message getMessageById(String _id) {
        if (_id == null) {
            throw new IllegalArgumentException("_id must not be null!");
        }
        try {

            GetDocumentOptions documentOptions = new GetDocumentOptions.Builder()
                    .db(CloudantDBManager.TABLE_MESSENGER)
                    .docId(_id)
                    .build();

            Document response = getClient().getDocument(documentOptions).execute()
                    .getResult();
            Message message = MessageMapper.toMessage(response);

            return message;
        } catch (ServiceResponseException e) {
            System.out.println("Err in getMessagebyId: " + e.getMessage());
            return null;
        }

    }

    public List<Message> getAllMessages() {

        List<Message> messages = new ArrayList<>();
        try {
            // Create the options for fetching all documents
            PostAllDocsOptions options = new PostAllDocsOptions.Builder()
                    .db(CloudantDBManager.TABLE_MESSENGER)
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
                        // Map document JSON to Message object
                        Message message = objectMapper.readValue(row.getDoc().toString(), Message.class);
                        messages.add(message);
                    } catch (Exception e) {
                        System.out.println("Error mapping document to Message: " + e.getMessage());
                    }
                }
            });

        } catch (Exception e) {
            System.out.println("Error fetching all messages: " + e.getMessage());
        }

        return messages;
    }

    public List<Message> getMessageForYear(int year) {
        List<Message> messagesList = getAllMessages();
        List<Message> messagesForYear = new ArrayList<>();
        Calendar cal = Calendar.getInstance();

        for (Message message : messagesList) { // loop through all messages
            cal.setTime(message.getCreated()); // extract the year from given Date
            if (cal.get(Calendar.YEAR) == year) {
                messagesForYear.add(message);
            }
        }
        return messagesForYear;
    }

    public List<Message> getAllMessagePaginated(int start, int size) {
        List<Message> messagesList = getAllMessages();
        ArrayList<Message> list = new ArrayList<Message>(messagesList);
        if (start + size > list.size())
            return new ArrayList<Message>();
        return list.subList(start, size + 1);
    }

    public Message insertMessage(Message theMessage) {
        if (theMessage == null) {
            throw new IllegalArgumentException("theMessage must not be null!");
        }

        theMessage.setCreated(new Date());

        PostDocumentOptions documentOptions = new PostDocumentOptions.Builder()
                .db(CloudantDBManager.TABLE_MESSENGER)
                .document(MessageMapper.toDocument(theMessage))
                .build();

        DocumentResult response = getClient().postDocument(documentOptions)
                .execute()
                .getResult();

        return getMessageById(response.getId());

    }

    public Message updateMessage(Message message, String _id) {
        System.out.println("Message Service "+message);

        if (client == null) {
            client = CloudantClient.INSTANCE.getCloudantClient();
        }

        Message currentMessage = getMessageById(_id);

        Document document = MessageMapper.toDocument(message);
        if (currentMessage != null) {
            String _rev = currentMessage.get_rev();
            PutDocumentOptions putDocumentOptions = new PutDocumentOptions.Builder()
                    .db(CloudantDBManager.TABLE_MESSENGER)
                    .docId(_id)
                    .rev(_rev)
                    .document(document)
                    .build();

            DocumentResult response = getClient().putDocument(putDocumentOptions)
                    .execute()
                    .getResult();

        } else {
            insertMessage(message);
        }

        return message;
    }

    public void deleteMessage(String _id) {
        if (client == null) {
            client = CloudantClient.INSTANCE.getCloudantClient();
        }
        Message currentMessage = getMessageById(_id);

        if (currentMessage != null) {
            String _rev = currentMessage.get_rev();

            DeleteDocumentOptions documentOptions = new DeleteDocumentOptions.Builder()
                    .db(CloudantDBManager.TABLE_MESSENGER)
                    .docId(_id)
                    .rev(_rev)
                    .build();

            DocumentResult response = getClient().deleteDocument(documentOptions).execute()
                    .getResult();

            System.out.println(response);
        } else {
            System.out.println("Message not found!");
        }

    }
}
