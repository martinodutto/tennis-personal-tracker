import {Component, OnInit} from '@angular/core';
import {NgbDateStruct} from "@ng-bootstrap/ng-bootstrap";

@Component({
  selector: 'app-new-activity',
  templateUrl: './new-activity.component.html',
  styleUrls: ['./new-activity.component.css']
})
export class NewActivityComponent implements OnInit {

  activityDate: NgbDateStruct;

  constructor() { }

  ngOnInit() {
  }

}
