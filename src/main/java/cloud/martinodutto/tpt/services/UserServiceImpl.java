package cloud.martinodutto.tpt.services;

import cloud.martinodutto.tpt.database.entities.Role;
import cloud.martinodutto.tpt.database.entities.User;
import cloud.martinodutto.tpt.database.mappers.RolesMapper;
import cloud.martinodutto.tpt.database.mappers.UsersMapper;
import cloud.martinodutto.tpt.exceptions.BadCurrentUserPasswordException;
import cloud.martinodutto.tpt.exceptions.DuplicateKeyException;
import cloud.martinodutto.tpt.exceptions.UnregisteredRoleException;
import cloud.martinodutto.tpt.security.TptGrantedAuthority;
import cloud.martinodutto.tpt.security.TptUser;
import org.h2.jdbc.JdbcSQLException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.authentication.AccountStatusUserDetailsChecker;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.*;

@Service("userService")
public class UserServiceImpl implements UserService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);

    private final UsersMapper usersMapper;
    private final RolesMapper rolesMapper;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UsersMapper usersMapper, RolesMapper rolesMapper, PasswordEncoder passwordEncoder) {
        this.usersMapper = usersMapper;
        this.rolesMapper = rolesMapper;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    @Override
    public @Nonnull UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        final User user = Optional.ofNullable(usersMapper.selectByUsername(username)).orElseThrow(() -> new UsernameNotFoundException("User not found"));
        final Role role = Optional.ofNullable(user.getRoleId()).map(rolesMapper::selectByPk).orElse(null);
        UserDetails userDetails = newTptUserFrom(user, role);
        final AccountStatusUserDetailsChecker detailsChecker = new AccountStatusUserDetailsChecker();
        detailsChecker.check(userDetails);

        return userDetails;
    }

    @Transactional
    @Override
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
    @Override
    public void changePassword(String oldPassword, String newPassword) throws BadCurrentUserPasswordException {
        final TptUser user = getCurrentUser();
        if (!passwordEncoder.matches(oldPassword, user.getPassword())) {
            throw new BadCurrentUserPasswordException();
        }
        final User userToUpdate = usersMapper.selectByPk(user.getUserId());
        Objects.requireNonNull(userToUpdate); // at this point we may expect it to be not null
        userToUpdate.setPassword(passwordEncoder.encode(newPassword));
        usersMapper.update(userToUpdate);
    }

    @Transactional
    @Override
    public int getCurrentUserId() {
        return getCurrentUser().getUserId();
    }

    @Transactional
    @Override
    public @Nonnull TptUser getCurrentUser() {
        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Objects.requireNonNull(authentication, "Unexpected null authentication object");
        final User user = Optional.ofNullable(usersMapper.selectByUsername(authentication.getName())).orElseThrow(() -> new
                RuntimeException("User not found. This is unexpected!"));
        final Role role = Optional.ofNullable(user.getRoleId()).map(rolesMapper::selectByPk).orElse(null);

        return newTptUserFrom(user, role);
    }

    @Transactional
    @Override
    public @Nullable TptUser getUserBy(int userId) {
        return Optional.ofNullable(usersMapper.selectByPk(userId)).map(u -> {
            final Role role = Optional.ofNullable(u.getRoleId()).map(rolesMapper::selectByPk).orElse(null);
            return newTptUserFrom(u, role);
        }).orElse(null);
    }

    @Nonnull
    private TptUser newTptUserFrom(@Nonnull User user, @Nullable Role role) {
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
