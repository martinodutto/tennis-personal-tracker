import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {Activity} from '../../model/activity';
import {Player} from '../../model/player';

@Injectable({
  providedIn: 'root'
})
export class ActivityService {

  constructor(private http: HttpClient) {
  }

  getKnownPlayers(): Observable<Player[]> {
    return this.http.get<Player[]>('/activity/getKnownPlayers');
  }

  createActivity(activity: Activity): Observable<Object> {
    return this.http.post('/activity/createActivity', activity);
  }

  getUserClubs(userPlayerId: number): Observable<string[]> {
    return this.http.get<string[]>('/activity/getUserClubs', {
      params: {
        firstPlayerId: userPlayerId.toString()
      }
    });
  }
}
