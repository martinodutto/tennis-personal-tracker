import {async, ComponentFixture, TestBed} from '@angular/core/testing';

import {HeaderComponent} from './header.component';
import {Router} from "@angular/router";
import {AuthenticationService} from "../services/authentication/authentication.service";
import {HttpClientTestingModule} from "@angular/common/http/testing";
import {Subject} from "rxjs/Subject";

describe('HeaderComponent', () => {
  let component: HeaderComponent;
  let fixture: ComponentFixture<HeaderComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      imports: [
        HttpClientTestingModule
      ],
      declarations: [HeaderComponent],
      providers: [
        AuthenticationService,
        {
          provide: Router, useClass: class {
            navigate = jasmine.createSpy("navigate");
            events = new Subject();
          }
        }
      ]
    })
      .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(HeaderComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
