import {Injectable} from '@angular/core';
import {NgbTimeStruct} from '@ng-bootstrap/ng-bootstrap';
import {DateFormatService} from '../date-format/date-format.service';

@Injectable({
  providedIn: 'root'
})
export class TimeFormatService {

  constructor() {
  }

  public format(time: NgbTimeStruct): string {
    let formattedTime: string = null;
    if (time) {
      formattedTime = `${DateFormatService.padNumber(time.hour, 2) || '00'}:` +
        `${DateFormatService.padNumber(time.minute, 2) || '00'}:` +
        `${DateFormatService.padNumber(time.second, 2) || '00'}`;
    }
    return formattedTime;
  }
}
