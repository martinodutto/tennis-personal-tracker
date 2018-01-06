import {async, ComponentFixture, TestBed} from '@angular/core/testing';

import {UnrecoverableErrorComponent} from './unrecoverable-error.component';

describe('UnrecoverableErrorComponent', () => {
  let component: UnrecoverableErrorComponent;
  let fixture: ComponentFixture<UnrecoverableErrorComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ UnrecoverableErrorComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(UnrecoverableErrorComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
