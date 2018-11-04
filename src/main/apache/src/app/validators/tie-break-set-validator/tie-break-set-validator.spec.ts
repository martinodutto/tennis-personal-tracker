import {TieBreakSetValidator} from './tie-break-set-validator';
import {FormControl, FormGroup} from '@angular/forms';

describe('TieBreakSetValidator', () => {

  let testFormGroup: FormGroup;

  beforeEach(() => {
    testFormGroup = new FormGroup({
      firstPlayerGames: new FormControl(0),
      secondPlayerGames: new FormControl(0)
    });
  });

  it('should create an instance', () => {
    expect(new TieBreakSetValidator()).toBeTruthy();
  });

  it('validates a 6 - 3', () => {
    testFormGroup.setValue({
      firstPlayerGames: 6,
      secondPlayerGames: 3
    });
    expect(TieBreakSetValidator.validate(testFormGroup)).toBeNull();
  });

  it('validates a 2 - 6', () => {
    testFormGroup.setValue({
      firstPlayerGames: 2,
      secondPlayerGames: 6
    });
    expect(TieBreakSetValidator.validate(testFormGroup)).toBeNull();
  });

  it('validates a 0 - 0', () => {
    expect(TieBreakSetValidator.validate(testFormGroup)).toBeNull();
  });

  it('validates a 7 - 6', () => {
    testFormGroup.setValue({
      firstPlayerGames: 7,
      secondPlayerGames: 6
    });
    expect(TieBreakSetValidator.validate(testFormGroup)).toBeNull();
  });

  it('validates a 6 - 7', () => {
    testFormGroup.setValue({
      firstPlayerGames: 6,
      secondPlayerGames: 7
    });
    expect(TieBreakSetValidator.validate(testFormGroup)).toBeNull();
  });

  it('validates a 7 - 5', () => {
    testFormGroup.setValue({
      firstPlayerGames: 7,
      secondPlayerGames: 5
    });
    expect(TieBreakSetValidator.validate(testFormGroup)).toBeNull();
  });

  it('validates a 5 - 7', () => {
    testFormGroup.setValue({
      firstPlayerGames: 5,
      secondPlayerGames: 7
    });
    expect(TieBreakSetValidator.validate(testFormGroup)).toBeNull();
  });

  it('validates a 6 - 6', () => {
    testFormGroup.setValue({
      firstPlayerGames: 6,
      secondPlayerGames: 6
    });
    expect(TieBreakSetValidator.validate(testFormGroup)).toBeNull();
  });

  it('validates a 6 - 0', () => {
    testFormGroup.setValue({
      firstPlayerGames: 6,
      secondPlayerGames: 0
    });
    expect(TieBreakSetValidator.validate(testFormGroup)).toBeNull();
  });

  it('does not validate an 8 - 6', () => {
    testFormGroup.setValue({
      firstPlayerGames: 8,
      secondPlayerGames: 6
    });
    expect(TieBreakSetValidator.validate(testFormGroup)).toEqual({tieBreakSet: {max: 7}});
  });

  it('does not validate a 4 - 7', () => {
    testFormGroup.setValue({
      firstPlayerGames: 4,
      secondPlayerGames: 7
    });
    expect(TieBreakSetValidator.validate(testFormGroup)).toEqual({tieBreakSet: {invalidResult: true}});
  });

  it('does not validate a 7 - 0', () => {
    testFormGroup.setValue({
      firstPlayerGames: 7,
      secondPlayerGames: 0
    });
    expect(TieBreakSetValidator.validate(testFormGroup)).toEqual({tieBreakSet: {invalidResult: true}});
  });

  it('does not validate a 7 - 7', () => {
    testFormGroup.setValue({
      firstPlayerGames: 7,
      secondPlayerGames: 7
    });
    expect(TieBreakSetValidator.validate(testFormGroup)).toEqual({tieBreakSet: {invalidResult: true}});
  });

  it('an 8-8 returns just one "max number of games" error message', () => {
    testFormGroup.setValue({
      firstPlayerGames: 8,
      secondPlayerGames: 8
    });
    expect(TieBreakSetValidator.validate(testFormGroup)).toEqual({tieBreakSet: {max: 7}});
  });

  it('does not validate a 3 - null', () => {
    testFormGroup.setValue({
      firstPlayerGames: 3,
      secondPlayerGames: null
    });
    expect(TieBreakSetValidator.validate(testFormGroup)).toEqual({tieBreakSet: {required: true}});
  });

  it('does not validate a null - null', () => {
    testFormGroup.setValue({
      firstPlayerGames: null,
      secondPlayerGames: null
    });
    expect(TieBreakSetValidator.validate(testFormGroup)).toEqual({tieBreakSet: {required: true}});
  });

  it('does not validate a 4e5 - 5', () => {
    testFormGroup.setValue({
      firstPlayerGames: 4e5,
      secondPlayerGames: 5
    });
    expect(TieBreakSetValidator.validate(testFormGroup)).toEqual({tieBreakSet: {max: 7}});
  });

  it('does not validate a -7 - 3', () => {
    testFormGroup.setValue({
      firstPlayerGames: -7,
      secondPlayerGames: 3
    });
    expect(TieBreakSetValidator.validate(testFormGroup)).toEqual({tieBreakSet: {min: 0}});
  });

  it('returns two correct error messages for a 12 - -5', () => {
    testFormGroup.setValue({
      firstPlayerGames: 12,
      secondPlayerGames: -5
    });
    expect(TieBreakSetValidator.validate(testFormGroup)).toEqual({tieBreakSet:
        {min: 0, max: 7}
    });
  });

  it('returns two correct error messages for a -3 - null', () => {
    testFormGroup.setValue({
      firstPlayerGames: -3,
      secondPlayerGames: null
    });
    expect(TieBreakSetValidator.validate(testFormGroup)).toEqual({tieBreakSet:
        {min: 0, required: true}
    });
  });

  it('does not validate an 1e1 - 3', () => {
    testFormGroup.setValue({
      firstPlayerGames: 1e1,
      secondPlayerGames: 3
    });
    expect(TieBreakSetValidator.validate(testFormGroup)).toEqual({tieBreakSet:
        {max: 7}
    });
  });
});
