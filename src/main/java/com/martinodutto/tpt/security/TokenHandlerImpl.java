package com.martinodutto.tpt.security;

import com.martinodutto.tpt.database.entities.User;
import com.martinodutto.tpt.services.AuthenticationService;
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
    private final AuthenticationService authenticationService;

    @Autowired
    public TokenHandlerImpl(@Value("${app.jwt.secret}") String secret, AuthenticationService authenticationService) {
        this.secret = secret;
        this.authenticationService = authenticationService;
    }

    @Override
    public Optional<UserDetails> parseUserFromToken(String token) {
        final String subject = Jwts.parser()
                .setSigningKey(secret)
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
        final User user = authenticationService.getUserBy(Integer.valueOf(subject));

        return Optional.ofNullable(user);
    }

    @Override
    public String createTokenForUser(User user) {
//        long t = System.currentTimeMillis();
        return Jwts.builder()
                .setSubject(String.valueOf(user.getUserId())) // expiration + session
                .signWith(SignatureAlgorithm.HS512, secret)
                .setExpiration(Date.from(LocalDate.now().plusWeeks(1).atStartOfDay().atZone(ZoneId.systemDefault()).toInstant()))
//                .setExpiration(new Date(t + 60000))
                .compact();
    }
}
