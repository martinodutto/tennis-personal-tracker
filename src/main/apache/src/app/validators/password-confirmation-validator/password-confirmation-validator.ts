import {AbstractControl, FormControl, FormGroup, ValidationErrors} from "@angular/forms";

export class PasswordConfirmationValidator {

  static validate(c: AbstractControl): ValidationErrors | null {
    let pwdConfirmation = <FormControl> c;
    let formGroup = <FormGroup> pwdConfirmation.parent;

    if (formGroup) {
      let pwd = formGroup.controls['password'];

      if (pwdConfirmation.value !== pwd.value) {
        return {
          "passwordValidator": {
            "matching": false
          }
        }
      }
    }

    return null;
  }
}
