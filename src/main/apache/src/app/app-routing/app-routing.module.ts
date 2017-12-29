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

// here we list all the routes available to the application
const routes: Routes = [{
  path: '',
  pathMatch: 'full',
  redirectTo: 'home'
}, {
  path: 'newplayer',
  component: NewPlayerComponent
}, {
  path: 'home',
  component: HomeComponent
}, {
  path: 'new',
  component: NewActivityComponent,
  resolve: {
    currentPlayer: CurrentPlayerResolve,
    knownPlayers: KnownPlayersResolve
  }
}, {
  path: 'credits',
  component: CreditsComponent
}, {
  path: '**', // this must be the LAST path, because it matches any not resolved path
  component: PageNotFoundComponent
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
