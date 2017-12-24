import {async, ComponentFixture, TestBed} from '@angular/core/testing';

import {NewActivityComponent} from './new-activity.component';
import {ReactiveFormsModule} from "@angular/forms";
import {NgbDatepickerModule} from "@ng-bootstrap/ng-bootstrap";

describe('NewActivityComponent', () => {
  let component: NewActivityComponent;
  let fixture: ComponentFixture<NewActivityComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      imports: [
        ReactiveFormsModule,
        NgbDatepickerModule.forRoot()
      ],
      declarations: [ NewActivityComponent ]
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
