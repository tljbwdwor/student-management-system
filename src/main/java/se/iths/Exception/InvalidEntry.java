package se.iths.Exception;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

public class InvalidEntry extends WebApplicationException {

    public InvalidEntry(String errorMessage) {
        super(Response.status(Response
                .Status.NOT_ACCEPTABLE)
                .entity(errorMessage)
                .type(MediaType.APPLICATION_JSON_TYPE)
                .build());
    }

}