import {Component, OnInit, ViewChild} from '@angular/core';
import {FormBuilder, FormControl, FormGroup, Validators} from "@angular/forms";
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
      name: new FormControl('', [Validators.required, Validators.maxLength(32)]),
      surname: new FormControl('', [Validators.required, Validators.maxLength(32)]),
      gender: new FormControl(this.optionsGender[0], [Validators.required, Validators.maxLength(1)])
    });
  }

  createNewPlayer() {
    console.debug('Creating new player...');
    this.playerService.createPlayer(
      new Player(this.form, Guest.N)
    ).subscribe(() => {
      console.debug('Player created correctly!');
      this.modalService.open(this.successModalContent, {backdrop: "static"}).result.then(() => {
        this.router.navigate(['home']);
      });
    }, error => {
      console.error(`Player creation ended with error: ${error.message}`);
      // TODO manage error
    });
  }
}
