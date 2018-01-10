package com.martinodutto.tpt.services;

import com.martinodutto.tpt.database.entities.Role;
import com.martinodutto.tpt.database.entities.User;
import com.martinodutto.tpt.database.mappers.RolesMapper;
import com.martinodutto.tpt.security.TptUser;
import org.springframework.context.annotation.Profile;
import org.springframework.security.authentication.AccountStatusUserDetailsChecker;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Nonnull;
import java.util.Optional;

@Service("userService")
@Profile({"development", "local", "production"})
public class UserServiceImpl implements UserService {

    private final AuthenticationService authenticationService;
    private final RolesMapper rolesMapper;

    public UserServiceImpl(AuthenticationService authenticationService, RolesMapper rolesMapper) {
        this.authenticationService = authenticationService;
        this.rolesMapper = rolesMapper;
    }

    @Override
    public @Nonnull UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = Optional.ofNullable(authenticationService.getUserBy(username)).orElseThrow(() -> new UsernameNotFoundException("User not found"));
        Role role = rolesMapper.selectByPk(user.getRoleId());
        UserDetails userDetails = authenticationService.newTptUserFrom(user, role);
        final AccountStatusUserDetailsChecker detailsChecker = new AccountStatusUserDetailsChecker();
        detailsChecker.check(userDetails);

        return userDetails;
    }

    @Override
    public int getUserId() {
        return getUser().getUserId();
    }

    @Override
    public @Nonnull TptUser getUser() {
        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = Optional.ofNullable(authenticationService.getUserBy(authentication.getName())).orElseThrow(() -> new RuntimeException("User not found. This is unexpected!"));
        Role role = rolesMapper.selectByPk(user.getRoleId());

        return authenticationService.newTptUserFrom(user, role);
    }
}
