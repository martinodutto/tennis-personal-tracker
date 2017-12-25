import {MatchResult} from "./match-result";

export class Activity {

  private _activityDate: string;

  private _firstPlayerName: string;

  private _secondPlayerName: string;

  private _activityType: string;

  private _bestOf: string;

  private _lastSetTiebreak: string;

  private _club: string;

  private _tournament: string;

  private _activityTime: string;

  private _duration: string;

  private _match: MatchResult;

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

  get activityType(): string {
    return this._activityType;
  }

  set activityType(value: string) {
    this._activityType = value;
  }

  get bestOf(): string {
    return this._bestOf;
  }

  set bestOf(value: string) {
    this._bestOf = value;
  }

  get lastSetTiebreak(): string {
    return this._lastSetTiebreak;
  }

  set lastSetTiebreak(value: string) {
    this._lastSetTiebreak = value;
  }

  get club(): string {
    return this._club;
  }

  set club(value: string) {
    this._club = value;
  }

  get tournament(): string {
    return this._tournament;
  }

  set tournament(value: string) {
    this._tournament = value;
  }

  get activityTime(): string {
    return this._activityTime;
  }

  set activityTime(value: string) {
    this._activityTime = value;
  }

  get duration(): string {
    return this._duration;
  }

  set duration(value: string) {
    this._duration = value;
  }

  get match(): MatchResult {
    return this._match;
  }

  set match(value: MatchResult) {
    this._match = value;
  }
}
