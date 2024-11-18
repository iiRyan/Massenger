package com.rayan.messenger.rest.resources;

import java.util.List;

import com.rayan.messenger.rest.model.Message;
import com.rayan.messenger.rest.service.MessageService;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/messages") // top level path annotation
public class MessageResource {

    private MessageService service = new MessageService();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Message> getAllMessages() {

        return service.getAllMessages();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{messageId}") // method level path annotation
    public Message getMessage(@PathParam("messageId") String _id) {
        return service.getMessage(_id);
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public String insertMessage(Message theMessage) {
        return service.insertMessage(theMessage);
    }
}
