package com.martinodutto.tpt.services;

import com.martinodutto.tpt.exceptions.DuplicateKeyException;
import com.martinodutto.tpt.exceptions.UnregisteredRoleException;
import com.martinodutto.tpt.security.TptGrantedAuthority;
import com.martinodutto.tpt.security.TptUser;
import org.springframework.context.annotation.Profile;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Nonnull;
import java.util.Collections;

@Service("userService")
@Profile("test")
public class MockUserService implements UserService {

    private TptUser mockUser = new TptUser("mockUser", "mockPassword", Collections.singletonList(new TptGrantedAuthority("ADMIN")));

    public MockUserService() {
        mockUser.setUserId(0);
    }

    @Override
    public void addUser(@Nonnull TptUser user) throws DuplicateKeyException, UnregisteredRoleException {
    }

    @Override
    public int getCurrentUserId() {
        return getCurrentUser().getUserId();
    }

    @Override
    public TptUser getCurrentUser() {
        return mockUser;
    }

    @Override
    public TptUser getUserBy(int userId) {
        return userId == 0 ? mockUser : null;
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        return mockUser.getUsername().equals(s) ? mockUser : null;
    }
}
