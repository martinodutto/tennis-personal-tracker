package com.martinodutto.tpt.services;

import com.martinodutto.tpt.database.entities.User;

public interface CurrentUserHelper {

    int getUserId();

    User getUser();
}
