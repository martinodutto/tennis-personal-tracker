import {AbstractControl, FormGroup} from "@angular/forms";

export class Player {

  private _name: string;

  private _surname: string;

  private _gender: string;

  private _guest: Guest;

  constructor(form: FormGroup, guest: Guest) {
    let formValues: AbstractControl = form.value;

    this.name = formValues['name'];
    this.surname = formValues['surname'];
    this.gender = formValues['gender'];
    this.guest = guest;
  }

  get name(): string {
    return this._name;
  }

  set name(value: string) {
    this._name = value;
  }

  get surname(): string {
    return this._surname;
  }

  set surname(value: string) {
    this._surname = value;
  }

  get gender(): string {
    return this._gender;
  }

  set gender(value: string) {
    this._gender = value;
  }

  get guest(): Guest {
    return this._guest;
  }

  set guest(value: Guest) {
    this._guest = value;
  }
}

export enum Guest {
  Y = "Y",
  N = "N"
}
