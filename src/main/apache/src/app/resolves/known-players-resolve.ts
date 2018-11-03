import {ActivatedRouteSnapshot, Resolve, RouterStateSnapshot} from '@angular/router';
import {Player} from '../model/player';
import {Observable} from 'rxjs';
import {Injectable} from '@angular/core';
import {ActivityService} from '../services/activity/activity.service';

@Injectable()
export class KnownPlayersResolve implements Resolve<Player[]> {

  constructor(private activityService: ActivityService) {
  }

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<Player[]> | Promise<Player[]> | Player[] {
    // TODO manage errors
    return this.activityService.getKnownPlayers(); /*.catch(err => {
      console.error(`Unable to resolve data for transition to route ${state.url}: ${err.message}`);
      return Promise.resolve([]);
    });*/
  }
}
