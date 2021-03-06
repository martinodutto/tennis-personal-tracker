package cloud.martinodutto.tpt.controllers.entities;

import cloud.martinodutto.tpt.validation.Base64Length;

import javax.validation.constraints.NotEmpty;

public class ChangePasswordForm {

    @NotEmpty
    @Base64Length(min = 8, max = 255)
    private String _oldPassword;

    @NotEmpty
    @Base64Length(min = 8, max = 255)
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
