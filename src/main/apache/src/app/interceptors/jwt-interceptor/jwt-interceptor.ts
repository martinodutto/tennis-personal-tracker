import {Injectable} from '@angular/core';
import {HttpEvent, HttpHandler, HttpInterceptor, HttpRequest} from "@angular/common/http";
import {Observable} from "rxjs/Observable";

@Injectable()
export class JwtInterceptor implements HttpInterceptor {

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

    return next.handle(req);
  }

}
