import {Component, Input, OnInit} from '@angular/core';
import {NgbActiveModal} from '@ng-bootstrap/ng-bootstrap';

@Component({
  selector: 'app-edit-activity-modal',
  templateUrl: './edit-activity-modal.component.html',
  styleUrls: ['./edit-activity-modal.component.scss']
})
export class EditActivityModalComponent implements OnInit {

  @Input() data: any;

  constructor(public activeModal: NgbActiveModal) {
  }

  ngOnInit() {
  }
}
