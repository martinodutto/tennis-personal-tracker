import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';
import {HTTP_INTERCEPTORS, HttpClientModule} from '@angular/common/http';
import {ReactiveFormsModule} from '@angular/forms';

import {AppComponent} from './app.component';
import {NewActivityComponent} from './new-activity/new-activity.component';
import {NgbModule} from '@ng-bootstrap/ng-bootstrap';
import {AppRoutingModule} from './app-routing/app-routing.module';
import {PageNotFoundComponent} from './page-not-found/page-not-found.component';
import {MatchResultComponent} from './match-result/match-result.component';
import {SetResultComponent} from './set-result/set-result.component';
import {HomeComponent} from './home/home.component';
import {HeaderComponent} from './header/header.component';
import {FooterComponent} from './footer/footer.component';
import {CreditsComponent} from './credits/credits.component';
import {NewPlayerComponent} from './new-player/new-player.component';
import {LoginComponent} from './login/login.component';
import {RegisterComponent} from './register/register.component';
import {JwtInterceptor} from './interceptors/jwt-interceptor/jwt-interceptor';
import {UnrecoverableErrorComponent} from './unrecoverable-error/unrecoverable-error.component';
import {ServiceUnavailableComponent} from './service-unavailable/service-unavailable.component';
import {ChangePasswordComponent} from './change-password/change-password.component';
import {PastResultsComponent} from './past-results/past-results.component';
import {AgGridModule} from 'ag-grid-angular';
import {environment} from '../environments/environment';
import {BaseInterceptor} from './interceptors/base-interceptor/base-interceptor';

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
    LoginComponent,
    RegisterComponent,
    UnrecoverableErrorComponent,
    ServiceUnavailableComponent,
    ChangePasswordComponent,
    PastResultsComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    ReactiveFormsModule,
    NgbModule,
    AgGridModule.withComponents([])
  ],
  providers: [
    {
      provide: HTTP_INTERCEPTORS,
      useClass: JwtInterceptor,
      multi: true
    },
    {
      provide: HTTP_INTERCEPTORS,
      useClass: BaseInterceptor,
      multi: true
    },
    {
      provide: 'BASE_API_URL',
      useValue: environment.baseApiUrl
    },
  ],
  bootstrap: [
    AppComponent
  ]
})
export class AppModule {
}
