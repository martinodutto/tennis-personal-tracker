import {async, ComponentFixture, TestBed} from '@angular/core/testing';

import {NewPlayerComponent} from './new-player.component';
import {NgbModalModule} from "@ng-bootstrap/ng-bootstrap";
import {HttpClientModule} from "@angular/common/http";
import {ReactiveFormsModule} from "@angular/forms";
import {PlayerService} from "../services/player/player.service";
import {Router} from "@angular/router";

describe('NewPlayerComponent', () => {
  let component: NewPlayerComponent;
  let fixture: ComponentFixture<NewPlayerComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      imports: [
        ReactiveFormsModule,
        NgbModalModule.forRoot(),
        HttpClientModule
      ],
      declarations: [
        NewPlayerComponent
      ],
      providers: [
        PlayerService,
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
    fixture = TestBed.createComponent(NewPlayerComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
