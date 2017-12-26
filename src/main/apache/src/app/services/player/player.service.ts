import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Player} from "../../model/player";
import {Observable} from "rxjs/Observable";

@Injectable()
export class PlayerService {

  private URL_PREFIX: string = "../script/player";

  constructor(private http: HttpClient) { }

  createPlayer(player: Player): Observable<Object> {
    return this.http.post(this.URL_PREFIX + "/createPlayer", player);
  }
}
