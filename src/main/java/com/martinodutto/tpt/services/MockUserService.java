package com.martinodutto.tpt.services;

import com.martinodutto.tpt.database.entities.User;
import org.springframework.context.annotation.Profile;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service("userService")
@Profile("test")
public class MockUserService implements UserService {

    private User mockUser = new User();

    public MockUserService() {
        mockUser.setUserId(0);
        mockUser.setUsername("mockUser");
        mockUser.setPassword("mockPassword");
        mockUser.setEnabled(true);
    }

    @Override
    public int getUserId() {
        return getUser().getUserId();
    }

    @Override
    public User getUser() {
        return mockUser;
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        return mockUser.getUsername().equals(s) ? mockUser : null;
    }
}
