import {Injectable} from '@angular/core';
import {NgbTimeStruct} from "@ng-bootstrap/ng-bootstrap";

@Injectable()
export class TimeFormatService {

  constructor() { }

  public format(time: NgbTimeStruct): string {
    let formattedTime: string = null;
    if (time) {
      // TODO solve NgBootstrap import problem
      // formattedTime = `${padNumber(time.hour) || '00'}:${padNumber(time.minute) || '00'}:${padNumber(time.second) || '00'}`;
    }
    return formattedTime;
  }
}
