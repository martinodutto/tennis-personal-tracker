import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {DataTableParams} from "angular5-data-table";
import {ActivityAndResult} from "../../model/activity-and-result";
import {Observable} from "rxjs";
import {PaginatedResponse} from "../../model/paginated-response";

@Injectable()
export class ActivitiesAndResultsService {

  private URL_PREFIX: string = "../script/activitiesAndResults";

  constructor(private http: HttpClient) { }

  getPastActivities(params: DataTableParams): Observable<PaginatedResponse<ActivityAndResult>> {
    return this.http.get<PaginatedResponse<ActivityAndResult>>(this.URL_PREFIX + "/getPastActivities", {
      params: params
    });
  }
}
