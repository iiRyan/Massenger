package com.rayan.messenger.rest.database.cloudant;

import com.ibm.cloud.cloudant.v1.Cloudant;
import com.ibm.cloud.sdk.core.security.IamAuthenticator;
import com.ibm.cloud.sdk.core.service.exception.ServiceResponseException;

public enum CloudantClient {
    INSTANCE;

    private static final String API_KEY_ENV = "APIKEY";
    private static final String CLOUDANT_URL = "CLOUDANT_URL";

    public Cloudant getCloudantClient() {
        String apikey = System.getenv(API_KEY_ENV);
        String cloudantURL = System.getenv(CLOUDANT_URL);

        try {
            IamAuthenticator authenticator = new IamAuthenticator.Builder()
                    .apikey(apikey)
                    .build();
            // Create the service instance.
            Cloudant client = new Cloudant(Cloudant.DEFAULT_SERVICE_NAME, authenticator);
            client.setServiceUrl(cloudantURL);
            return client;
        } catch (ServiceResponseException e) {
            throw e;
        }
    }
}
