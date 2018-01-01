package com.martinodutto.tpt.controllers;

import com.martinodutto.tpt.controllers.entities.UserForm;
import com.martinodutto.tpt.database.entities.User;
import com.martinodutto.tpt.exceptions.EmptyInputException;
import com.martinodutto.tpt.exceptions.InvalidInputException;
import com.martinodutto.tpt.services.AuthenticationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class AuthenticationController {

    private static final Logger LOGGER = LoggerFactory.getLogger(AuthenticationController.class);

    private final AuthenticationService authenticationService;

    @Autowired
    public AuthenticationController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @RequestMapping(method = RequestMethod.POST, value = "/authentication/register")
    public void register(@Valid @RequestBody UserForm form, BindingResult bindingResult) throws InvalidInputException, EmptyInputException {

        LOGGER.info("Registering a new user");

        if (bindingResult.hasErrors()) {
            throw new InvalidInputException(bindingResult.getAllErrors());
        }

        if (form != null) {
            User user = new User(form);
            authenticationService.addUser(user);
        } else {
            throw new EmptyInputException();
        }

        LOGGER.info("Successfully registered a new user");
    }
}
