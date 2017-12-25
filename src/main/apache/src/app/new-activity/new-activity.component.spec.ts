import {async, ComponentFixture, TestBed} from '@angular/core/testing';

import {NewActivityComponent} from './new-activity.component';
import {ReactiveFormsModule} from "@angular/forms";
import {NgbDatepickerModule, NgbModalModule, NgbPopoverModule, NgbTimepickerModule} from "@ng-bootstrap/ng-bootstrap";
import {MatchResultComponent} from "../match-result/match-result.component";
import {SetResultComponent} from "../set-result/set-result.component";
import {ActivityService} from "../services/activity/activity.service";
import {HttpClientModule} from "@angular/common/http";
import {Router} from "@angular/router";
import {TimeFormatService} from "../services/time-format/time-format.service";

describe('NewActivityComponent', () => {
  let component: NewActivityComponent;
  let fixture: ComponentFixture<NewActivityComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      imports: [
        ReactiveFormsModule,
        NgbDatepickerModule.forRoot(),
        NgbTimepickerModule.forRoot(),
        NgbPopoverModule.forRoot(),
        NgbModalModule.forRoot(),
        HttpClientModule
      ],
      declarations: [
        NewActivityComponent,
        MatchResultComponent,
        SetResultComponent
      ],
      providers: [
        TimeFormatService,
        ActivityService,
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
    fixture = TestBed.createComponent(NewActivityComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
