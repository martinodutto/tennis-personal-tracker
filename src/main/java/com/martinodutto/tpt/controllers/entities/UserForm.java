package com.martinodutto.tpt.controllers.entities;

import com.martinodutto.tpt.validation.Base64Length;
import org.hibernate.validator.constraints.NotEmpty;

public class UserForm {

    @NotEmpty
    @Base64Length(max = 64)
    private String _username;

    @NotEmpty
    @Base64Length(min = 8, max = 255)
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

    @Override
    public String toString() {
        return "UserForm{" +
                "_username='" + _username + '\'' +
                '}';
    }
}
