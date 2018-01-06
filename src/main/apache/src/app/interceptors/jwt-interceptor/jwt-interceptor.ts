import {Injectable} from '@angular/core';
import {HttpErrorResponse, HttpEvent, HttpHandler, HttpInterceptor, HttpRequest} from "@angular/common/http";
import {Observable} from "rxjs/Observable";
import 'rxjs/add/operator/do';
import {Router} from "@angular/router";

@Injectable()
export class JwtInterceptor implements HttpInterceptor {

  constructor(private router: Router) {}

  intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    // add authorization header with JWT token, if available
    let jwt = localStorage.getItem('jwt');
    if (jwt) {
      req = req.clone({
        setHeaders: {
          Authorization: `Bearer ${jwt}`
        }
      });
    }

    return next.handle(req).do(() => {
      // nothing to do
    }, (err: any) => {
      if (err instanceof HttpErrorResponse) {
        // managing the unauthorized case (e.g. when the token expires or when the login fails)
        if (err.status === 401) {
          localStorage.clear(); // removed JWT token, in order to let the login page be reached
          this.router.navigate(['login']);
        } else if (err.status === 503) {
          this.router.navigate(['serviceunavailable']);
        }
      }
    });
  }

}
