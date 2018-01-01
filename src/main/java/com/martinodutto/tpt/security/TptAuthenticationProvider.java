package com.martinodutto.tpt.security;

import com.martinodutto.tpt.database.entities.User;
import com.martinodutto.tpt.services.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Service;

@Service
public class TptAuthenticationProvider implements AuthenticationProvider {

    private final AuthenticationService authenticationService;

    @Autowired
    public TptAuthenticationProvider(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = String.valueOf(authentication.getPrincipal());
        String password = String.valueOf(authentication.getCredentials());

        final User user = authenticationService.getUserBy(username);
        if (user == null) {
            throw new BadCredentialsException("Username not valid");
        } else if (!user.getPassword().equals(password)) { // user.getPassword() should never return null (because of the db constraint)
            throw new BadCredentialsException("Password not valid");
        } else if (!user.isEnabled()) {
            throw new DisabledException("Disabled user");
        }

        return new UsernamePasswordAuthenticationToken(username, password);
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return (UsernamePasswordAuthenticationToken.class.isAssignableFrom(aClass));
    }
}
