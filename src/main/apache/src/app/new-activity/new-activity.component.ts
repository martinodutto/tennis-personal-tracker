import {debounceTime, distinctUntilChanged, map} from 'rxjs/operators';
import {Component, OnInit, ViewChild} from '@angular/core';
import {SetResultComponent} from '../set-result/set-result.component';
import {FormBuilder, FormControl, FormGroup, Validators} from '@angular/forms';
import {ActivityService} from '../services/activity/activity.service';
import {Activity} from '../model/activity';
import {NgbDateStruct, NgbModal} from '@ng-bootstrap/ng-bootstrap';
import {ActivatedRoute, Router} from '@angular/router';
import {TimeFormatService} from '../services/time-format/time-format.service';
import {PlayerService} from '../services/player/player.service';
import {Guest, Player} from '../model/player';
import {Observable} from 'rxjs';
import 'rxjs/add/operator/debounceTime';
import 'rxjs/add/operator/distinctUntilChanged';
import 'rxjs/add/operator/map';
import {AuthenticationService} from '../services/authentication/authentication.service';
import {DateFormatService} from '../services/date-format/date-format.service';

@Component({
  selector: 'app-new-activity',
  templateUrl: './new-activity.component.html',
  styleUrls: ['./new-activity.component.scss']
})
export class NewActivityComponent implements OnInit {
  // form model for the whole page
  form: FormGroup;
  newPlayerForm: FormGroup;
  optionsKnownPlayers: Player[];
  optionsActivityType: string[];
  optionsBestOf: number[];
  optionsLastSetTiebreak: string[];
  optionsGender: string[];
  result: SetResultComponent[];
  maxDatepickerDate: NgbDateStruct;
  collapsedOptionalSection = true;
  firstPlayerFullName: string;
  secondPlayerFullName: string;
  newPlayerErrorMessage: string;
  submitErrorMessage: string;
  hasBeenSaved: boolean;
  typeaheadClubs: string[] = []; // loaded asynchronously
  @ViewChild('discardModalContent') discardModal: any;
  searchClub = (text$: Observable<string>) => {
    return text$.pipe(
      debounceTime(200),
      distinctUntilChanged(),
      map(searchKey => searchKey.length < 2 ? [] : this.typeaheadClubs
        .filter(v => v.toLowerCase().indexOf(searchKey.toLowerCase()) >= 0)
        .slice(0, 10)
      )
    );
  }

  // injections
  constructor(private formBuilder: FormBuilder,
              private activityService: ActivityService,
              private modalService: NgbModal,
              private router: Router,
              private route: ActivatedRoute,
              private dateFormatService: DateFormatService,
              private timeFormatService: TimeFormatService,
              private playerService: PlayerService,
              private authenticationService: AuthenticationService) {
  }

  ngOnInit() {
    this.hasBeenSaved = false;
    const now = new Date();
    this.maxDatepickerDate = {year: now.getFullYear(), month: now.getMonth() + 1, day: now.getDate()};
    this.optionsActivityType = [
      'Match',
      'Training'
    ];
    this.optionsBestOf = [
      3,
      5
    ];
    this.optionsLastSetTiebreak = [
      'Yes',
      'No'
    ];
    this.optionsGender = [
      'M',
      'F'
    ];

    // loaded by the server (sync)
    const currentPlayer: Player = this.route.snapshot.data['currentPlayer'];
    this.optionsKnownPlayers = this.route.snapshot.data['knownPlayers'];

    this.firstPlayerFullName = this.getFullName(currentPlayer);

    // async loaders
    this.activityService.getUserClubs(currentPlayer.playerId).subscribe(clubs => {
      this.typeaheadClubs = clubs;
    }, error => {
      console.error(`Error while loading the clubs: ${error.message}`);
      this.typeaheadClubs = [];
    });

    this.form = this.formBuilder.group({
      activityDate: new FormControl({
        year: now.getFullYear(),
        month: now.getMonth() + 1,
        day: now.getDate()
      }, [Validators.required]),
      firstPlayerId: new FormControl(currentPlayer.playerId, Validators.required), // a "fake" control, because this is constant
      // this trick lets us have an empty and invalid default
      secondPlayerId: new FormControl(this.optionsKnownPlayers[0].playerId, Validators.min(0)),
      activityType: new FormControl(this.optionsActivityType[0]),
      bestOf: new FormControl(this.optionsBestOf[0]),
      lastSetTiebreak: new FormControl(this.optionsLastSetTiebreak[0]),
      club: new FormControl(''),
      tournament: new FormControl(''),
      activityTime: new FormControl({}),
      duration: new FormControl({}),
      notes: new FormControl(''),
      match: this.formBuilder.group({})
    });
  }

