package am.developer.oauth2.exception;

import am.developer.oauth2.payload.response.ExceptionResponse;
import lombok.Getter;

import java.util.Set;

@Getter
public class ValidationException extends RuntimeException {

    private final Set<ExceptionResponse> messages;

    public ValidationException(ExceptionResponse message) {
        this.messages = Set.of(message);
    }
}
