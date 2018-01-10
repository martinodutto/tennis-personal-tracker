package com.martinodutto.tpt.database.entities;

import com.martinodutto.tpt.security.TptUser;

import javax.annotation.Nonnull;

public class User {

    private int userId;

    private String username;

    private String password;

    private boolean enabled;

    private Integer roleId;

    public User() {
    }

    public User(@Nonnull TptUser tptUser) {
        this.username = tptUser.getUsername();
        this.password = tptUser.getPassword();
        this.enabled = tptUser.isEnabled();
        this.roleId = 2; // TODO make generic
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("[");
        sb.append("userId=").append(userId);
        sb.append(", username=").append(username);
        sb.append(", password=").append("[PROTECTED]");
        sb.append(", enabled=").append(enabled);
        sb.append(", roleId=").append(roleId);
        sb.append("]");
        return sb.toString();
    }
}
