package cloud.martinodutto.tpt.controllers;

import cloud.martinodutto.tpt.controllers.entities.AuthenticationResponse;
import cloud.martinodutto.tpt.controllers.entities.ChangePasswordForm;
import cloud.martinodutto.tpt.controllers.entities.UserForm;
import cloud.martinodutto.tpt.exceptions.*;
import cloud.martinodutto.tpt.security.TokenHandler;
import cloud.martinodutto.tpt.security.TptUser;
import cloud.martinodutto.tpt.services.UserService;
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
import java.util.Base64;

@RestController
public class AuthenticationController {

    private static final Logger LOGGER = LoggerFactory.getLogger(AuthenticationController.class);

    private final AuthenticationManager authenticationManager;
    private final TokenHandler tokenHandler;
    private final PasswordEncoder passwordEncoder;
    private final UserService userService;

    @Autowired
    public AuthenticationController(AuthenticationManager authenticationManager,
                                    TokenHandler tokenHandler,
                                    PasswordEncoder passwordEncoder,
                                    UserService userService) {
        this.authenticationManager = authenticationManager;
        this.tokenHandler = tokenHandler;
        this.passwordEncoder = passwordEncoder;
        this.userService = userService;
    }

    @RequestMapping(method = RequestMethod.POST, value = "/authentication/unfiltered/register")
    public void register(@Valid @RequestBody UserForm form, BindingResult bindingResult)
            throws InvalidInputException, EmptyInputException, DuplicateKeyException, UnregisteredRoleException {

        LOGGER.info("Registering a new user");

        if (bindingResult.hasErrors()) {
            throw new InvalidInputException(bindingResult.getAllErrors());
        }

        if (form != null) {
            TptUser user = new TptUser(form);
            userService.addUser(getHashedUser(user));
        } else {
            throw new EmptyInputException();
        }

        LOGGER.info("Successfully registered a new user");
    }

    @RequestMapping(method = RequestMethod.POST, value = "/authentication/unfiltered/login")
    public AuthenticationResponse login(@Valid @RequestBody UserForm form, BindingResult bindingResult)
            throws InvalidInputException, EmptyInputException {

        LOGGER.info("Logging in");

        if (bindingResult.hasErrors()) {
            throw new InvalidInputException(bindingResult.getAllErrors());
        }

        if (form != null) {
            TptUser user = new TptUser(form);
            final UsernamePasswordAuthenticationToken loginToken = user.toAuthenticationToken();
            final Authentication authentication = authenticationManager.authenticate(loginToken);

            // upon successful login, we may proceed to create the token
            SecurityContextHolder.getContext().setAuthentication(authentication); // this makes the username available elsewhere

            TptUser u = userService.getCurrentUser();
            return new AuthenticationResponse(tokenHandler.createTokenForUser(u));
        } else {
            throw new EmptyInputException();
        }
    }

    @RequestMapping(method = RequestMethod.POST, value = "/authentication/changePassword")
    public void changePassword(@Valid @RequestBody ChangePasswordForm form, BindingResult bindingResult) throws InvalidInputException, EmptyInputException, BadCurrentUserPasswordException {

        LOGGER.info("Changing password");

        if (bindingResult.hasErrors()) {
            throw new InvalidInputException(bindingResult.getAllErrors());
        }

        if (form != null) {
            userService.changePassword(new String(Base64.getDecoder().decode(form.get_oldPassword())), new String(Base64.getDecoder().decode(form.get_newPassword())));
        } else {
            throw new EmptyInputException();
        }
    }

    private TptUser getHashedUser(@Nonnull TptUser user) {
        return new TptUser(user.getUsername(), passwordEncoder.encode(user.getPassword()), user.getAuthorities());
    }
}
