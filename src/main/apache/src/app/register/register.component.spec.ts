import {async, ComponentFixture, TestBed} from '@angular/core/testing';

import {RegisterComponent} from './register.component';
import {ReactiveFormsModule} from '@angular/forms';
import {Router} from '@angular/router';
import {AuthenticationService} from '../services/authentication/authentication.service';
import {HttpClientTestingModule} from '@angular/common/http/testing';
import {NgbAlertModule, NgbModalModule} from '@ng-bootstrap/ng-bootstrap';

describe('RegisterComponent', () => {
  let component: RegisterComponent;
  let fixture: ComponentFixture<RegisterComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      imports: [
        ReactiveFormsModule,
        HttpClientTestingModule,
        NgbAlertModule,
        NgbModalModule
      ],
      declarations: [ RegisterComponent ],
      providers: [
        AuthenticationService,
        {
          provide: Router, useClass: class {
            navigate = jasmine.createSpy('navigate');
          }
        }
      ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(RegisterComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
