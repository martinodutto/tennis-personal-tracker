package com.martinodutto.tpt.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT, reason = "Duplicated key")
public class DuplicateKeyException extends Exception {

    public DuplicateKeyException() {
    }

    public DuplicateKeyException(Throwable cause) {
        super(cause);
    }
}
