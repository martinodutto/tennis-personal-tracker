import {Component, OnInit} from '@angular/core';
import {SetResultComponent} from "../set-result/set-result.component";
import {AbstractControl, FormBuilder, FormControl, FormGroup, Validators} from "@angular/forms";
import {ActivityService} from "../services/activity/activity.service";
import {NgbDateISOParserFormatter} from "@ng-bootstrap/ng-bootstrap/datepicker/ngb-date-parser-formatter";
import {Activity} from "../model/activity";
import {NgbModal} from "@ng-bootstrap/ng-bootstrap";
import {Router} from "@angular/router";
import {TimeFormatService} from "../services/time-format/time-format.service";
import {PlayerService} from "../services/player/player.service";
import {Guest, Player} from "../model/player";

@Component({
  selector: 'app-new-activity',
  templateUrl: './new-activity.component.html',
  styleUrls: ['./new-activity.component.css']
})
export class NewActivityComponent implements OnInit {
  // form model for the whole page
  form: FormGroup;
  newPlayerForm: FormGroup;
  optionsActivityType: Array<string>;
  optionsBestOf: Array<string>;
  optionsLastSetTiebreak: Array<string>;
  optionsGender: Array<string>;
  result: SetResultComponent[];

  // injections
  constructor(private formBuilder: FormBuilder,
              private activityService: ActivityService,
              private modalService: NgbModal,
              private router: Router,
              private timeFormatService: TimeFormatService,
              private playerService: PlayerService) {
  }

  ngOnInit() {
    const now = new Date();
    this.optionsActivityType = [
      'Match',
      'Training'
    ];
    this.optionsBestOf = [
      'Best of 3',
      'Best of 5'
    ];
    this.optionsLastSetTiebreak = [
      'Yes',
      'No'
    ];
    this.optionsGender = [
      'M',
      'F'
    ];
    this.form = this.formBuilder.group({
      activityDate: new FormControl({year: now.getFullYear(), month: now.getMonth() + 1, day: now.getDate()}, [Validators.required]),
      firstPlayerName: new FormControl('', Validators.required),
      secondPlayerName: new FormControl('', Validators.required),
      activityType: new FormControl(this.optionsActivityType[0]),
      bestOf: new FormControl(this.optionsBestOf[0]),
      lastSetTiebreak: new FormControl(this.optionsLastSetTiebreak[0]),
      club: new FormControl(''),
      tournament: new FormControl(''),
      activityTime: new FormControl({}),
      duration: new FormControl({}),
      match: this.formBuilder.group({})
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
      console.error(`Form submission ended with error: ${error.message}`);
    });
  }

  mapFormToActivity(form: FormGroup): Activity {
    let df: NgbDateISOParserFormatter = new NgbDateISOParserFormatter();
    let formValues: AbstractControl = form.value;

    let a: Activity = new Activity();
    a.activityDate = df.format(formValues['activityDate']); // the activity date should never be null at this point
    a.firstPlayerName = formValues['firstPlayerName'];
    a.secondPlayerName = formValues['secondPlayerName'];
    a.activityType = formValues['activityType'];
    a.bestOf = formValues['bestOf'];
    a.lastSetTiebreak = formValues['lastSetTiebreak'];
    a.club = formValues['club'];
    a.tournament = formValues['tournament'];
    a.activityTime = this.timeFormatService.format(formValues['activityTime']);
    a.duration = this.timeFormatService.format(formValues['duration']);
    a.match = formValues['match'];

    return a;
  }

  openDiscardModal(content) {
    this.modalService.open(content).result.then((result) => {
      if (result === 'Discarded') {
        this.router.navigate(['home']);
      }
    }, () => {
      // nothing to do
    });
  }

  openNewPlayerModal(content) {
    this.newPlayerForm = this.formBuilder.group({
      name: new FormControl('', Validators.required),
      surname: new FormControl('', Validators.required),
      gender: new FormControl(this.optionsGender[0], Validators.required)
    });
    this.modalService.open(content).result.then((result) => {
      if (result && result instanceof Object) {
        const newPlayer: Player = new Player(result, Guest.Y);
        this.playerService.createPlayer(
          newPlayer
        ).subscribe(() => {
          console.debug('Player created correctly!');
          this.form.patchValue({
            secondPlayerName: newPlayer.name + ' ' + newPlayer.surname
          });
        }, error => {
          console.error(`Player creation ended with error: ${error.message}`);
          // TODO manage error
        });
      }
    }, () => {
      // nothing to do
    });
  }

  onBestOfChange(event) {
    console.debug('3 or 5 setter option changed!');
    // TODO implement logic
  }

  onActivityTypeChange(event) {
    // in case of training, all the options concerning the match must be disabled
    if (event === this.optionsActivityType[1]) {
      this.form.controls['bestOf'].disable();
      this.form.controls['lastSetTiebreak'].disable();
      this.form.controls['tournament'].disable();
    } else if (event === this.optionsActivityType[0]) {
      this.form.controls['bestOf'].enable();
      this.form.controls['lastSetTiebreak'].enable();
      this.form.controls['tournament'].enable();
    }
  }
}
