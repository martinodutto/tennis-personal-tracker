import {Component, OnInit} from '@angular/core';
import {SetResultComponent} from "../set-result/set-result.component";

@Component({
  selector: 'app-match-result',
  templateUrl: './match-result.component.html',
  styleUrls: ['./match-result.component.css']
})
export class MatchResultComponent implements OnInit {

  sets: SetResultComponent[];

  constructor() {
    this.sets = [new SetResultComponent()];
  }

  // adds a new set to the match, without a result (yet)
  addNewSet() {
    this.sets.push(new SetResultComponent());
  }

  ngOnInit() {
  }

}
