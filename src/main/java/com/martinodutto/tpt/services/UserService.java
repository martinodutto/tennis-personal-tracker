package com.martinodutto.tpt.services;

import com.martinodutto.tpt.security.TptUser;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {

    int getUserId();

    TptUser getUser();
}
