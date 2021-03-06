package cloud.martinodutto.tpt.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "No data found on database")
public class NoDataFoundOnDatabaseException extends Exception {

    public NoDataFoundOnDatabaseException() {
    }
}
