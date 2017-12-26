export class Player {

  private _name: string;

  private _surname: string;

  private _gender: string;

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
}
