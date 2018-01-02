import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs/Observable";
import {User} from "../../model/user";

@Injectable()
export class AuthenticationService {

  private URL_PREFIX: string = "../script/authentication";
  private JWT_TOKEN_PARAM_NAME = "jwt";

  constructor(private http: HttpClient) {
  }

  saveJwtToken(token: string): void {
    localStorage.setItem(this.JWT_TOKEN_PARAM_NAME, token);
  }

  getJwtToken(): string {
    return localStorage.getItem(this.JWT_TOKEN_PARAM_NAME);
  }

  register(user: User): Observable<any> {
    return this.http.post(this.URL_PREFIX + "/register", AuthenticationService.encodeToBase64(user));
  }

  login(user: User): Observable<any> {
    return this.http.post(this.URL_PREFIX + "/login", AuthenticationService.encodeToBase64(user));
  }

  logout(): void {
    localStorage.removeItem(this.JWT_TOKEN_PARAM_NAME);
  }

  isSignedIn(): boolean {
    return this.getJwtToken() !== null;
  }

  private static encodeToBase64(user: User): User {
    user.username = this.b64EncodeUnicode(user.username);
    user.password = this.b64EncodeUnicode(user.password);

    return user;
  }

  /**
   * Function to convert from UTF8 to Base64 solving the Unicode Problem
   * Requires: window.btoa and window.encodeURIComponent functions
   * More info: http://stackoverflow.com/questions/30106476/using-javascripts-atob-to-decode-base64-doesnt-properly-decode-utf-8-strings
   * Samples:
   *      b64EncodeUnicode('✓ à la mode'); // "4pyTIMOgIGxhIG1vZGU="
   *      b64EncodeUnicode('\n'); // "Cg=="
   */
  private static b64EncodeUnicode(str: string): string {
    if (window
      && "btoa" in window
      && "encodeURIComponent" in window) {
      return btoa(encodeURIComponent(str).replace(/%([0-9A-F]{2})/g, (match, p1) => {
        return String.fromCharCode(("0x" + p1) as any);
      }));
    } else {
      console.warn("b64EncodeUnicode requirements: window.btoa and window.encodeURIComponent functions");
      return null;
    }

  }
}
