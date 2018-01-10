package com.martinodutto.tpt.services;

import com.martinodutto.tpt.security.TptGrantedAuthority;
import com.martinodutto.tpt.security.TptUser;
import org.springframework.context.annotation.Profile;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service("userService")
@Profile("test")
public class MockUserService implements UserService {

    private TptUser mockUser = new TptUser("mockUser", "mockPassword", Collections.singletonList(new TptGrantedAuthority("ADMIN")));

    public MockUserService() {
        mockUser.setUserId(0);
    }

    @Override
    public int getUserId() {
        return getUser().getUserId();
    }

    @Override
    public TptUser getUser() {
        return mockUser;
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        return mockUser.getUsername().equals(s) ? mockUser : null;
    }
}
