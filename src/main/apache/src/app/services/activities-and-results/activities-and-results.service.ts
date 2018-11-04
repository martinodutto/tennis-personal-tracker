import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {ActivityAndResult} from '../../model/activity-and-result';
import {Observable} from 'rxjs';
import {PaginatedResponse} from '../../model/paginated-response';
import {IGetRowsParams} from 'ag-grid-community';

@Injectable({
  providedIn: 'root'
})
export class ActivitiesAndResultsService {

  private URL_PREFIX = '../script/activitiesAndResults';

  constructor(private http: HttpClient) { }

  getPastActivities(params: IGetRowsParams): Observable<PaginatedResponse<ActivityAndResult>> {
    return this.http.post<PaginatedResponse<ActivityAndResult>>(this.URL_PREFIX + '/getPastActivities', params);
  }
}
