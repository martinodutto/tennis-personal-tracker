import {Component, OnInit} from '@angular/core';
import {NavigationStart, Router} from "@angular/router";
import {AuthenticationService} from "../services/authentication/authentication.service";
import 'rxjs/add/operator/filter';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {

  showHeader: boolean;

  constructor(private router: Router,
              private authenticationService: AuthenticationService) {
  }

  ngOnInit() {
    // we subscribe to all the navigation change events, in order to use the refreshed login status of the user to show/hide the header
    this.router.events.filter(event => event instanceof NavigationStart).subscribe(() => {
      this.showHeader = this.authenticationService.isSignedIn();
    });
  }

  goToHome(event: MouseEvent) {
    event.preventDefault();
    this.router.navigate(['home']);
  }

  logout(event: MouseEvent) {
    event.preventDefault();
    this.authenticationService.logout();
    this.router.navigate(['logout']);
  }
}
