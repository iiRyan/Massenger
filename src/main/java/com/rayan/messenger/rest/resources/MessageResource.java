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
    public Message getMessage(@PathParam("messageId") String _id, @Context UriInfo uriInfo) {
        Message message = service.getMessageById(_id);
        getUriForSelf(_id, uriInfo, message);
        getUriForProfile(_id, uriInfo, message);
        getUriForComments(_id, uriInfo, message);

        return message;
    }

    @POST
    public Response insertMessage(Message theMessage, @Context UriInfo uriInfo) {
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
    public CommentResource getCommentResource() {
        return new CommentResource();
    }

    private void getUriForSelf(String _id, UriInfo uriInfo, Message message) {
        String uri = uriInfo.getBaseUriBuilder() // http://localhost:9080/messenger/api/
                .path(MessageResource.class) // /messages
                .path(_id) // /{messageId}
                .build()
                .toString();
        message.addLinks(uri, "self");
    }

    private void getUriForProfile(String _id, UriInfo uriInfo, Message message) {
        String uri = uriInfo.getBaseUriBuilder() // http://localhost:9080/messenger/api/
                .path(ProfileResource.class) // /profiles
                .path(message.getAuthor()) // /{profileName}
                .build()
                .toString();
        message.addLinks(uri, "profile");
    }

    private void getUriForComments(String _id, UriInfo uriInfo, Message message) {
        String uri = uriInfo.getBaseUriBuilder() // http://localhost:9080/messenger/api/
                .path(MessageResource.class) // /messages
                .path(MessageResource.class,"getCommentResource") // /{messageId}/comments
                .path(CommentResource.class) // comment class level path it will be "/" doesn't hurt to add it here :).
                .resolveTemplate("messageId",message.get_id()) // to pull the @pathParam of getCommentResource method.
                .build()
                .toString();
        message.addLinks(uri, "comments");
    }
}
