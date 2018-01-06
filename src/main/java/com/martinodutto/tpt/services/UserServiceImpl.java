package com.martinodutto.tpt.services;

import com.martinodutto.tpt.database.entities.User;
import org.springframework.context.annotation.Profile;
import org.springframework.security.authentication.AccountStatusUserDetailsChecker;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service("userService")
@Profile({"development", "local", "production"})
public class UserServiceImpl implements UserService {

    private final AuthenticationService authenticationService;

    public UserServiceImpl(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        final Optional<User> user = Optional.ofNullable(authenticationService.getUserBy(username));
        final AccountStatusUserDetailsChecker detailsChecker = new AccountStatusUserDetailsChecker();
        user.ifPresent(detailsChecker::check);
        return user.orElseThrow(() -> new UsernameNotFoundException("User not found"));
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
