import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs/Observable";
import {Activity} from "../../model/activity";

@Injectable()
export class ActivityService {

  private URL_PREFIX: string = "../script/activity";

  constructor(private http: HttpClient) { }

  createActivity(activity: Activity): Observable<Object> {
    return this.http.post(this.URL_PREFIX + "/createActivity", activity);
  }
}
