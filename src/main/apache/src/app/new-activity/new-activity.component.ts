import {Component, OnInit} from '@angular/core';
import {SetResultComponent} from "../set-result/set-result.component";
import {AbstractControl, FormBuilder, FormControl, FormGroup, Validators} from "@angular/forms";
import {ActivityService} from "../services/activity/activity.service";
import {NgbDateISOParserFormatter} from "@ng-bootstrap/ng-bootstrap/datepicker/ngb-date-parser-formatter";
import {Activity} from "../model/activity";
import {NgbModal} from "@ng-bootstrap/ng-bootstrap";
import {Router} from "@angular/router";

@Component({
  selector: 'app-new-activity',
  templateUrl: './new-activity.component.html',
  styleUrls: ['./new-activity.component.css']
})
export class NewActivityComponent implements OnInit {
  // form model for the whole page
  form: FormGroup;
  result: SetResultComponent[];

  // injections
  constructor(private _fb: FormBuilder,
              private activityService: ActivityService,
              private modalService: NgbModal,
              private router: Router) {
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
    console.debug('Submitting the data to the server...');
    this.activityService.createActivity(
      this.mapFormToActivity(<FormGroup> this.form)
    ).subscribe((response) => {
      if (response) {
        console.debug('Form submitted correctly!');
      }
    }, (error) => {
      console.debug('Form submission ended with error {}', error);
    });
  }

  mapFormToActivity(form: FormGroup): Activity {
    let df: NgbDateISOParserFormatter = new NgbDateISOParserFormatter();
    let formValues: AbstractControl = form.value;

    let a: Activity = new Activity();
    a.activityDate = df.format(formValues['activityDate']);
    a.firstPlayerName = formValues['firstPlayerName'];
    a.secondPlayerName = formValues['secondPlayerName'];
    a.match = formValues['match'];
    a.club = formValues['club'];

    return a;
  }

  open(content) {
    this.modalService.open(content).result.then((result) => {
      if (result === 'Discarded') {
        this.router.navigate(['home']);
      }
    });
  }
}
