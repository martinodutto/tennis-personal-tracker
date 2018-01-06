package com.martinodutto.tpt.services;

import com.martinodutto.tpt.database.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Profile({"development", "local", "production"})
public class CurrentUserHelperImpl implements CurrentUserHelper {

    private final AuthenticationService authenticationService;

    @Autowired
    public CurrentUserHelperImpl(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @Override
    public int getUserId() {
        return getUser().getUserId();
    }

    @Override
    public User getUser() {
        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return Optional.ofNullable(authenticationService.getUserBy(authentication.getName())).orElseThrow(() -> new RuntimeException("User not found. This is unexpected!"));
    }
}
