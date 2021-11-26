package se.iths.Exception;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

public class NotModified extends WebApplicationException {

    public NotModified(String errorMessage) {
        super(Response.status(Response
                .Status.NOT_MODIFIED)
                .entity(new ResponseMessage(errorMessage))
                .type(MediaType.APPLICATION_JSON)
                .build());
    }

}
