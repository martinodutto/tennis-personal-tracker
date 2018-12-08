package cloud.martinodutto.tpt.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Empty input")
public class EmptyInputException extends Exception {

    public EmptyInputException() {
    }
}
