import {Injectable} from "@angular/core";
import {ActivatedRouteSnapshot, Resolve, RouterStateSnapshot} from "@angular/router";
import {Player} from "../model/player";
import {ActivityService} from "../services/activity/activity.service";
import {Observable} from "rxjs/Observable";
import 'rxjs/add/observable/empty';

/**
 * Resolves the current user's player data, in a way that it can be used while transitioning to new routes.
 */
@Injectable()
export class CurrentPlayerResolve implements Resolve<Player> {

  constructor(private activityService: ActivityService) {
  }

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<Player> | Promise<Player> | Player {
    return this.activityService.getCurrentPlayer();
  }
}
