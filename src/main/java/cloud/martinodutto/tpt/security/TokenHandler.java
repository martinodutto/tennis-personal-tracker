package cloud.martinodutto.tpt.security;

import org.springframework.security.core.userdetails.UserDetails;

import java.util.Optional;

public interface TokenHandler {

    Optional<UserDetails> parseUserFromToken(String token);

    String createTokenForUser(TptUser user);
}
