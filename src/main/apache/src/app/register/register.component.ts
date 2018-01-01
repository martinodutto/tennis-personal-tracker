import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormControl, FormGroup, Validators} from "@angular/forms";
import {AuthenticationService} from "../services/authentication/authentication.service";
import {User} from "../model/user";
import {Router} from "@angular/router";

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {

  form: FormGroup;

  constructor(private formBuilder: FormBuilder,
              private router: Router,
              private authenticationService: AuthenticationService) {
  }

  ngOnInit() {
    this.form = this.formBuilder.group({
      username: new FormControl('', [Validators.required, Validators.maxLength(64)]),
      password: new FormControl('', [Validators.required, Validators.minLength(8), Validators.maxLength(255)])
    })
  }

  register() {
    this.authenticationService.register(
      new User(this.form)
    ).subscribe(() => {
      console.info('Successful user sign-up');
      // TODO show a success message somewhere
      this.router.navigate(['login']);
    }, error => {
      console.debug(`Error while registering the new user: ${error.message}`);
      // TODO manage, maybe by redirecting on an error page
    });
  }

}
