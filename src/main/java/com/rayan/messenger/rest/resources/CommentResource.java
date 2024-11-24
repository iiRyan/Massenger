package com.rayan.messenger.rest.resources;

import java.util.List;

import com.rayan.messenger.rest.model.Comment;
import com.rayan.messenger.rest.service.CommentService;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;

@Path("/") // Class level annotation is optional for sub resources.
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class CommentResource {

    CommentService service = new CommentService();


    @GET
    public List<Comment> getMessageComments(@PathParam("messageId") String _id){
        return service.getMessageComments(_id);
    }

    @POST
    public Response addComment(@PathParam("messageId") String _id,Comment comment) {
        service.addCommentToMessage(_id, comment);
        return Response.status(Status.CREATED).build();
    }

    @PUT
    @Path("/{commentId}")
    public void updateComment(@PathParam("messageId") String _id,@PathParam("commentId") String commentId,Comment comment){
      service.updateComment(_id, commentId, comment);
    }

    @DELETE
    @Path("/{commentId}")
    public Response deleteComment(@PathParam("messageId") String _id,@PathParam("commentId") String commentId){
        service.deleteComment(_id, commentId);
        return Response.status(Status.NO_CONTENT).build();
    }
}
