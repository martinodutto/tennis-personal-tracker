package com.martinodutto.tpt.security;

import com.martinodutto.tpt.database.entities.Role;
import com.martinodutto.tpt.database.mappers.RolesMapper;
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
    private final RolesMapper rolesMapper;

    @Autowired
    public TokenHandlerImpl(@Value("${app.jwt.secret}") String secret, AuthenticationService authenticationService, RolesMapper rolesMapper) {
        this.secret = secret;
        this.authenticationService = authenticationService;
        this.rolesMapper = rolesMapper;
    }

    @Override
    public Optional<UserDetails> parseUserFromToken(String token) {
        final String subject = Jwts.parser()
                .setSigningKey(secret)
                .parseClaimsJws(token)
                .getBody()
                .getSubject();

        TptUser tptUser = Optional.ofNullable(authenticationService.getUserBy(Integer.valueOf(subject))).map(u -> {
            final Role role = Optional.ofNullable(u.getRoleId()).map(rolesMapper::selectByPk).orElse(null);
            return authenticationService.newTptUserFrom(u, role);
        }).orElse(null);

        return Optional.ofNullable(tptUser);
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
