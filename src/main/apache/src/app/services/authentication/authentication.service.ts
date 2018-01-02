import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs/Observable";
import {User} from "../../model/user";

@Injectable()
export class AuthenticationService {

  private URL_PREFIX: string = "../script/authentication";
  private JWT_TOKEN_PARAM_NAME = "jwt";

  constructor(private http: HttpClient) { }

  saveJwtToken(token: string): void {
    localStorage.setItem(this.JWT_TOKEN_PARAM_NAME, token);
  }

  getJwtToken(): string {
    return localStorage.getItem(this.JWT_TOKEN_PARAM_NAME);
  }

  register(user: User): Observable<any> {
    return this.http.post(this.URL_PREFIX + "/register", this.encodeToBase64(user));
  }

  login(user: User): Observable<any> {
    return this.http.post(this.URL_PREFIX + "/login", this.encodeToBase64(user));
  }

  logout(): void {
    localStorage.removeItem(this.JWT_TOKEN_PARAM_NAME);
  }

  isSignedIn(): boolean {
    return this.getJwtToken() !== null;
  }

  private encodeToBase64(user: User): User {
    user.username = btoa(user.username);
    user.password = btoa(user.password);

    return user;
  }
}
