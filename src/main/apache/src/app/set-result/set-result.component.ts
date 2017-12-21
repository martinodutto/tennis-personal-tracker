import {Component, Input, OnInit} from '@angular/core';
import {FormGroup} from "@angular/forms";

@Component({
  selector: 'app-set-result',
  templateUrl: './set-result.component.html',
  styleUrls: ['./set-result.component.css']
})
export class SetResultComponent implements OnInit {

  @Input("group") set: FormGroup;

  ngOnInit() {
    //
  }
}
