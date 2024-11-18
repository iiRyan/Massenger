package com.rayan.messenger.rest.database.cloudant;

import java.util.ArrayList;
import java.util.List;

import com.ibm.cloud.cloudant.v1.model.Document;
import com.ibm.cloud.cloudant.v1.model.DocumentResult;
import com.ibm.cloud.cloudant.v1.model.PostDocumentOptions;
import com.rayan.messenger.rest.model.Message;

public class CloudantOperation {

    private CloudantDBManager dbManager = new CloudantDBManager();

    public CloudantOperation() {
    }

    // Insert Bucket's objects into CloudantDB.
    public boolean save(Message theMessage) {


        DocumentResult result = null;

        try {
            // Manually map keys to values , I used this approach bcoz the structure of Message is fixed
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
        Message message =new Message("Hello Java","James Gosling");
        operation.save(message);
    }
}
