import {Component, OnInit, ViewChild} from '@angular/core';
import {FormBuilder, FormControl, FormGroup, Validators} from '@angular/forms';
import {Router} from '@angular/router';
import {AuthenticationService} from '../services/authentication/authentication.service';
import {PasswordConfirmationValidator} from '../validators/password-confirmation-validator/password-confirmation-validator';
import {ChangePassword} from '../model/change-password';
import {HttpErrorResponse} from '@angular/common/http';
import {NgbModal} from '@ng-bootstrap/ng-bootstrap';

@Component({
  selector: 'app-change-password',
  templateUrl: './change-password.component.html',
  styleUrls: ['./change-password.component.scss']
})
export class ChangePasswordComponent implements OnInit {

  currentUserName: string;
  form: FormGroup;
  newPasswordsForm: FormGroup;
  changePasswordErrorMessage: string;
  @ViewChild('successModalContent') successModalContent: any;

  constructor(private formBuilder: FormBuilder,
              private router: Router,
              private authenticationService: AuthenticationService,
              private modalService: NgbModal) { }

  ngOnInit() {
    this.currentUserName = this.authenticationService.getLoggedUserInfos().userName;
    this.newPasswordsForm = this.formBuilder.group({
      password: new FormControl('', [Validators.required, Validators.minLength(8), Validators.maxLength(255)]),
      confirmPassword: new FormControl('', [Validators.required, Validators.minLength(8), Validators.maxLength(255)])
    }, {
      validator: PasswordConfirmationValidator.validate
    });
    this.form = this.formBuilder.group({
      oldPassword: new FormControl('', [Validators.required, Validators.minLength(8), Validators.maxLength(255)]),
      newPasswords: this.newPasswordsForm
    });
  }

  changePassword() {
    this.authenticationService.changePassword(
      new ChangePassword(this.form, this.newPasswordsForm)
    ).subscribe(() => {
      console.info('Successful password change');
      this.authenticationService.logout();
      this.modalService.open(this.successModalContent, {backdrop: 'static'}).result.then(() => {
        this.router.navigate(['login']);
      });
    }, (error: HttpErrorResponse) => {
      console.debug(`Error while changing password: ${error.message}`);
      switch (error.status) {
        case 403: {
          this.changePasswordErrorMessage = 'An invalid current password was entered';
          break;
        }
        default: {
          this.changePasswordErrorMessage = 'Error while changing password';
        }
      }
    });
  }

  goToHome(event: MouseEvent) {
    event.preventDefault();
    this.router.navigate(['home']);
  }
}
