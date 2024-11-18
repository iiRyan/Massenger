package com.rayan.messenger.rest.database.cloudant;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ibm.cloud.cloudant.v1.model.AllDocsResult;
import com.ibm.cloud.cloudant.v1.model.Document;
import com.ibm.cloud.cloudant.v1.model.DocumentResult;
import com.ibm.cloud.cloudant.v1.model.GetDocumentOptions;
import com.ibm.cloud.cloudant.v1.model.PostAllDocsOptions;
import com.ibm.cloud.cloudant.v1.model.PostDocumentOptions;
import com.rayan.messenger.rest.model.Message;

public class CloudantOperation {

    private ObjectMapper objectMapper = new ObjectMapper();

    public Message getMessageById(String _id) {
        try {

            GetDocumentOptions documentOptions = new GetDocumentOptions.Builder()
                    .db("messenger_db")
                    .docId(_id)
                    .build();

            Document response = CloudantClient.INSTANCE.getCloudantClient().getDocument(documentOptions).execute()
                    .getResult();
            Message message = objectMapper.readValue(response.toString(), Message.class);

            return message;
        } catch (Exception e) {
            System.out.println("Err: " + e);
            return null;
        }

    }

    public List<Message> getAllMessages() {

        List<Message> messages = new ArrayList<>();
        try {
            // Create the options for fetching all documents
            PostAllDocsOptions options = new PostAllDocsOptions.Builder()
                    .db("messenger_db") // Replace with your database name
                    .includeDocs(true) // Include full document content
                    .build();

            // Execute the query
            AllDocsResult result = CloudantClient.INSTANCE.getCloudantClient()
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

    // Insert Bucket's objects into CloudantDB.
    public boolean saveMessage(Message theMessage) {

        DocumentResult result = null;
        try {
            // Manually map keys to values , I used this approach bcoz the structure of
            // Message is fixed
            // I could use library like Jackson but no need in this case.
            Document document = new Document();
            document.put("author", theMessage.getAuthor());
            document.put("message", theMessage.getMessage());
            document.put("createdAt", theMessage.getCreated());

            PostDocumentOptions createDocumentOptions = new PostDocumentOptions.Builder()
                    .db(CloudantDBManager.TABLE_BUCKET)
                    .document(document)
                    .build();

            result = CloudantClient.INSTANCE.getCloudantClient()
                    .postDocument(createDocumentOptions)
                    .execute()
                    .getResult();
            return result.isOk();
        } catch (Exception e) {

            System.out.println("Error ocurred: " + e);
            return false;
        }
    }

    public static void main(String[] args) {
        CloudantOperation operation = new CloudantOperation();
        Message message = new Message("Hello Java", "James Gosling");

        System.out.println(operation.getMessageById("53b77aee35d1a3a2a5cd5382ee15fa6d"));
    }
}
