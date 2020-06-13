package bankingsystem.backend.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNAUTHORIZED)
public class MyJwtTokenExpiredException extends RuntimeException {

    public MyJwtTokenExpiredException(String message) {
        super(message);
    }

    public MyJwtTokenExpiredException(String message, Throwable cause) {
        super(message, cause);
    }

}
