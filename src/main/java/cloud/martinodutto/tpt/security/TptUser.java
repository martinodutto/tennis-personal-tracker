package cloud.martinodutto.tpt.security;

import cloud.martinodutto.tpt.controllers.entities.UserForm;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Collection;

public class TptUser extends User {

    private int userId;

    /**
     * Constructor from a user frontend form.
     *
     * <p>Should be used only when receiving data from the frontend, because it doesn't set any authority or other condition.</p>
     *
     * @param form Form submitted by the user while signing-in or signing-up.
     */
    public TptUser(@Nonnull UserForm form) {
        this(new String(Base64.getDecoder().decode(form.get_username())), new String(Base64.getDecoder().decode(form.get_password())), new ArrayList<>());
    }

    public TptUser(String username, String password, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, authorities);
    }

    public TptUser(String username, String password, boolean enabled, boolean accountNonExpired, boolean credentialsNonExpired, boolean accountNonLocked, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
    }

    public UsernamePasswordAuthenticationToken toAuthenticationToken() {
        return new UsernamePasswordAuthenticationToken(getUsername(), getPassword());
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
