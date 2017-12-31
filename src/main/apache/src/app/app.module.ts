import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';
import {HttpClientModule} from '@angular/common/http';
import {ReactiveFormsModule} from '@angular/forms';

import {AppComponent} from './app.component';
import {NewActivityComponent} from './new-activity/new-activity.component';
import {NgbModule} from "@ng-bootstrap/ng-bootstrap";
import {AppRoutingModule} from "./app-routing/app-routing.module";
import {PageNotFoundComponent} from './page-not-found/page-not-found.component';
import {MatchResultComponent} from './match-result/match-result.component';
import {SetResultComponent} from './set-result/set-result.component';
import {ActivityService} from "./services/activity/activity.service";
import {HomeComponent} from './home/home.component';
import {HeaderComponent} from './header/header.component';
import {FooterComponent} from './footer/footer.component';
import {CreditsComponent} from './credits/credits.component';
import {TimeFormatService} from "./services/time-format/time-format.service";
import {NewPlayerComponent} from './new-player/new-player.component';
import {PlayerService} from "./services/player/player.service";
import {CurrentPlayerResolve} from "./resolves/current-player-resolve";
import {KnownPlayersResolve} from "./resolves/known-players-resolve";
import {LoginComponent} from './login/login.component';

@NgModule({
  declarations: [
    AppComponent,
    NewActivityComponent,
    PageNotFoundComponent,
    MatchResultComponent,
    SetResultComponent,
    HomeComponent,
    HeaderComponent,
    FooterComponent,
    CreditsComponent,
    NewPlayerComponent,
    LoginComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    ReactiveFormsModule,
    NgbModule.forRoot()
  ],
  providers: [
    TimeFormatService,
    PlayerService,
    ActivityService,
    CurrentPlayerResolve,
    KnownPlayersResolve
  ],
  bootstrap: [
    AppComponent
  ]
})
export class AppModule {
}
