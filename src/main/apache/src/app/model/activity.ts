import {MatchResult} from "./match-result";

export class Activity {

  private _activityDate: string;

  private _firstPlayerName: string;

  private _secondPlayerName: string;

  private _match: MatchResult;

  private _club: String;

  get activityDate(): string {
    return this._activityDate;
  }

  set activityDate(value: string) {
    this._activityDate = value;
  }

  get firstPlayerName(): string {
    return this._firstPlayerName;
  }

  set firstPlayerName(value: string) {
    this._firstPlayerName = value;
  }

  get secondPlayerName(): string {
    return this._secondPlayerName;
  }

  set secondPlayerName(value: string) {
    this._secondPlayerName = value;
  }

  get match(): MatchResult {
    return this._match;
  }

  set match(value: MatchResult) {
    this._match = value;
  }

  get club(): String {
    return this._club;
  }

  set club(value: String) {
    this._club = value;
  }
}
