import {async, ComponentFixture, TestBed} from '@angular/core/testing';

import {NewPlayerComponent} from './new-player.component';
import {ReactiveFormsModule} from '@angular/forms';
import {PlayerService} from '../services/player/player.service';
import {Router} from '@angular/router';
import {HttpClientTestingModule} from '@angular/common/http/testing';
import {AuthenticationService} from '../services/authentication/authentication.service';

describe('NewPlayerComponent', () => {
  let component: NewPlayerComponent;
  let fixture: ComponentFixture<NewPlayerComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      imports: [
        ReactiveFormsModule,
        HttpClientTestingModule
      ],
      declarations: [
        NewPlayerComponent
      ],
      providers: [
        PlayerService,
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
    fixture = TestBed.createComponent(NewPlayerComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
