package am.developer.oauth2.payload.response;

import lombok.Getter;

import java.util.Set;

@Getter
public class ExceptionResponse {

    private final Set<String> errorMessages;

    public ExceptionResponse(String message) {
        this.errorMessages = Set.of(message);
    }

    public ExceptionResponse(Set<String> messages) {
        this.errorMessages = messages;
    }

}
