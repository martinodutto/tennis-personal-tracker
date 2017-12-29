import {MatchResult} from "./match-result";
import {AbstractControl, FormGroup} from "@angular/forms";
import {NgbDateISOParserFormatter} from "@ng-bootstrap/ng-bootstrap/datepicker/ngb-date-parser-formatter";
import {TimeFormatService} from "../services/time-format/time-format.service";

export class Activity {

  private _activityDate: string;

  private _firstPlayerId: number;

  private _secondPlayerId: number;

  private _activityType: string;

  private _bestOf: number;

  private _lastSetTiebreak: string;

  private _club: string;

  private _tournament: string;

  private _activityTime: string;

  private _duration: string;

  private _notes: string;

  private _match: MatchResult;

  constructor(form: FormGroup, timeFormatService: TimeFormatService) {
    let df: NgbDateISOParserFormatter = new NgbDateISOParserFormatter();
    let formValues: AbstractControl = form.value;

    this.activityDate = df.format(formValues['activityDate']);
    this.firstPlayerId = formValues['firstPlayerId'];
    this.secondPlayerId = formValues['secondPlayerId'];
    this.activityType = formValues['activityType'];
    this.bestOf = formValues['bestOf'] === 'Best of 3' ? 3 : 5;
    this.lastSetTiebreak = formValues['lastSetTiebreak'] === 'Yes' ? 'Y' : 'N';
    this.club = formValues['club'];
    this.tournament = formValues['tournament'];
    this.activityTime = timeFormatService.format(formValues['activityTime']);
    this.duration = timeFormatService.format(formValues['duration']);
    this.notes = formValues['notes'];
    this.match = formValues['match'];
  }

  get activityDate(): string {
    return this._activityDate;
  }

  set activityDate(value: string) {
    this._activityDate = value;
  }

  get firstPlayerId(): number {
    return this._firstPlayerId;
  }

  set firstPlayerId(value: number) {
    this._firstPlayerId = value;
  }

  get secondPlayerId(): number {
    return this._secondPlayerId;
  }

  set secondPlayerId(value: number) {
    this._secondPlayerId = value;
  }

  get activityType(): string {
    return this._activityType;
  }

  set activityType(value: string) {
    this._activityType = value;
  }

  get bestOf(): number {
    return this._bestOf;
  }

  set bestOf(value: number) {
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

  get notes(): string {
    return this._notes;
  }

  set notes(value: string) {
    this._notes = value;
  }

  get match(): MatchResult {
    return this._match;
  }

  set match(value: MatchResult) {
    this._match = value;
  }
}
