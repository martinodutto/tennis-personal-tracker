package com.martinodutto.tpt.security;

import com.martinodutto.tpt.database.entities.User;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Optional;

public interface TokenHandler {

    Optional<UserDetails> parseUserFromToken(String token);

    String createTokenForUser(User user);
}
