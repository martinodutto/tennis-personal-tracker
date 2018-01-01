package com.martinodutto.tpt.services;

import com.martinodutto.tpt.database.entities.User;

import java.util.Optional;

public interface CurrentUserHelper {

    int getUserId();

    Optional<User> getUser();
}
