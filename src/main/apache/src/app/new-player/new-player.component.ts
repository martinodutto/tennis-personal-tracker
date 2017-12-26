import {Component, OnInit, ViewChild} from '@angular/core';
import {AbstractControl, FormBuilder, FormControl, FormGroup, Validators} from "@angular/forms";
import {Guest, Player} from "../model/player";
import {PlayerService} from "../services/player/player.service";
import {NgbModal} from "@ng-bootstrap/ng-bootstrap";
import {Router} from "@angular/router";

@Component({
  selector: 'app-new-player',
  templateUrl: './new-player.component.html',
  styleUrls: ['./new-player.component.css']
})
export class NewPlayerComponent implements OnInit {

  form: FormGroup;
  optionsGender: Array<string>;
  @ViewChild("successModalContent") successModalContent: any;

  constructor(private formBuilder: FormBuilder,
              private playerService: PlayerService,
              private modalService: NgbModal,
              private router: Router) { }

  ngOnInit() {
    this.optionsGender = [
      'M',
      'F'
    ];
    this.form = this.formBuilder.group({
      name: new FormControl('', Validators.required),
      surname: new FormControl('', Validators.required),
      gender: new FormControl(this.optionsGender[0], Validators.required)
    });
  }

  createNewPlayer() {
    console.debug('Creating new player...');
    this.playerService.createPlayer(
      this.mapFormToPlayer(this.form)
    ).subscribe(response => {
      console.debug('Player created correctly!');
      this.modalService.open(this.successModalContent, {backdrop: "static"}).result.then(() => {
        this.router.navigate(['home']);
      });
    }, error => {
      console.error(`Player creation ended with error: ${error.message}`);
      // TODO manage error
    });
  }

  /**
   * Maps the form to an instance of a {@link Player}
   *
   * @param {FormGroup} form Form that has to be mapped.
   * @returns {Player} Player representing the form content.
   */
  mapFormToPlayer(form: FormGroup): Player {
    let formValues: AbstractControl = form.value;

    let p: Player = new Player();
    p.name = formValues['name'];
    p.surname = formValues['surname'];
    p.gender = formValues['gender'];
    p.guest = Guest.N;

    return p;
  }
}
