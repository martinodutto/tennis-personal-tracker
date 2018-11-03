import {map} from 'rxjs/operators';
import {Injectable} from '@angular/core';
import {ActivatedRouteSnapshot, CanDeactivate, RouterStateSnapshot} from '@angular/router';
import {Observable} from 'rxjs';
import {NewPlayerComponent} from "../new-player/new-player.component";
import {PlayerService} from "../services/player/player.service";
import {AuthenticationService} from "../services/authentication/authentication.service";

@Injectable()
export class PlayerNotCreatedGuard implements CanDeactivate<NewPlayerComponent> {

  constructor(private playerService: PlayerService,
              private authenticationService: AuthenticationService) {}

  canDeactivate(component: NewPlayerComponent,
                currentRoute: ActivatedRouteSnapshot,
                currentState: RouterStateSnapshot,
                nextState?: RouterStateSnapshot): Observable<boolean> | boolean {
    if (this.authenticationService.isSignedIn()) {
      return this.playerService.getCurrentPlayer().pipe(map(player => player != null));
    } else {
      // in case the user decides to logout before creating a player, the redirect to the login page is allowed
      return true;
    }
  }
}
