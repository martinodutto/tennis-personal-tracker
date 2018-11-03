import {async, ComponentFixture, TestBed} from '@angular/core/testing';

import {LoginComponent} from './login.component';
import {ReactiveFormsModule} from '@angular/forms';
import {Router} from '@angular/router';
import {NgbAlertModule} from '@ng-bootstrap/ng-bootstrap';
import {AuthenticationService} from '../services/authentication/authentication.service';
import {HttpClientTestingModule} from '@angular/common/http/testing';
import {PlayerService} from '../services/player/player.service';

describe('LoginComponent', () => {
  let component: LoginComponent;
  let fixture: ComponentFixture<LoginComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      imports: [
        HttpClientTestingModule,
        ReactiveFormsModule,
        NgbAlertModule
      ],
      declarations: [ LoginComponent ],
      providers: [
        AuthenticationService,
        PlayerService,
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
    fixture = TestBed.createComponent(LoginComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
