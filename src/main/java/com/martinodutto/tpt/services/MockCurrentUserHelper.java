package com.martinodutto.tpt.services;

import com.martinodutto.tpt.database.entities.User;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Service
@Profile("test")
public class MockCurrentUserHelper implements CurrentUserHelper {

    private User mockUser = new User();

    public MockCurrentUserHelper() {
        mockUser.setUserId(0);
        mockUser.setUsername("mockUser");
        mockUser.setPassword("mockPassword");
        mockUser.setEnabled(true);
    }

    @Override
    public int getUserId() {
        return mockUser.getUserId();
    }

    @Override
    public User getUser() {
        return mockUser;
    }
}
