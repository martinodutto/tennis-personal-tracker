import {Injectable} from '@angular/core';
import {NgbDateStruct} from '@ng-bootstrap/ng-bootstrap';

@Injectable({
  providedIn: 'root'
})
export class DateFormatService {

  constructor() { }

  public static padNumber(n: number, length: number): string {
    return (n ? n.toString() : '').padStart(length, '0');
  }

  public isoFormat(date: NgbDateStruct): string {
    let formattedDate: string = null;
    if (date) {
      formattedDate = `${DateFormatService.padNumber(date.year, 4)}-` +
        `${DateFormatService.padNumber(date.month, 2)}-${DateFormatService.padNumber(date.day, 2)}`;
    }
    return formattedDate;
  }
}
