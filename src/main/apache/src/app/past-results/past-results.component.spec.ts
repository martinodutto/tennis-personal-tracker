import {async, ComponentFixture, TestBed} from '@angular/core/testing';

import {PastResultsComponent} from './past-results.component';
import {NgbAlertModule} from '@ng-bootstrap/ng-bootstrap';
import {DataTableModule} from 'angular5-data-table';
import {ActivitiesAndResultsService} from '../services/activities-and-results/activities-and-results.service';
import {HttpClientTestingModule} from '@angular/common/http/testing';

describe('PastResultsComponent', () => {
  let component: PastResultsComponent;
  let fixture: ComponentFixture<PastResultsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      imports: [
        DataTableModule,
        NgbAlertModule,
        HttpClientTestingModule
      ],
      declarations: [
        PastResultsComponent
      ],
      providers: [
        ActivitiesAndResultsService
      ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(PastResultsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
