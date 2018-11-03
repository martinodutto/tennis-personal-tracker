import {Component, OnInit} from '@angular/core';
import {ActivitiesAndResultsService} from '../services/activities-and-results/activities-and-results.service';
import {ActivityAndResult} from '../model/activity-and-result';

@Component({
  selector: 'app-past-results',
  templateUrl: './past-results.component.html',
  styleUrls: ['./past-results.component.css']
})
export class PastResultsComponent implements OnInit {

  constructor(private activitiesAndResultsService: ActivitiesAndResultsService) { }

  items: ActivityAndResult[] = [];
  itemCount = 0;
  loadingErrorMessage: string = null;

  // TODO maybe put this logic inside a SetResult
  static formatSetResult(player1Result: number, player2Result: number): string {
    if (player1Result !== null && player2Result !== null) {
      return `${player1Result} - ${player2Result}`;
    } else {
      return '';
    }
  }

  ngOnInit() {
  }

  reloadItems(params) {
    this.loadingErrorMessage = null; // reset
    this.activitiesAndResultsService.getPastActivities(params).subscribe(response => {
      if (response) {
        this.items = response.data;
        this.itemCount = response.totalCount;
      } else {
        this.items = [];
        this.itemCount = 0;
      }
    }, err => {
      console.error(`Activity loading ended with error: ${err.message}`);
      this.loadingErrorMessage = 'An error occurred while loading the activities';
    });
  }

  // TODO maybe put this logic inside a MatchResult
  formatMatchResult(record: ActivityAndResult): string {
    return `${PastResultsComponent.formatSetResult(record.set1P1, record.set1P2)} ` +
      `${PastResultsComponent.formatSetResult(record.set2P1, record.set2P2)} ` +
      `${PastResultsComponent.formatSetResult(record.set3P1, record.set3P2)} ` +
      `${PastResultsComponent.formatSetResult(record.set4P1, record.set4P2)} ` +
      `${PastResultsComponent.formatSetResult(record.set5P1, record.set5P2)}`;
  }
}
