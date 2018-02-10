import {inject, TestBed} from '@angular/core/testing';

import {ActivitiesAndResultsService} from './activities-and-results.service';
import {HttpClientTestingModule} from "@angular/common/http/testing";

describe('ActivitiesAndResultsService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [
        HttpClientTestingModule
      ],
      providers: [ActivitiesAndResultsService]
    });
  });

  it('should be created', inject([ActivitiesAndResultsService], (service: ActivitiesAndResultsService) => {
    expect(service).toBeTruthy();
  }));
});
