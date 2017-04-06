import { Component, OnInit } from '@angular/core';
import { QuoteService } from './rest/quote.service';

@Component({
  selector: 'qts-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {
  quotes: any[];

  constructor(private quoteService: QuoteService) {
  }

  ngOnInit() {
    this.quoteService.getQuotes()
      .subscribe(quotes => this.quotes = quotes);
  }
}
