import {AbstractControl, FormGroup} from "@angular/forms";

export class User {

  private _username;

  private _password;

  constructor(form: FormGroup) {
    let formValues: AbstractControl = form.value;

    this.username = formValues['username'];
    this.password = formValues['password'];
  }

  get username() {
    return this._username;
  }

  set username(value) {
    this._username = value;
  }

  get password() {
    return this._password;
  }

  set password(value) {
    this._password = value;
  }
}
