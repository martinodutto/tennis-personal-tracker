import {HttpEvent, HttpHandler, HttpInterceptor, HttpRequest} from '@angular/common/http';
import {Inject, Injectable} from '@angular/core';
import {Observable} from 'rxjs';

@Injectable()
export class BaseInterceptor implements HttpInterceptor {

  constructor(@Inject('BASE_API_URL') private baseApiUrl: string) {
  }

  intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    const apiReq = req.clone({
      url: this.baseApiUrl + req.url
    });
    return next.handle(apiReq);
  }
}
