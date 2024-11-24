package com.rayan.messenger.rest.service;

import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

import com.rayan.messenger.rest.model.Comment;
import com.rayan.messenger.rest.model.Message;

import jakarta.ws.rs.NotFoundException;

public class CommentService {
    private MessageService service = new MessageService();

    public void addCommentToMessage(String messageId, Comment comment) {
        Message message = service.getMessageById(messageId);
        if (message != null) {
            comment.set_id(UUID.randomUUID().toString());
            comment.setCreated(new Date());
            message.getComments().add(comment);
            service.updateMessage(message, messageId);
        } else {
            throw new NotFoundException("Message not found!");
        }
    }

    public List<Comment> getMessageComments(String messageId) {
        Message message = service.getMessageById(messageId);
        System.out.println("Message Comments: " + message.getComments());
        return message.getComments();
    }

    public void updateComment(String messageId, String commentId, Comment updatedComment) {

        Message message = service.getMessageById(messageId);
        if (message != null) {
            for (Comment comment : message.getComments()) {
                if (commentId.equals(comment.get_id())) {

                    comment.setComment(updatedComment.getComment());
                    comment.setAuthor(updatedComment.getAuthor());
                    comment.setCreated(new Date());
                    comment.set_rev(comment.get_rev() + 1); // // Simulated revision number.
                }
            }
            service.updateMessage(message, messageId);
        } else {
            throw new NotFoundException("Message not found!");
        }
    }

    public void deleteComment(String messageId, String commentId) {
        Message message = service.getMessageById(messageId);

        if (message == null) {
            throw new NotFoundException("Message not found!");
        }

        Iterator<Comment> iterator = message.getComments().iterator();
        boolean commentFound = false;

        while (iterator.hasNext()) {
            Comment comment = iterator.next();
            if (comment.get_id() != null && comment.get_id().equals(commentId)) {
                iterator.remove(); 
                commentFound = true;
                break;
            }
        }

        if (!commentFound) {
            throw new NotFoundException("Comment not found!");
        }
        service.updateMessage(message, messageId);
    }
}