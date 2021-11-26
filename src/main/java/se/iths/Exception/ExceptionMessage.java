package se.iths.Exception;

import javax.json.bind.annotation.JsonbProperty;

public class ExceptionMessage {

    private final String response;

    public ExceptionMessage(String response) {
        this.response = response;
    }

    @JsonbProperty("response")
    public String getResponse() {
        return response;
    }
}
