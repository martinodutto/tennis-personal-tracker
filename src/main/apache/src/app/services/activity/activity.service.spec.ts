import {inject, TestBed} from '@angular/core/testing';

import {ActivityService} from './activity.service';
import {HttpClientModule} from "@angular/common/http";

describe('ActivityService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [
        HttpClientModule
      ],
      providers: [ActivityService]
    });
  });

  it('should be created', inject([ActivityService], (service: ActivityService) => {
    expect(service).toBeTruthy();
  }));
});
