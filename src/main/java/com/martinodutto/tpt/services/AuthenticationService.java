package com.martinodutto.tpt.services;

import com.martinodutto.tpt.database.entities.Role;
import com.martinodutto.tpt.database.entities.User;
import com.martinodutto.tpt.database.mappers.UsersMapper;
import com.martinodutto.tpt.exceptions.DuplicateKeyException;
import com.martinodutto.tpt.exceptions.UnregisteredRoleException;
import com.martinodutto.tpt.security.TptGrantedAuthority;
import com.martinodutto.tpt.security.TptUser;
import org.h2.jdbc.JdbcSQLException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class AuthenticationService {

    private static final Logger LOGGER = LoggerFactory.getLogger(AuthenticationService.class);

    private final UsersMapper usersMapper;

    @Autowired
    public AuthenticationService(UsersMapper usersMapper) {
        this.usersMapper = usersMapper;
    }

    @Transactional
    public void addUser(@Nonnull TptUser user) throws DuplicateKeyException, UnregisteredRoleException {
        int insertOutcome;
        try {
            insertOutcome = usersMapper.insert(new User(user));
        } catch (org.springframework.dao.DuplicateKeyException d) {
            // makes this a checked exception, with an HTTP response code
            throw new DuplicateKeyException(d);
        } catch (DataIntegrityViolationException dve) {
            if (dve.getCause() instanceof JdbcSQLException) {
                int errorCode = ((JdbcSQLException) dve.getCause()).getErrorCode();
                if (errorCode == 23506) {
                    throw new UnregisteredRoleException();
                }
            }
            throw dve;
        }
        LOGGER.info("Persisted {} new users", insertOutcome);
    }

    @Transactional
    @Nullable
    public User getUserBy(String username) {
        return usersMapper.selectByUsername(username);
    }

    @Transactional
    @Nullable
    public User getUserBy(int userId) {
        return usersMapper.selectByPk(userId);
    }

    public @Nonnull TptUser newTptUserFrom(@Nonnull User user, @Nullable Role role) {
        List<GrantedAuthority> grantedAuthorities;
        if (role != null) {
            grantedAuthorities = Collections.singletonList(new TptGrantedAuthority(role));
        } else {
            grantedAuthorities = new ArrayList<>(); // the user has no roles
        }

        TptUser u = new TptUser(
                user.getUsername(),
                user.getPassword(),
                user.isEnabled(),
                true,
                true,
                true,
                grantedAuthorities
        );
        u.setUserId(user.getUserId());

        return u;
    }
}
