import {async, ComponentFixture, TestBed} from '@angular/core/testing';

import {MatchResultComponent} from './match-result.component';
import {ReactiveFormsModule} from "@angular/forms";
import {SetResultComponent} from "../set-result/set-result.component";

describe('MatchResultComponent', () => {
  let component: MatchResultComponent;
  let fixture: ComponentFixture<MatchResultComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      imports: [
        ReactiveFormsModule
      ],
      declarations: [ MatchResultComponent, SetResultComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(MatchResultComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
