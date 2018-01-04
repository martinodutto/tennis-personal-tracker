import {Injectable} from '@angular/core';
import {ActivatedRouteSnapshot, CanActivate, Router, RouterStateSnapshot} from "@angular/router";
import {Observable} from "rxjs/Observable";
import {ActivityService} from "../services/activity/activity.service";

@Injectable()
export class HomePageGuard implements CanActivate {

  constructor(private router: Router,
              private activityService: ActivityService) {
  }

  // this lets us access the home page only when the user's player has been created. In the other case, it redirects us to the new player configuration page
  canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<boolean> | Promise<boolean> | boolean {
    return this.activityService.getCurrentPlayer().map(player => {
      if (player === null) {
        this.router.navigate(['newplayer']);
      }
      return player !== null;
    }).toPromise();
  }

}
