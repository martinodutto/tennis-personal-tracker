import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormControl, FormGroup, Validators} from "@angular/forms";
import {Router} from "@angular/router";
import {AuthenticationService} from "../services/authentication/authentication.service";
import {User} from "../model/user";
import {PlayerService} from "../services/player/player.service";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  form: FormGroup;
  loginErrorMessage: string;
  loading: boolean;

  constructor(private formBuilder: FormBuilder,
              private router: Router,
              private authenticationService: AuthenticationService,
              private playerService: PlayerService) { }

  ngOnInit() {
    this.loading = false;
    this.form = this.formBuilder.group({
      username: new FormControl('', [Validators.required, Validators.maxLength(64)]),
      password: new FormControl('', [Validators.required, Validators.minLength(8), Validators.maxLength(255)])
    })
  }

  login() {
    console.debug('Logging in!');
    this.loginErrorMessage = null; // reset, in case an error message was already displayed
    this.loading = true;
    this.authenticationService.login(
      new User(this.form)
    ).subscribe(response => {
      this.loading = false;
      // we set the JWT on the local storage, where it can be retrieved by subsequent requests
      this.authenticationService.saveJwtTokenAndUsername(response.token, this.form.value['username']);
      this.playerService.getCurrentPlayer().subscribe(player => {
        if (player === null) {
          this.router.navigate(['newplayer']);
        } else {
          this.router.navigate(['home']);
        }
      });
    }, error => {
      this.loading = false;
      console.error(`Error while logging in: ${error.message}`);
      this.loginErrorMessage = error.error ? error.error.message || 'Error while logging in' : 'Error while logging in';
    });
  }

  signUp() {
    this.router.navigate(['register']);
  }
}
