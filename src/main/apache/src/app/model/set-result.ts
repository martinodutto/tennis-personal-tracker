export class SetResult {

  private _firstPlayerGames: number;

  private _secondPlayerGames: number;

  get firstPlayerGames(): number {
    return this._firstPlayerGames;
  }

  set firstPlayerGames(value: number) {
    this._firstPlayerGames = value;
  }

  get secondPlayerGames(): number {
    return this._secondPlayerGames;
  }

  set secondPlayerGames(value: number) {
    this._secondPlayerGames = value;
  }
}
