import {Observable} from 'rxjs';
import {Injectable} from '@angular/core';
import {ActivatedRouteSnapshot, Resolve, RouterStateSnapshot} from '@angular/router';
import {Player} from '../model/player';


import {PlayerService} from '../services/player/player.service';

/**
 * Resolves the current user's player data, in a way that it can be used while transitioning to new routes.
 */
@Injectable()
export class CurrentPlayerResolve implements Resolve<Player> {

  constructor(private playerService: PlayerService) {
  }

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<Player> | Player {
    // TODO manage errors here
    return this.playerService.getCurrentPlayer(); /*.pipe(catchError(err => {
      console.error(`Unable to resolve data for transition to route ${state.url}: ${err.message}`);
      return observableEmpty<Player>();
    }));*/
  }
}
