import {AbstractControl, FormGroup, ValidationErrors} from "@angular/forms";

export class PasswordConfirmationValidator {

  static validate(c: AbstractControl): ValidationErrors | null {
    let g = <FormGroup> c;
    let c1 = g.controls['password'];
    let c2 = g.controls['confirmPassword'];

    if (c1.value !== c2.value) {
      return {
        "passwordValidator": {
          "matching": false
        }
      }
    }

    return null;
  }
}
