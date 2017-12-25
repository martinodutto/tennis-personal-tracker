import {Injectable} from '@angular/core';
import {NgbTimeStruct} from "@ng-bootstrap/ng-bootstrap";
import {padNumber} from "@ng-bootstrap/ng-bootstrap/util/util";

@Injectable()
export class TimeFormatService {

  constructor() { }

  public format(time: NgbTimeStruct): string {
    let formattedTime: string = null;
    if (time) {
      formattedTime = `${padNumber(time.hour) || '00'}:${padNumber(time.minute) || '00'}:${padNumber(time.second) || '00'}`;
    }
    return formattedTime;
  }
}
