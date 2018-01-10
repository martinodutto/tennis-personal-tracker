package com.martinodutto.tpt.security;

import com.martinodutto.tpt.database.entities.Role;
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
}
