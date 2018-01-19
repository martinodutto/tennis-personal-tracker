import {Component, OnInit} from '@angular/core';
import {NavigationEnd, NavigationStart, Router} from "@angular/router";
import {AuthenticationService} from "../services/authentication/authentication.service";
import 'rxjs/add/operator/filter';
import {LoggedUser} from "../model/logged-user";

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {

  loggedUser: LoggedUser;
  showHeader: boolean;

  constructor(private router: Router,
              private authenticationService: AuthenticationService) {
  }

  ngOnInit() {
    this.loggedUser = this.authenticationService.getLoggedUserInfos();

    // we subscribe to all the navigation change events, in order to use the refreshed login status of the user to show/hide the header
    this.router.events.filter(event => event instanceof NavigationStart).subscribe(() => {
      this.showHeader = this.authenticationService.isSignedIn();
    });

    this.router.events.filter(event => event instanceof NavigationEnd).subscribe(() => {
      // at any route change we update the user infos shown (necessary because we could have logged in/out)
      this.loggedUser = this.authenticationService.getLoggedUserInfos();
    });
  }

  goToHome(event: MouseEvent) {
    event.preventDefault();
    this.router.navigate(['home']);
  }

  goToChangePassword(event: MouseEvent) {
    event.preventDefault();
    this.router.navigate(['changepassword']);
  }

  logout(event: MouseEvent) {
    event.preventDefault();
    this.authenticationService.logout();
    this.router.navigate(['login']);
  }
}
