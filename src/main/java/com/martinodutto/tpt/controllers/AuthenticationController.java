package com.martinodutto.tpt.controllers;

import com.martinodutto.tpt.controllers.entities.AuthenticationResponse;
import com.martinodutto.tpt.controllers.entities.UserForm;
import com.martinodutto.tpt.database.entities.User;
import com.martinodutto.tpt.exceptions.DuplicateKeyException;
import com.martinodutto.tpt.exceptions.EmptyInputException;
import com.martinodutto.tpt.exceptions.InvalidInputException;
import com.martinodutto.tpt.security.TokenHandler;
import com.martinodutto.tpt.services.AuthenticationService;
import com.martinodutto.tpt.services.CurrentUserHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Nonnull;
import javax.validation.Valid;

@RestController
public class AuthenticationController {

    private static final Logger LOGGER = LoggerFactory.getLogger(AuthenticationController.class);

    private final AuthenticationManager authenticationManager;
    private final AuthenticationService authenticationService;
    private final CurrentUserHelper currentUserHelper;
    private final TokenHandler tokenHandler;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public AuthenticationController(AuthenticationManager authenticationManager, AuthenticationService authenticationService, CurrentUserHelper currentUserHelper, TokenHandler tokenHandler, PasswordEncoder passwordEncoder) {
        this.authenticationManager = authenticationManager;
        this.authenticationService = authenticationService;
        this.currentUserHelper = currentUserHelper;
        this.tokenHandler = tokenHandler;
        this.passwordEncoder = passwordEncoder;
    }

    @RequestMapping(method = RequestMethod.POST, value = "/authentication/register")
    public void register(@Valid @RequestBody UserForm form, BindingResult bindingResult) throws InvalidInputException, EmptyInputException, DuplicateKeyException {

        LOGGER.info("Registering a new user");

        if (bindingResult.hasErrors()) {
            throw new InvalidInputException(bindingResult.getAllErrors());
        }

        if (form != null) {
            User user = new User(form);
            hashPassword(user);
            authenticationService.addUser(user);
        } else {
            throw new EmptyInputException();
        }

        LOGGER.info("Successfully registered a new user");
    }

    @RequestMapping(method = RequestMethod.POST, value = "/authentication/login")
    public AuthenticationResponse login(@Valid @RequestBody UserForm form, BindingResult bindingResult) throws InvalidInputException, EmptyInputException {

        LOGGER.info("Logging in");

        if (bindingResult.hasErrors()) {
            throw new InvalidInputException(bindingResult.getAllErrors());
        }

        if (form != null) {
            User user = new User(form);
            final UsernamePasswordAuthenticationToken loginToken = user.toAuthenticationToken();
            final Authentication authentication = authenticationManager.authenticate(loginToken);

            // upon successful login, we may proceed to create the token
            SecurityContextHolder.getContext().setAuthentication(authentication); // this makes the username available elsewhere

            return currentUserHelper.getUser().map(u -> {
                final String token = tokenHandler.createTokenForUser(u);
                return new AuthenticationResponse(token);
            }).orElseThrow(RuntimeException::new);
        } else {
            throw new EmptyInputException();
        }
    }

    private void hashPassword(@Nonnull User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
    }
}
