import {map} from 'rxjs/operators';
import {Injectable} from '@angular/core';
import {ActivatedRouteSnapshot, CanActivate, RouterStateSnapshot} from '@angular/router';
import {Observable} from 'rxjs';
import {PlayerService} from "../services/player/player.service";

@Injectable()
export class PlayerAlreadyCreatedGuard implements CanActivate {

  constructor(private playerService: PlayerService) {}

  canActivate(next: ActivatedRouteSnapshot,
              state: RouterStateSnapshot): Observable<boolean> | boolean {
    return this.playerService.getCurrentPlayer().pipe(map(player => player === null));
  }
}
