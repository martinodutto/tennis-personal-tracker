import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';
import {HttpClientModule} from '@angular/common/http';
import {FormsModule} from '@angular/forms';

import {AppComponent} from './app.component';
import {NewActivityComponent} from './new-activity/new-activity.component';
import {NgbModule} from "@ng-bootstrap/ng-bootstrap";
import {AppRoutingModule} from "./app-routing/app-routing.module";


@NgModule({
  declarations: [
    AppComponent,
    NewActivityComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule,
    NgbModule.forRoot()
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
