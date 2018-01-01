package com.martinodutto.tpt.controllers.entities;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

public class UserForm {

    @NotEmpty
    @Length(max = 64)
    private String _username;

    @NotEmpty
    @Length(min = 8, max = 255)
    private String _password;

    public String get_username() {
        return _username;
    }

    public void set_username(String _username) {
        this._username = _username;
    }

    public String get_password() {
        return _password;
    }

    public void set_password(String _password) {
        this._password = _password;
    }

    public UsernamePasswordAuthenticationToken toAuthenticationToken() {
        return new UsernamePasswordAuthenticationToken(_username, _password);
    }

    @Override
    public String toString() {
        return "UserForm{" +
                "_username='" + _username + '\'' +
                '}';
    }
}
