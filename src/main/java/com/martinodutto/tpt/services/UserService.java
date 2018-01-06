package com.martinodutto.tpt.services;

import com.martinodutto.tpt.database.entities.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {

    int getUserId();

    User getUser();
}
