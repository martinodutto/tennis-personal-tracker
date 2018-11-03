import {Injectable} from '@angular/core';
import {AuthenticationService} from '../services/authentication/authentication.service';
import {ActivatedRouteSnapshot, CanActivate, Router, RouterStateSnapshot} from '@angular/router';
import {Observable} from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class LoginPageGuard implements CanActivate {

  constructor(private router: Router,
              private authenticationService: AuthenticationService) { }

  // this ensures that we can access the login page only when not logged in
  canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<boolean> | boolean {
    if (this.authenticationService.isSignedIn()) {
      this.router.navigate(['home']);
    }
    return !this.authenticationService.isSignedIn();
  }

}
