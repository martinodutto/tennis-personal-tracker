import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {RouterModule, Routes} from "@angular/router";
import {NewActivityComponent} from "../new-activity/new-activity.component";
import {PageNotFoundComponent} from "../page-not-found/page-not-found.component";
import {HomeComponent} from "../home/home.component";
import {CreditsComponent} from "../credits/credits.component";
import {NewPlayerComponent} from "../new-player/new-player.component";

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
  component: NewActivityComponent
}, {
  path: 'credits',
  component: CreditsComponent
}, {
  path: '**', // this must be the LAST path, because it matches any not resolved path
  component: PageNotFoundComponent
}];

@NgModule({
  imports: [
    CommonModule,
    RouterModule.forRoot(routes)
  ],
  exports: [
    RouterModule
  ],
  declarations: []
})
export class AppRoutingModule { }