  save() {
    console.debug('Submitting the data to the server...');
    this.submitErrorMessage = null; // reset, in case an error message was already displayed
    this.activityService.createActivity(
      new Activity(<FormGroup> this.form, this.dateFormatService, this.timeFormatService)
    ).subscribe(() => {
      console.debug('Form submitted correctly!');
      this.hasBeenSaved = true;
      this.router.navigate(['past']);
    }, (error) => {
      console.error(`Form submission ended with error: ${error.message}`);
      switch (error.status) {
        case 400: {
          this.submitErrorMessage = 'An empty form was submitted';
          break;
        }
        case 406: {
          this.submitErrorMessage = 'An invalid form was submitted';
          break;
        }
        default: {
          this.submitErrorMessage = 'An internal error occurred while saving the activity';
        }
      }
    });
  }

  goToHome(event: MouseEvent) {
    event.preventDefault();
    this.router.navigate(['home']);
  }

  openDiscardModal(): Promise<boolean> {
    return this.modalService.open(this.discardModal).result.then((result) => {
      if (result === 'Discarded') {
        return true;
      }
    }, () => {
      return false;
    });
  }

  openNewPlayerModal(content) {
    this.newPlayerForm = this.formBuilder.group({
      name: new FormControl('', [Validators.required, Validators.maxLength(32)]),
      surname: new FormControl('', [Validators.required, Validators.maxLength(32)]),
      gender: new FormControl(this.optionsGender[0], [Validators.required, Validators.maxLength(1)])
    });

    this.newPlayerErrorMessage = null; // reset, in case an error message was already displayed
    this.modalService.open(content).result.then((result) => {
      if (result && result instanceof Object) {
        const newPlayer: Player = new Player(result, Guest.Y);
        this.playerService.createPlayer(
          newPlayer
        ).subscribe((p) => {
          console.debug(`Player ${p.name} ${p.surname} created correctly!`);
          this.optionsKnownPlayers.push(p);
          this.form.patchValue({
            secondPlayerId: p.playerId
          });
        }, error => {
          console.error(`Player creation ended with error: ${error.message}`);
          switch (error.status) {
            case 406: {
              this.newPlayerErrorMessage = 'Invalid player';
              break;
            }
            case 409: {
              this.newPlayerErrorMessage = 'Found a duplicate player: please choose another name/surname and try again';
              break;
            }
            default: {
              this.newPlayerErrorMessage = 'An error occurred while adding the player';
            }
          }
        });
      }
    }, () => {
      // nothing to do
    });
  }

  onSecondPlayerChange(event) {
    const selectedPlayers: Player[] = this.optionsKnownPlayers.filter(player => {
      return player.playerId === parseInt(event, 10);
    });
    if (selectedPlayers && selectedPlayers.length > 0) {
      this.secondPlayerFullName = this.getFullName(selectedPlayers[0]);
    }
  }

  onActivityTypeChange(event) {
    // in case of training, all the options concerning the match must be disabled
    if (event === this.optionsActivityType[1]) {
      this.form.controls['bestOf'].disable();
      this.form.controls['lastSetTiebreak'].disable();
    } else if (event === this.optionsActivityType[0]) {
      this.form.controls['bestOf'].enable();
      this.form.controls['lastSetTiebreak'].enable();
    }
  }

  toggleCollapse() {
    this.collapsedOptionalSection = !this.collapsedOptionalSection;
  }

  private getFullName(p: Player) {
    if (p.name && p.surname) {
      return p.name + ' ' + p.surname;
    } else {
      return '';
    }
  }

  /**
   * When returning true, this method allows for a straightforward route change towards another page.
   * When returning false, it sets the conditions under which a discard confirmation popup displays.
   *
   * @returns {boolean} True to let the route change, false to ask for confirmation.
   */
  canDeactivate(): boolean {
    return this.hasBeenSaved || this.form.pristine || !this.authenticationService.isSignedIn();
  }
}
