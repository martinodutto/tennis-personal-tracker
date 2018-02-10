import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {RouterModule, Routes} from "@angular/router";
import {NewActivityComponent} from "../new-activity/new-activity.component";
import {PageNotFoundComponent} from "../page-not-found/page-not-found.component";
import {HomeComponent} from "../home/home.component";
import {CreditsComponent} from "../credits/credits.component";
import {NewPlayerComponent} from "../new-player/new-player.component";
import {CurrentPlayerResolve} from "../resolves/current-player-resolve";
import {KnownPlayersResolve} from "../resolves/known-players-resolve";
import {LoginComponent} from "../login/login.component";
import {RegisterComponent} from "../register/register.component";
import {PrivatePageGuard} from "../guards/private-page.guard";
import {LoginPageGuard} from "../guards/login-page.guard";
import {ServiceUnavailableComponent} from "../service-unavailable/service-unavailable.component";
import {UnrecoverableErrorComponent} from "../unrecoverable-error/unrecoverable-error.component";
import {ChangePasswordComponent} from "../change-password/change-password.component";
import {UnsavedActivityGuard} from "../guards/unsaved-activity.guard";
import {PastResultsComponent} from "../past-results/past-results.component";
import {PlayerNotCreatedGuard} from "../guards/player-not-created.guard";
import {PlayerAlreadyCreatedGuard} from "../guards/player-already-created.guard";

// here we list all the routes available to the application
const routes: Routes = [{
  path: '',
  pathMatch: 'full',
  redirectTo: 'home'
}, {
  path: 'login',
  component: LoginComponent,
  canActivate: [LoginPageGuard]
}, {
  path: 'register',
  component: RegisterComponent,
  canActivate: [LoginPageGuard]
}, {
  path: 'changepassword',
  component: ChangePasswordComponent,
  canActivate: [PrivatePageGuard]
}, {
  path: 'newplayer',
  component: NewPlayerComponent,
  canActivate: [PrivatePageGuard, PlayerAlreadyCreatedGuard],
  canDeactivate: [PlayerNotCreatedGuard]
}, {
  path: 'home',
  component: HomeComponent,
  canActivate: [PrivatePageGuard]
}, {
  path: 'new',
  component: NewActivityComponent,
  canActivate: [PrivatePageGuard],
  canDeactivate: [UnsavedActivityGuard],
  resolve: {
    currentPlayer: CurrentPlayerResolve,
    knownPlayers: KnownPlayersResolve
  }
}, {
  path: 'past',
  component: PastResultsComponent,
  canActivate: [PrivatePageGuard]
}, {
  path: 'credits',
  component: CreditsComponent,
  canActivate: [PrivatePageGuard]
}, {
  path: 'serviceunavailable',
  component: ServiceUnavailableComponent // must be reached from any user (logged-in or not)
}, {
  path: 'unrecoverableerror',
  component: UnrecoverableErrorComponent // must be reached from any user (logged-in or not)
}, {
  path: '**', // this must be the LAST path, because it matches any not resolved path
  component: PageNotFoundComponent,
  canActivate: [PrivatePageGuard]
}];

// we use {useHash: true} as an option for the router to avoid the browser to call the server (and thus returning a 404)
// while manually changing the URL to a defined router route. See https://github.com/angular/angular-cli/issues/5113
@NgModule({
  imports: [
    CommonModule,
    RouterModule.forRoot(routes, {useHash: true})
  ],
  exports: [
    RouterModule
  ],
  declarations: []
})
export class AppRoutingModule {
}
