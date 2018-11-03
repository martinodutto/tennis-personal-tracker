import {SetResult} from './set-result';

export class MatchResult {

  private _sets: SetResult[];

  get sets(): SetResult[] {
    return this._sets;
  }

  set sets(value: SetResult[]) {
    this._sets = value;
  }
}
