import {async, ComponentFixture, TestBed} from '@angular/core/testing';

import {NewActivityComponent} from './new-activity.component';
import {FormGroup, ReactiveFormsModule} from "@angular/forms";
import {
  NgbAlertModule,
  NgbDatepickerModule,
  NgbModalModule,
  NgbPopoverModule,
  NgbTimepickerModule,
  NgbTypeaheadModule
} from "@ng-bootstrap/ng-bootstrap";
import {MatchResultComponent} from "../match-result/match-result.component";
import {SetResultComponent} from "../set-result/set-result.component";
import {ActivityService} from "../services/activity/activity.service";
import {ActivatedRoute, Router} from "@angular/router";
import {TimeFormatService} from "../services/time-format/time-format.service";
import {PlayerService} from "../services/player/player.service";
import {Guest, Player} from "../model/player";
import {HttpClientTestingModule} from "@angular/common/http/testing";
import {AuthenticationService} from "../services/authentication/authentication.service";

describe('NewActivityComponent', () => {
  let component: NewActivityComponent;
  let fixture: ComponentFixture<NewActivityComponent>;
  // TODO bad
  let mockPlayer: Player = new Player(new FormGroup({}), Guest.N);
  mockPlayer.playerId = 0;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      imports: [
        ReactiveFormsModule,
        NgbDatepickerModule.forRoot(),
        NgbTimepickerModule.forRoot(),
        NgbPopoverModule.forRoot(),
        NgbModalModule.forRoot(),
        NgbAlertModule.forRoot(),
        NgbTypeaheadModule.forRoot(),
        HttpClientTestingModule
      ],
      declarations: [
        NewActivityComponent,
        MatchResultComponent,
        SetResultComponent
      ],
      providers: [
        TimeFormatService,
        ActivityService,
        PlayerService,
        AuthenticationService,
        {
          provide: Router, useClass: class {
            navigate = jasmine.createSpy("navigate");
          }
        },
        {
          provide: ActivatedRoute,
          useValue: {
            snapshot: {
              data: {
                currentPlayer: mockPlayer,
                knownPlayers: [
                  new Player(new FormGroup({}), Guest.Y)
                ]
              }
            }
          }
        }
      ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(NewActivityComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
