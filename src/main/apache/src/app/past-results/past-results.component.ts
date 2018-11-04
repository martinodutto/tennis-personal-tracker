import {Component, OnInit} from '@angular/core';
import {ActivitiesAndResultsService} from '../services/activities-and-results/activities-and-results.service';
import {ActivityAndResult} from '../model/activity-and-result';
import {ICellRendererParams, IDatasource, IGetRowsParams} from 'ag-grid-community';
import {PaginatedResponse} from '../model/paginated-response';

@Component({
  selector: 'app-past-results',
  templateUrl: './past-results.component.html',
  styleUrls: ['./past-results.component.css']
})
export class PastResultsComponent implements OnInit {

  loadingErrorMessage: string = null;
  columnDefs: object[];
  gridComponents: any;
  dataSource: IDatasource;
  private gridApi: any;

  constructor(private activitiesAndResultsService: ActivitiesAndResultsService) {
    this.columnDefs = [{
      headerName: 'Date',
      field: 'activityDate'
    }, {
      headerName: 'Type',
      field: 'activityType'
    }, {
      headerName: 'Opponent name',
      field: 'secondPlayer',
      cellRenderer: 'nameSurnameRenderer',
      suppressSorting: true
    }, {
      headerName: 'Result',
      field: 'matchResult',
      cellRenderer: 'matchRenderer',
      suppressSorting: true
    }, {
      headerName: 'Club',
      field: 'club'
    }, {
      headerName: 'Notes',
      field: 'notes'
    }];
    this.gridComponents = {
      nameSurnameRenderer: (params: ICellRendererParams) => {
        const row = params.data;
        if (row) {
          return row.secondPlayerName + ' ' + row.secondPlayerSurname;
        }
      },
      matchRenderer: (params: ICellRendererParams) => {
        const row = params.data;
        if (row) {
          return PastResultsComponent.formatMatchResult(row);
        }
      }
    };
  }

  // TODO maybe put this logic inside a SetResult
  static formatSetResult(player1Result: number, player2Result: number): string {
    if (player1Result !== null && player2Result !== null) {
      return `${player1Result} - ${player2Result}`;
    } else {
      return '';
    }
  }

  // TODO maybe put this logic inside a MatchResult
  static formatMatchResult(record: ActivityAndResult): string {
    return `${PastResultsComponent.formatSetResult(record.set1P1, record.set1P2)} ` +
      `${PastResultsComponent.formatSetResult(record.set2P1, record.set2P2)} ` +
      `${PastResultsComponent.formatSetResult(record.set3P1, record.set3P2)} ` +
      `${PastResultsComponent.formatSetResult(record.set4P1, record.set4P2)} ` +
      `${PastResultsComponent.formatSetResult(record.set5P1, record.set5P2)}`;
  }

  ngOnInit() {
  }

  onGridReady(event): void {
    this.gridApi = event.api;
    this.dataSource = {
      getRows: (params: IGetRowsParams) => {
        this.gridApi.showLoadingOverlay();
        this.activitiesAndResultsService.getPastActivities(params)
          .subscribe((page: PaginatedResponse<ActivityAndResult>) => {
            if (page) {
              params.successCallback(page.data, page.totalCount);
            }
          }, err => {
            console.error(`Activity loading ended with error: ${err.message}`);
            this.loadingErrorMessage = 'An error occurred while loading the activities';
            params.failCallback();
          }, () => {
            this.gridApi.hideOverlay();
          });
      }
    };
  }
}
