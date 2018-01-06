export class LoggedUser {

  private _userName: string;

  private _playerName: string;

  private _playerSurname: string;

  get userName(): string {
    return this._userName;
  }

  set userName(value: string) {
    this._userName = value;
  }

  get playerName(): string {
    return this._playerName;
  }

  set playerName(value: string) {
    this._playerName = value;
  }

  get playerSurname(): string {
    return this._playerSurname;
  }

  set playerSurname(value: string) {
    this._playerSurname = value;
  }
}
