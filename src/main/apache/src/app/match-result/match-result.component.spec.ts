import {async, ComponentFixture, TestBed} from '@angular/core/testing';

import {MatchResultComponent} from './match-result.component';
import {FormGroup, ReactiveFormsModule} from "@angular/forms";
import {SetResultComponent} from "../set-result/set-result.component";
import {NgbPopoverModule} from "@ng-bootstrap/ng-bootstrap";

describe('MatchResultComponent', () => {
  let component: MatchResultComponent;
  let fixture: ComponentFixture<MatchResultComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      imports: [
        ReactiveFormsModule,
        NgbPopoverModule.forRoot()
      ],
      declarations: [ MatchResultComponent, SetResultComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(MatchResultComponent);
    component = fixture.componentInstance;
    component.match = new FormGroup({});
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
