import {inject, TestBed} from '@angular/core/testing';

import {TimeFormatService} from './time-format.service';
import {NgbTimeStruct} from '@ng-bootstrap/ng-bootstrap';

describe('TimeFormatService', () => {

  let testTime: NgbTimeStruct;

  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [TimeFormatService]
    });
    testTime = {
      hour: 0,
      minute: 0,
      second: 0
    };
  });

  it('should be created', inject([TimeFormatService], (service: TimeFormatService) => {
    expect(service).toBeTruthy();
  }));

  it('formats correctly 12:21:56', inject([TimeFormatService], (service: TimeFormatService) => {
    testTime.hour = 12;
    testTime.minute = 21;
    testTime.second = 56;
    expect(service.format(testTime)).toEqual('12:21:56');
  }));

  it('formats correctly 01:21:56', inject([TimeFormatService], (service: TimeFormatService) => {
    testTime.hour = 1;
    testTime.minute = 21;
    testTime.second = 56;
    expect(service.format(testTime)).toEqual('01:21:56');
  }));

  it('formats correctly 11:08:56', inject([TimeFormatService], (service: TimeFormatService) => {
    testTime.hour = 11;
    testTime.minute = 8;
    testTime.second = 56;
    expect(service.format(testTime)).toEqual('11:08:56');
  }));

  it('formats correctly 11:28:03', inject([TimeFormatService], (service: TimeFormatService) => {
    testTime.hour = 11;
    testTime.minute = 28;
    testTime.second = 3;
    expect(service.format(testTime)).toEqual('11:28:03');
  }));

  it('handles a null input by returning null', inject([TimeFormatService], (service: TimeFormatService) => {
    expect(service.format(null)).toBeNull();
  }));

  it('treats a null hour as 00', inject([TimeFormatService], (service: TimeFormatService) => {
    testTime.hour = null;
    testTime.minute = 21;
    testTime.second = 56;
    expect(service.format(testTime)).toEqual('00:21:56');
  }));

  it('treats a null minute as 00', inject([TimeFormatService], (service: TimeFormatService) => {
    testTime.hour = 23;
    testTime.minute = null;
    testTime.second = 56;
    expect(service.format(testTime)).toEqual('23:00:56');
  }));

  it('treats a null second as 00', inject([TimeFormatService], (service: TimeFormatService) => {
    testTime.hour = 23;
    testTime.minute = 31;
    testTime.second = null;
    expect(service.format(testTime)).toEqual('23:31:00');
  }));
});
