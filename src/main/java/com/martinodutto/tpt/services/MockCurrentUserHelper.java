package com.martinodutto.tpt.services;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Service
@Profile({"test", "development", "local", "production"})
public class MockCurrentUserHelper implements CurrentUserHelper {

    @Override
    public int getUserId() {
        return 0;
    }
}
