import {Injectable} from '@angular/core';
import {NgbDateStruct} from '@ng-bootstrap/ng-bootstrap';

@Injectable({
  providedIn: 'root'
})
export class DateFormatService {

  constructor() { }

  public isoFormat(date: NgbDateStruct): string {
    let formattedDate: string = null;
    if (date) {
      formattedDate = `${date.year}-${date.month}-${date.day}`;
    }
    return formattedDate;
  }
}
