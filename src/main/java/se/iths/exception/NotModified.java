package se.iths.exception;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

public class NotModified extends WebApplicationException {

    public NotModified(String errorMessage) {
        super(Response.status(Response
                .Status.NOT_MODIFIED)
                .entity(errorMessage)
                .type(MediaType.APPLICATION_JSON_TYPE)
                .build());
    }

}