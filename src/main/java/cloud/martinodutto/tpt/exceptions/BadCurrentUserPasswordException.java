package cloud.martinodutto.tpt.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.FORBIDDEN, reason = "Invalid current user credentials")
public class BadCurrentUserPasswordException extends Exception {

    public BadCurrentUserPasswordException() {
    }
}
