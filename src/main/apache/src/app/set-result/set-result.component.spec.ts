import {async, ComponentFixture, TestBed} from '@angular/core/testing';

import {SetResultComponent} from './set-result.component';
import {FormControl, FormGroup, ReactiveFormsModule} from '@angular/forms';
import {NgbPopoverModule} from '@ng-bootstrap/ng-bootstrap';

describe('SetResultComponent', () => {
  let component: SetResultComponent;
  let fixture: ComponentFixture<SetResultComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      imports: [
        ReactiveFormsModule,
        NgbPopoverModule
      ],
      declarations: [ SetResultComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(SetResultComponent);
    component = fixture.componentInstance;
    // manually initialized because it's an @Input()
    component.set = new FormGroup({
      firstPlayerGames: new FormControl(0),
      secondPlayerGames: new FormControl(0)
    });
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
