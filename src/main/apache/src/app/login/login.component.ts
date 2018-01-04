import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormControl, FormGroup, Validators} from "@angular/forms";
import {Router} from "@angular/router";
import {AuthenticationService} from "../services/authentication/authentication.service";
import {User} from "../model/user";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  form: FormGroup;
  loginErrorMessage: string;

  constructor(private formBuilder: FormBuilder,
              private router: Router,
              private authenticationService: AuthenticationService) { }

  ngOnInit() {
    this.form = this.formBuilder.group({
      username: new FormControl('', [Validators.required, Validators.maxLength(64)]),
      password: new FormControl('', [Validators.required, Validators.minLength(8), Validators.maxLength(255)])
    })
  }

  login() {
    console.debug('Logging in!');
    this.loginErrorMessage = null; // reset, in case an error message was already displayed
    this.authenticationService.login(
      new User(this.form)
    ).subscribe(response => {
      // console.debug(`Successfully logged in with token ${response.token}`);
      // we set the JWT on the local storage, where it can be retrieved by subsequent requests
      this.authenticationService.saveJwtToken(response.token);
      this.router.navigate(['home']);
    }, error => {
      console.error(`Error while logging in: ${error.message}`);
      this.loginErrorMessage = error.error ? error.error.message : 'Invalid credentials';
    });
  }

  signUp() {
    this.router.navigate(['register']);
  }
}
