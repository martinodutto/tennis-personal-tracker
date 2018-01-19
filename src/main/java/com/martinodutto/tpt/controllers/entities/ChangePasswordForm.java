package com.martinodutto.tpt.controllers.entities;

import org.hibernate.validator.constraints.NotEmpty;

public class ChangePasswordForm {

    @NotEmpty
    private String _oldPassword;

    @NotEmpty
    private String _newPassword;

    public String get_oldPassword() {
        return _oldPassword;
    }

    public void set_oldPassword(String _oldPassword) {
        this._oldPassword = _oldPassword;
    }

    public String get_newPassword() {
        return _newPassword;
    }

    public void set_newPassword(String _newPassword) {
        this._newPassword = _newPassword;
    }
}
