import {async, ComponentFixture, TestBed} from '@angular/core/testing';

import {ChangePasswordComponent} from './change-password.component';
import {NgbAlertModule, NgbModalModule} from "@ng-bootstrap/ng-bootstrap";
import {ReactiveFormsModule} from "@angular/forms";
import {Router} from "@angular/router";
import {AuthenticationService} from "../services/authentication/authentication.service";
import {HttpClientTestingModule} from "@angular/common/http/testing";

describe('ChangePasswordComponent', () => {
  let component: ChangePasswordComponent;
  let fixture: ComponentFixture<ChangePasswordComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      imports: [
        ReactiveFormsModule,
        HttpClientTestingModule,
        NgbAlertModule.forRoot(),
        NgbModalModule.forRoot()
      ],
      declarations: [ ChangePasswordComponent ],
      providers: [
        AuthenticationService,
        {
          provide: Router, useClass: class {
            navigate = jasmine.createSpy("navigate");
          }
        }
      ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ChangePasswordComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
