package cloud.martinodutto.tpt.security;

import cloud.martinodutto.tpt.database.entities.Role;
import org.springframework.security.core.GrantedAuthority;

import javax.annotation.Nonnull;

public class TptGrantedAuthority implements GrantedAuthority {

    private String roleName;

    public TptGrantedAuthority(@Nonnull Role role) {
        this.roleName = role.getRole();
    }

    public TptGrantedAuthority(String role) {
        this.roleName = role;
    }

    @Override
    public String getAuthority() {
        return roleName;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        } else {
            if (obj instanceof TptGrantedAuthority) {
                TptGrantedAuthority tptGrantedAuthority = (TptGrantedAuthority) obj;
                return ((tptGrantedAuthority.roleName != null) && tptGrantedAuthority.roleName.equals(this.roleName))
                        ||
                        (tptGrantedAuthority.roleName == null && this.roleName == null);
            } else {
                return false;
            }
        }
    }
}
