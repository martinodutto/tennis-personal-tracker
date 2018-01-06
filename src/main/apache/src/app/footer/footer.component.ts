import {Component, OnInit} from '@angular/core';
import {NavigationStart, Router} from "@angular/router";
import {AuthenticationService} from "../services/authentication/authentication.service";

@Component({
  selector: 'app-footer',
  templateUrl: './footer.component.html',
  styleUrls: ['./footer.component.css']
})
export class FooterComponent implements OnInit {

  showFooter: boolean;

  constructor(private router: Router,
              private authenticationService: AuthenticationService) { }

  ngOnInit() {
    // we subscribe to all the navigation change events, in order to use the refreshed login status of the user to show/hide the footer
    this.router.events.filter(event => event instanceof NavigationStart).subscribe(() => {
      this.showFooter = this.authenticationService.isSignedIn();
    });
  }

  goToCredits(event: MouseEvent) {
    event.preventDefault();
    this.router.navigate(['credits']);
  }
}
