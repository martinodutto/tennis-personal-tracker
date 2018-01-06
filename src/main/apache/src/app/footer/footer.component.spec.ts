import {async, ComponentFixture, TestBed} from '@angular/core/testing';

import {FooterComponent} from './footer.component';
import {Router} from "@angular/router";
import {AuthenticationService} from "../services/authentication/authentication.service";
import {HttpClientTestingModule} from "@angular/common/http/testing";
import {Subject} from "rxjs/Subject";

describe('FooterComponent', () => {
  let component: FooterComponent;
  let fixture: ComponentFixture<FooterComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      imports: [
        HttpClientTestingModule
      ],
      declarations: [ FooterComponent ],
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
    fixture = TestBed.createComponent(FooterComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
