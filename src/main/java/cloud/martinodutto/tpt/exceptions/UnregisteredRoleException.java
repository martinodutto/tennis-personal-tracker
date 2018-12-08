package cloud.martinodutto.tpt.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Exception thrown when the role id of the user that's being inserted does not exist,
 * i.e. it's not registered on the Roles table.
 */
@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR, reason = "Unregistered role")
public class UnregisteredRoleException extends Exception {
}
