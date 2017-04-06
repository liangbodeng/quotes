import { Component, Input, OnInit } from '@angular/core';

@Component({
  selector: 'qts-quote-list',
  templateUrl: './quote-list.component.html',
  styleUrls: ['./quote-list.component.css']
})
export class QuoteListComponent implements OnInit {
  @Input()
  quotes: any[];

  constructor() { }

  ngOnInit() {
  }
}
