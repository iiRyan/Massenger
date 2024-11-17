package com.rayan.messenger.rest.resources;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/messages")
public class MessageResource {
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String hello(){
        return "Hello Rayan!";
    }
}
