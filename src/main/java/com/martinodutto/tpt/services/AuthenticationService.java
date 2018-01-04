package com.martinodutto.tpt.services;

import com.martinodutto.tpt.database.entities.User;
import com.martinodutto.tpt.database.mappers.UsersMapper;
import com.martinodutto.tpt.exceptions.DuplicateKeyException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

@Service
public class AuthenticationService {

    private static final Logger LOGGER = LoggerFactory.getLogger(AuthenticationService.class);

    private final UsersMapper usersMapper;

    @Autowired
    public AuthenticationService(UsersMapper usersMapper) {
        this.usersMapper = usersMapper;
    }

    @Transactional
    public void addUser(@Nonnull User user) throws DuplicateKeyException {
        int insertOutcome;
        try {
            insertOutcome = usersMapper.insert(user);
        } catch (org.springframework.dao.DuplicateKeyException d) {
            // makes this a checked exception, with an HTTP response code
            throw new DuplicateKeyException(d);
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
}
