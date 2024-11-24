package com.rayan.messenger.rest.exception;

import com.rayan.messenger.rest.model.ErrorMessage;

import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

@Provider // Register this class in JAX-RS
public class DataNotFoundMapper implements ExceptionMapper<DataNotFoundException>{

    @Override
    public Response toResponse(DataNotFoundException exception) {
        ErrorMessage notFound = new ErrorMessage(exception.getMessage(),404);
        return Response.status(Status.NOT_FOUND)
        .entity(notFound)
        .build();
    }
    
}
