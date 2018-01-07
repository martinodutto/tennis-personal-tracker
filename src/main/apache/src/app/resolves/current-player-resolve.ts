import {Injectable} from "@angular/core";
import {ActivatedRouteSnapshot, Resolve, RouterStateSnapshot} from "@angular/router";
import {Player} from "../model/player";
import {Observable} from "rxjs/Observable";
import 'rxjs/add/observable/empty';
import 'rxjs/add/operator/catch';
import {PlayerService} from "../services/player/player.service";

/**
 * Resolves the current user's player data, in a way that it can be used while transitioning to new routes.
 */
@Injectable()
export class CurrentPlayerResolve implements Resolve<Player> {

  constructor(private playerService: PlayerService) {
  }

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<Player> | Promise<Player> | Player {
    return this.playerService.getCurrentPlayer().catch(err => {
      console.error(`Unable to resolve data for transition to route ${state.url}: ${err.message}`);
      return Observable.empty<Player>();
    });
  }
}
