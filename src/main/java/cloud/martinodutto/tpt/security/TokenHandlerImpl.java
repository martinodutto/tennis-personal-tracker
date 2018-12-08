package cloud.martinodutto.tpt.security;

import cloud.martinodutto.tpt.services.UserService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.Optional;

@Component
public class TokenHandlerImpl implements TokenHandler {

    private final String secret;
    private final UserService userService;

    @Autowired
    public TokenHandlerImpl(@Value("${app.jwt.secret}") String secret, UserService userService) {
        this.secret = secret;
        this.userService = userService;
    }

    @Override
    public Optional<UserDetails> parseUserFromToken(String token) {
        final String subject = Jwts.parser()
                .setSigningKey(secret)
                .parseClaimsJws(token)
                .getBody()
                .getSubject();

        return Optional.ofNullable(userService.getUserBy(Integer.valueOf(subject)));
    }

    @Override
    public String createTokenForUser(TptUser user) {
//        long t = System.currentTimeMillis();
        return Jwts.builder()
                .setSubject(String.valueOf(user.getUserId())) // expiration + session
                .signWith(SignatureAlgorithm.HS512, secret)
                .setExpiration(Date.from(LocalDate.now().plusWeeks(1).atStartOfDay().atZone(ZoneId.systemDefault()).toInstant()))
//                .setExpiration(new Date(t + 60000))
                .compact();
    }
}
