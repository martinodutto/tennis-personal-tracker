import {AbstractControl, FormGroup, ValidationErrors} from '@angular/forms';
import {isNullOrUndefined} from 'util';

export class TieBreakSetValidator {

  static validate(c: AbstractControl): ValidationErrors | null {
    const g = <FormGroup> c;
    const c1 = g.controls['firstPlayerGames'];
    const c2 = g.controls['secondPlayerGames'];
    const errorDetails: Object = {};

    if (!isNullOrUndefined(c1.value)) {
      if (c1.value < 0) {
        errorDetails['min'] = 0;
      } else if (c1.value > 7) {
        errorDetails['max'] = 7;
      } else {
        if (!isNullOrUndefined(c2.value)) {
          if ((c1.value === 7 && c2.value < 5) || (c1.value === 7 && c2.value === 7)) {
            errorDetails['invalidResult'] = true;
          }
        }
      }
    } else {
      errorDetails['required'] = true;
    }

    if (!isNullOrUndefined(c2.value)) {
      if (c2.value < 0) {
        errorDetails['min'] = 0;
      } else if (c2.value > 7) {
        errorDetails['max'] = 7;
      } else {
        if (!isNullOrUndefined(c1.value)) {
          if (c2.value === 7 && c1.value < 5) {
            errorDetails['invalidResult'] = true;
          }
        }
      }
    } else {
      errorDetails['required'] = true;
    }

    let empty = true;
    for (const prop in errorDetails) {
      if (errorDetails.hasOwnProperty(prop)) {
        empty = false;
        break;
      }
    }

    return empty ? null : {'tieBreakSet': errorDetails};
  }
}
