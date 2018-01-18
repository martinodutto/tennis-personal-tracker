import {AbstractControl, FormGroup} from "@angular/forms";

export class ChangePassword {

  private _oldPassword: string;

  private _newPassword: string;

  constructor(form: FormGroup, passwordsForm: FormGroup) {
    let formValues: AbstractControl = form.value;

    this.oldPassword = formValues['oldPassword'];
    this.newPassword = passwordsForm.value['password'];
  }

  get oldPassword(): string {
    return this._oldPassword;
  }

  set oldPassword(value: string) {
    this._oldPassword = value;
  }

  get newPassword(): string {
    return this._newPassword;
  }

  set newPassword(value: string) {
    this._newPassword = value;
  }
}
