import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {User} from '../../model/user';
import {LoggedUser} from '../../model/logged-user';
import {ChangePassword} from '../../model/change-password';

@Injectable({
  providedIn: 'root'
})
export class AuthenticationService {

  constructor(private http: HttpClient) {
  }

  private readonly JWT_TOKEN_PARAM_NAME = 'jwt';
  private readonly USER_NAME_PARAM_NAME = 'username';
  private readonly PLAYER_NAME_PARAM_NAME = 'playername';
  private readonly PLAYER_SURNAME_PARAM_NAME = 'playersurname';

  // careful: this method operates destructively on the passed object!
  private static encodeUserToBase64(user: User): User {
    user.username = this.b64EncodeUnicode(user.username);
    user.password = this.b64EncodeUnicode(user.password);

    return user;
  }

  // careful: this method operates destructively on the passed object!
  private static encodeChangePasswordToBase64(changePassword: ChangePassword): ChangePassword {
    changePassword.oldPassword = this.b64EncodeUnicode(changePassword.oldPassword);
    changePassword.newPassword = this.b64EncodeUnicode(changePassword.newPassword);

    return changePassword;
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
      && 'btoa' in window
      && 'encodeURIComponent' in window) {
      return btoa(encodeURIComponent(str).replace(/%([0-9A-F]{2})/g, (match, p1) => {
        return String.fromCharCode(('0x' + p1) as any);
      }));
    } else {
      console.warn('b64EncodeUnicode requirements: window.btoa and window.encodeURIComponent functions');
      return null;
    }

  }

  saveJwtTokenAndUsername(token: string, username: string): void {
    localStorage.setItem(this.JWT_TOKEN_PARAM_NAME, token);
    localStorage.setItem(this.USER_NAME_PARAM_NAME, username);
  }

  savePlayerNameAndSurname(name: string, surname: string) {
    localStorage.setItem(this.PLAYER_NAME_PARAM_NAME, name);
    localStorage.setItem(this.PLAYER_SURNAME_PARAM_NAME, surname);
  }

  getJwtToken(): string {
    return localStorage.getItem(this.JWT_TOKEN_PARAM_NAME);
  }

  register(user: User): Observable<any> {
    return this.http.post('/authentication/unfiltered/register', AuthenticationService.encodeUserToBase64(user));
  }

  login(user: User): Observable<any> {
    return this.http.post('/authentication/unfiltered/login', AuthenticationService.encodeUserToBase64(user));
  }

  changePassword(changePassword: ChangePassword): Observable<any> {
    return this.http.post('/authentication/changePassword', AuthenticationService.encodeChangePasswordToBase64(changePassword));
  }

  logout(): void {
    localStorage.clear();
  }

  isSignedIn(): boolean {
    return this.getJwtToken() !== null;
  }

  getLoggedUserInfos(): LoggedUser {
    const loggedUser: LoggedUser = new LoggedUser();
    loggedUser.userName = localStorage.getItem(this.USER_NAME_PARAM_NAME);
    loggedUser.playerName = localStorage.getItem(this.PLAYER_NAME_PARAM_NAME) || 'Unknown Player';
    loggedUser.playerSurname = localStorage.getItem(this.PLAYER_SURNAME_PARAM_NAME);

    return loggedUser;
  }
}
