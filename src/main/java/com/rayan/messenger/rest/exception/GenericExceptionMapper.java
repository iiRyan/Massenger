package com.rayan.messenger.rest.exception;

import com.rayan.messenger.rest.model.ErrorMessage;

import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;
@Provider
public class GenericExceptionMapper implements ExceptionMapper<Throwable> {

    @Override
    public Response toResponse(Throwable exception) {
        ErrorMessage errorMessage = new ErrorMessage(exception.getMessage(), 500);
        return Response.status(Status.INTERNAL_SERVER_ERROR)
        .entity(errorMessage)
        .build();
    }
    
}
