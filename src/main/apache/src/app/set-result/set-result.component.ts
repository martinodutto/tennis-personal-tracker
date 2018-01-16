import {Component, Input, OnInit, ViewChild} from '@angular/core';
import {FormGroup} from "@angular/forms";
import {NgbPopover} from "@ng-bootstrap/ng-bootstrap";

@Component({
  selector: 'app-set-result',
  templateUrl: './set-result.component.html',
  styleUrls: ['./set-result.component.css']
})
export class SetResultComponent implements OnInit {

  @Input("group") set: FormGroup;
  @Input() parity: boolean;
  @ViewChild("p") popover: NgbPopover; // this way we can refer to the view-defined variable "p"

  ngOnInit() {
    this.set.valueChanges.subscribe(() => {
      if (this.set.valid) {
        this.popover.close();
      } else {
        this.popover.open();
      }
    });
  }

  numbersOnly(event) {
    return (((event.which > 47) && (event.which < 58)) || (event.which == 13));
  }
}
