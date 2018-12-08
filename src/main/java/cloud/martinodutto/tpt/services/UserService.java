package cloud.martinodutto.tpt.services;

import cloud.martinodutto.tpt.exceptions.BadCurrentUserPasswordException;
import cloud.martinodutto.tpt.exceptions.DuplicateKeyException;
import cloud.martinodutto.tpt.exceptions.UnregisteredRoleException;
import cloud.martinodutto.tpt.security.TptUser;
import org.springframework.security.core.userdetails.UserDetailsService;

import javax.annotation.Nonnull;

public interface UserService extends UserDetailsService {

    void addUser(@Nonnull TptUser user) throws DuplicateKeyException, UnregisteredRoleException;

    void changePassword(String oldPassword, String newPassword) throws BadCurrentUserPasswordException;

    int getCurrentUserId();

    TptUser getCurrentUser();

    TptUser getUserBy(int userId);
}
