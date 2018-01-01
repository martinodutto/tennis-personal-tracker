package com.martinodutto.tpt.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.annotation.Nonnull;
import java.util.List;

@ResponseStatus(value = HttpStatus.NOT_ACCEPTABLE, reason = "Invalid input")
public class InvalidInputException extends Exception {

    private List<ObjectError> validationErrors;

    public InvalidInputException() {
    }

    public InvalidInputException(@Nonnull List<ObjectError> validationErrors) {
        super("Invalid form with " + validationErrors.size() + " errors");
        this.validationErrors = validationErrors;
    }

    public List<ObjectError> getValidationErrors() {
        return validationErrors;
    }
}
