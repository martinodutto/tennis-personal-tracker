import {async, ComponentFixture, TestBed} from '@angular/core/testing';

import {SetResultComponent} from './set-result.component';
import {ReactiveFormsModule} from "@angular/forms";

describe('SetResultComponent', () => {
  let component: SetResultComponent;
  let fixture: ComponentFixture<SetResultComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      imports: [
        ReactiveFormsModule
      ],
      declarations: [ SetResultComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(SetResultComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
