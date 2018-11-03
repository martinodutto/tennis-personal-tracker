import {inject, TestBed} from '@angular/core/testing';

import {ActivityService} from './activity.service';
import {HttpClientTestingModule} from '@angular/common/http/testing';

describe('ActivityService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [
        HttpClientTestingModule
      ],
      providers: [ActivityService]
    });
  });

  it('should be created', inject([ActivityService], (service: ActivityService) => {
    expect(service).toBeTruthy();
  }));
});
