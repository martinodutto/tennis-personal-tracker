import {Component, OnInit} from '@angular/core';
import {HttpClient} from "@angular/common/http";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {

  title = 'Tennis Personal Tracker';

  helloText: string;

  constructor(private http: HttpClient) {}

  ngOnInit(): void {
    this.http.get('../script/hello', {responseType: 'text'}).subscribe(response => {
      console.info(`This is the response got from the server: ${response}`);
      this.helloText = response;
    })
  }
}
