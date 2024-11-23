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
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;

@Path("/messages") // top level path annotation
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class MessageResource {

    private MessageService service = new MessageService();

    @GET
    public List<Message> getAllMessages(@QueryParam("year") int year ,
                                        @QueryParam("start")int start ,
                                        @QueryParam("size")int size) {
        if(year > 0 ){
            return service.getMessageForYear(year);
        }
        if(start > 0 && size > 0){
            System.out.println("Insise size");
            return service.getAllMessagePaginated(start, size);
        }
        return service.getAllMessages();
    }



    @GET
    @Path("/{messageId}") // method level path annotation
    public Message getMessage(@PathParam("messageId") String _id) {
        return service.getMessageById(_id);
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
