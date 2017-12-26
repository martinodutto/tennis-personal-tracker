import {inject, TestBed} from '@angular/core/testing';

import {PlayerService} from './player.service';
import {HttpClientModule} from "@angular/common/http";

describe('PlayerService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [
        HttpClientModule
      ],
      providers: [
        PlayerService
      ]
    });
  });

  it('should be created', inject([PlayerService], (service: PlayerService) => {
    expect(service).toBeTruthy();
  }));
});
