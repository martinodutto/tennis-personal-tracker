import {Component, OnInit, ViewChild} from '@angular/core';
import {FormBuilder, FormControl, FormGroup, Validators} from "@angular/forms";
import {AuthenticationService} from "../services/authentication/authentication.service";
import {User} from "../model/user";
import {Router} from "@angular/router";
import {PasswordConfirmationValidator} from "../validators/password-confirmation-validator/password-confirmation-validator";
import {NgbModal} from "@ng-bootstrap/ng-bootstrap";

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {

  form: FormGroup;
  signUpErrorMessage: string;
  @ViewChild("successModalContent") successModalContent: any;

  constructor(private formBuilder: FormBuilder,
              private router: Router,
              private authenticationService: AuthenticationService,
              private modalService: NgbModal) {
  }

  ngOnInit() {
    this.form = this.formBuilder.group({
      username: new FormControl('', [Validators.required, Validators.maxLength(64)]),
      password: new FormControl('', [Validators.required, Validators.minLength(8), Validators.maxLength(255)]),
      confirmPassword: new FormControl('', [Validators.required, Validators.minLength(8), Validators.maxLength(255), PasswordConfirmationValidator.validate])
    })
  }

  register() {
    this.signUpErrorMessage = null; // reset, in case an error message was already displayed
    this.authenticationService.register(
      new User(this.form)
    ).subscribe(() => {
      console.info('Successful user sign-up');
      this.modalService.open(this.successModalContent, {backdrop: "static"}).result.then(() => {
        this.router.navigate(['login']);
      });
    }, error => {
      console.debug(`Error while registering the new user: ${error.message}`);
      switch (error.status) {
        case 409: {
          this.signUpErrorMessage = 'Name already taken! Please choose another';
          break;
        }
        default: {
          // TODO manage, maybe by redirecting on an error page
        }
      }
    });
  }

  goToLogin() {
    this.router.navigate(['login']);
  }

}
