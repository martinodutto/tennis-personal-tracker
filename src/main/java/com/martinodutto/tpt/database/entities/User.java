package com.martinodutto.tpt.database.entities;

import com.martinodutto.tpt.controllers.entities.UserForm;

import javax.annotation.Nonnull;

public class User {

    private int userId;

    private String username;

    private String password;

    private boolean enabled;

    private Integer roleId;

    public User() {
    }

    public User(@Nonnull UserForm form) {
        this.username = form.get_username();
        this.password = form.get_password();
        this.enabled = true; // when a user is created, it is enabled by default
        // TODO which role do we assign?
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
        return "User{" +
                "userId=" + userId +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", enabled=" + enabled +
                ", roleId=" + roleId +
                '}';
    }
}
