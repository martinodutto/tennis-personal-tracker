import {Injectable} from '@angular/core';
import {ActivatedRouteSnapshot, CanDeactivate, RouterStateSnapshot} from '@angular/router';
import {Observable} from 'rxjs';
import {NewActivityComponent} from '../new-activity/new-activity.component';

@Injectable()
export class UnsavedActivityGuard implements CanDeactivate<NewActivityComponent> {

  canDeactivate(component: NewActivityComponent,
                currentRoute: ActivatedRouteSnapshot,
                currentState: RouterStateSnapshot,
                nextState?: RouterStateSnapshot): Observable<boolean> | Promise<boolean> | boolean {
    return component.canDeactivate() || component.openDiscardModal();
  }
}
