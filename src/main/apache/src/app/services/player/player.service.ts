import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Player} from '../../model/player';
import {Observable} from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class PlayerService {

  constructor(private http: HttpClient) {
  }

  createPlayer(player: Player): Observable<Player> {
    return this.http.post<Player>('/player/createPlayer', player);
  }

  getCurrentPlayer(): Observable<Player> {
    return this.http.get<Player>('/player/getCurrentUserPlayer');
  }
}
