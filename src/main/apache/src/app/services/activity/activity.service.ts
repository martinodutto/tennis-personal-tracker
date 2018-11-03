import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {Activity} from '../../model/activity';
import {Player} from '../../model/player';

@Injectable()
export class ActivityService {

  private URL_PREFIX = '../script/activity';

  constructor(private http: HttpClient) { }

  getKnownPlayers(): Observable<Player[]> {
    return this.http.get<Player[]>(this.URL_PREFIX + '/getKnownPlayers');
  }

  createActivity(activity: Activity): Observable<Object> {
    return this.http.post(this.URL_PREFIX + '/createActivity', activity);
  }

  getUserClubs(userPlayerId: number): Observable<string[]> {
    return this.http.get<string[]>(this.URL_PREFIX + '/getUserClubs', {
      params: {
        firstPlayerId: userPlayerId.toString()
      }
    });
  }
}
