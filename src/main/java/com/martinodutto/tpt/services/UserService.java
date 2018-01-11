package com.martinodutto.tpt.services;

import com.martinodutto.tpt.exceptions.DuplicateKeyException;
import com.martinodutto.tpt.exceptions.UnregisteredRoleException;
import com.martinodutto.tpt.security.TptUser;
import org.springframework.security.core.userdetails.UserDetailsService;

import javax.annotation.Nonnull;

public interface UserService extends UserDetailsService {

    void addUser(@Nonnull TptUser user) throws DuplicateKeyException, UnregisteredRoleException;

    int getCurrentUserId();

    TptUser getCurrentUser();

    TptUser getUserBy(int userId);
}
