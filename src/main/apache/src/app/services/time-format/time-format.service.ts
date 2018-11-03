import {Injectable} from '@angular/core';
import {NgbTimeStruct} from '@ng-bootstrap/ng-bootstrap';

@Injectable({
  providedIn: 'root'
})
export class TimeFormatService {

  constructor() {
  }

  private static padNumber(n: number, length: number): string {
    return (n ? n.toString() : '').padStart(length, '0');
  }

  public format(time: NgbTimeStruct): string {
    let formattedTime: string = null;
    if (time) {
      formattedTime = `${TimeFormatService.padNumber(time.hour, 2) || '00'}:` +
        `${TimeFormatService.padNumber(time.minute, 2) || '00'}:` +
        `${TimeFormatService.padNumber(time.second, 2) || '00'}`;
    }
    return formattedTime;
  }
}
