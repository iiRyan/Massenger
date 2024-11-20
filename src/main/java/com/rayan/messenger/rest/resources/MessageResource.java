package com.rayan.messenger.rest.resources;

import java.util.List;

import com.rayan.messenger.rest.model.Message;
import com.rayan.messenger.rest.service.MessageService;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/messages") // top level path annotation
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class MessageResource {

    private MessageService service = new MessageService();

    @GET
    
    public List<Message> getAllMessages() {

        return service.getAllMessages();
    }

    @GET
    @Path("/{messageId}") // method level path annotation
    public Message getMessage(@PathParam("messageId") String _id) {
        System.out.println("_id: "+_id);
        return service.getMessage(_id);
    }

    @POST
    public Message insertMessage(Message theMessage) {
        return service.insertMessage(theMessage);
    }

    @PUT
    @Path("/{messageId}")
    public Message updateMessage(@PathParam("messageId") String _id, Message message){
        message.set_id(_id);
        return service.updateMessage(message,_id);
    }

    @DELETE
    @Path("/{messageId}")
    public void deleteMessage(@PathParam("messageId") String _id){
        service.deleteMessage(_id);
    }
}
