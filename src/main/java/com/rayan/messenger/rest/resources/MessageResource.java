package com.rayan.messenger.rest.resources;

import java.net.URI;
import java.util.List;

import com.rayan.messenger.rest.model.Message;
import com.rayan.messenger.rest.service.MessageService;

import jakarta.ws.rs.BeanParam;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriInfo;
import jakarta.ws.rs.core.Response.Status;

@Path("/messages") // top level path annotation
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class MessageResource {

    private MessageService service = new MessageService();

    @GET
    public List<Message> getAllMessages(@BeanParam MessageFilterBean bean) {
        if (bean.getYear() > 0) {
            return service.getMessageForYear(bean.getYear());
        }
        if (bean.getStart() > 0 && bean.getSize() > 0) {
            return service.getAllMessagePaginated(bean.getStart(), bean.getSize());
        }
        return service.getAllMessages();
    }

    @GET
    @Path("/{messageId}") // method level path annotation
    public Message getMessage(@PathParam("messageId") String _id) {
        return service.getMessageById(_id);
    }

    @POST
    public Response insertMessage(Message theMessage,@Context UriInfo uriInfo) {
        Message newMessage = service.insertMessage(theMessage);
        String newId = newMessage.get_id();
        URI uri = uriInfo.getAbsolutePathBuilder().path(newId).build();
        return Response.created(uri)
                .entity(newMessage)
                .build();

    }

    @PUT
    @Path("/{messageId}")
    public Response updateMessage(@PathParam("messageId") String _id, Message message) {
        message.set_id(_id);
        Message updatedMessage = service.updateMessage(message, _id);
        return Response.ok(updatedMessage).build();
    }

    @DELETE
    @Path("/{messageId}")
    public Response deleteMessage(@PathParam("messageId") String _id) {
        service.deleteMessage(_id);
        return Response.status(Status.NO_CONTENT).build();
    }

    // Sub Resource
    @Path("/{messageId}/comments")
    public CommentResource getComment() {
        return new CommentResource();
    }
}
