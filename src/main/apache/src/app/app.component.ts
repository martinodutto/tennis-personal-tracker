import {Component, OnInit} from '@angular/core';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent implements OnInit {

  constructor(/*private http: HttpClient*/) {}

  ngOnInit(): void {
    // this.http.get('../script/hello', {responseType: 'text'}).subscribe(response => {
    //   console.info(`This is the response got from the server: ${response}`);
    // })
  }
}
