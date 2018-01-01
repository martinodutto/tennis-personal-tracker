import {Component, OnInit} from '@angular/core';
import {Router} from "@angular/router";
import {AuthenticationService} from "../services/authentication/authentication.service";

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {

  constructor(private router: Router,
              private authenticationService: AuthenticationService) { }

  ngOnInit() {
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
