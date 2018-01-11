import {Component, OnInit, ViewChild} from '@angular/core';
import {FormBuilder, FormControl, FormGroup, Validators} from "@angular/forms";
import {AuthenticationService} from "../services/authentication/authentication.service";
import {User} from "../model/user";
import {Router} from "@angular/router";
import {PasswordConfirmationValidator} from "../validators/password-confirmation-validator/password-confirmation-validator";
import {NgbModal} from "@ng-bootstrap/ng-bootstrap";
import {HttpErrorResponse} from "@angular/common/http";

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {

  form: FormGroup;
  passwordsForm: FormGroup;
  signUpErrorMessage: string;
  @ViewChild("successModalContent") successModalContent: any;

  constructor(private formBuilder: FormBuilder,
              private router: Router,
              private authenticationService: AuthenticationService,
              private modalService: NgbModal) {
  }

  ngOnInit() {
    this.passwordsForm = this.formBuilder.group({
      password: new FormControl('', [Validators.required, Validators.minLength(8), Validators.maxLength(255)]),
      confirmPassword: new FormControl('', [Validators.required, Validators.minLength(8), Validators.maxLength(255)])
    }, {
      validator: PasswordConfirmationValidator.validate
    });

    this.form = this.formBuilder.group({
      username: new FormControl('', [Validators.required, Validators.maxLength(64)]),
      passwords: this.passwordsForm
    });
  }

  register() {
    this.signUpErrorMessage = null; // reset, in case an error message was already displayed
    this.authenticationService.register(
      new User(this.form, this.passwordsForm)
    ).subscribe(() => {
      console.info('Successful user sign-up');
      this.modalService.open(this.successModalContent, {backdrop: "static"}).result.then(() => {
        this.router.navigate(['login']);
      });
    }, (error: HttpErrorResponse) => {
      console.debug(`Error while registering the new user: ${error.message}`);
      switch (error.status) {
        case 409: {
          this.signUpErrorMessage = 'Name already taken! Please choose another';
          break;
        }
        case 500: {
          if (error.error && error.error.message === 'Unregistered role') {
            this.signUpErrorMessage = 'Unregistered role. Please contact the administrator';
            break;
          }
        }
        default: {
          this.signUpErrorMessage = 'Error while signing up';
        }
      }
    });
  }

  goToLogin() {
    this.router.navigate(['login']);
  }

}
