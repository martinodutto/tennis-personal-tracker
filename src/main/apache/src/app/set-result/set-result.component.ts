import {Component, OnInit} from '@angular/core';

@Component({
  selector: 'app-set-result',
  templateUrl: './set-result.component.html',
  styleUrls: ['./set-result.component.css']
})
export class SetResultComponent implements OnInit {

  firstPlayerGames: number;
  secondPlayerGames: number;
  // setResultControl: FormControl;

  constructor() {
    this.firstPlayerGames = 0;
    this.secondPlayerGames = 0;
    // this.setResultControl = new FormControl("", [Validators.min(0), Validators.max(7)]);
  }

  ngOnInit() {
  }

}
