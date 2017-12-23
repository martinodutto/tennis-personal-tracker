import {Component, OnInit} from '@angular/core';
import {SetResultComponent} from "../set-result/set-result.component";
import {FormBuilder, FormControl, FormGroup, Validators} from "@angular/forms";

@Component({
  selector: 'app-new-activity',
  templateUrl: './new-activity.component.html',
  styleUrls: ['./new-activity.component.css']
})
export class NewActivityComponent implements OnInit {
  // form model for the whole page
  form: FormGroup;

  result: SetResultComponent[];

  // injecting a form builder
  constructor(private _fb: FormBuilder) {
  }

  ngOnInit() {
    const now = new Date();
    this.form = this._fb.group({
      activityDate: new FormControl({year: now.getFullYear(), month: now.getMonth() + 1, day: now.getDate()}, [Validators.required]),
      firstPlayerName: new FormControl('', Validators.required),
      secondPlayerName: new FormControl('', Validators.required),
      club: new FormControl(''),
      match: this._fb.group({})
    });
  }

  save() {
    console.debug('Submitting the data to the server!');
    // TODO use this.form.value to recover the data from the form
  }

}
