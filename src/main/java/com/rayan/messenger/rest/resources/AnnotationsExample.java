package com.rayan.messenger.rest.resources;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.CookieParam;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.HeaderParam;
import jakarta.ws.rs.MatrixParam;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.HttpHeaders;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.UriInfo;

@Path("/injectdemo")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class AnnotationsExample {

    @GET
    @Path("annotations")
    public String getParamsUsingAnnotations(@MatrixParam("param") String matrixParam, // matrixParam example:
                                                                                      // /injectdemo/annotations;param=value
            @HeaderParam("authSessionID") String header,
            @CookieParam("name") String name) {

        return "Matrix Param: " + matrixParam + " HeaderParam: " + header + " CookieParam: " + name;

    }

    @GET
    @Path("context")
    public String getParamUsingContext(@Context UriInfo uriInfo, @Context HttpHeaders headers) {
        String path = uriInfo.getAbsolutePath().toString();
        String cookies = headers.getCookies().toString();
        return "Path: " + path + " Cookies: " + cookies;
    }
}