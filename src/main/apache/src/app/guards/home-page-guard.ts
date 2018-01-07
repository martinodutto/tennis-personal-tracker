import {Injectable} from '@angular/core';
import {ActivatedRouteSnapshot, CanActivate, Router, RouterStateSnapshot} from "@angular/router";
import {Observable} from "rxjs/Observable";
import {PlayerService} from "../services/player/player.service";
import {AuthenticationService} from "../services/authentication/authentication.service";

@Injectable()
export class HomePageGuard implements CanActivate {

  constructor(private router: Router,
              private playerService: PlayerService,
              private authenticationService: AuthenticationService) {
  }

  // this lets us access the home page only when the user's player has been created. In the other case, it redirects us to the new player configuration page
  canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<boolean> | Promise<boolean> | boolean {
    return this.playerService.getCurrentPlayer().map(player => {
      if (player === null) {
        this.router.navigate(['newplayer']);
      } else {
        // this is a good place to store player information, because we have it and we must pass through this guard when we end up signing-in or signing-up
        this.authenticationService.savePlayerNameAndSurname(player.name, player.surname);
      }
      return player !== null;
    }).toPromise().catch(err => {
      console.error(`Unable to resolve data for transition to route ${state.url}: ${err.message}`);
      return Promise.resolve(false);
    });
  }

}
