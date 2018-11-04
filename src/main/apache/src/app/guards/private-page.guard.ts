import {Injectable} from '@angular/core';
import {AuthenticationService} from '../services/authentication/authentication.service';
import {ActivatedRouteSnapshot, CanActivate, Router, RouterStateSnapshot} from '@angular/router';
import {Observable} from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class PrivatePageGuard implements CanActivate {

  constructor(private router: Router,
              private authenticationService: AuthenticationService) { }

  canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<boolean> | boolean {
    if (!this.authenticationService.isSignedIn()) {
      this.router.navigate(['login']);
    }
    return this.authenticationService.isSignedIn();
  }

}
