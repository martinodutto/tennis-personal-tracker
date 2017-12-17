import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {RouterModule, Routes} from "@angular/router";
import {NewActivityComponent} from "../new-activity/new-activity.component";

// here we list all the routes available to the application
const routes: Routes = [{
  path: '',
  pathMatch: 'full',
  redirectTo: 'new' // for now at least, we redirect the home to the new activity page
}, {
  path: 'new',
  component: NewActivityComponent
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
