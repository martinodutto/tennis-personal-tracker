import {Component, Input, OnInit} from '@angular/core';
import {FormArray, FormBuilder, FormControl, FormGroup} from "@angular/forms";
import {TieBreakSetValidator} from "../validators/tie-break-set-validator/tie-break-set-validator";

@Component({
  selector: 'app-match-result',
  templateUrl: './match-result.component.html',
  styleUrls: ['./match-result.component.css']
})
export class MatchResultComponent implements OnInit {

  @Input("group") match: FormGroup;

  _setNumber: number;

  @Input()
  set setNumber(setNumber: string) {
    const sn = parseInt(setNumber);
    this._setNumber = sn;
    const setArray = <FormArray> this.match.controls['sets'];
    // this gets called before ngOnInit, so we must ensure that all the elements exist
    if (setArray && setArray.length > sn) {
      for (let i = setArray.length - 1; i >= sn; i--) {
        setArray.removeAt(i);
      }
    }
  }

  constructor(private _fb: FormBuilder) {
  }

  ngOnInit() {
    this.match.addControl('sets', this._fb.array([
      this.initSet()
    ]));
  }

  initSet() {
    return this._fb.group({
      firstPlayerGames: new FormControl(0),
      secondPlayerGames: new FormControl(0)
    }, {
      validator: TieBreakSetValidator.validate
    });
  }

  // adds a new set to the match, without a result (yet)
  addNewSet() {
    const setArray = <FormArray> this.match.controls['sets'];
    setArray.push(this.initSet());
  }
}
