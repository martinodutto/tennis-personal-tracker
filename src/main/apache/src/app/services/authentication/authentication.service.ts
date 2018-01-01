import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs/Observable";
import {User} from "../../model/user";

@Injectable()
export class AuthenticationService {

  private URL_PREFIX: string = "../script/authentication";

  constructor(private http: HttpClient) { }

  register(user: User): Observable<any> {
    return this.http.post(this.URL_PREFIX + "/register", user);
  }
}
