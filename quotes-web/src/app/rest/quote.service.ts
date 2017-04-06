import { Injectable } from '@angular/core';
import { Http } from '@angular/http';
import { Observable } from 'rxjs';

@Injectable()
export class QuoteService {
  constructor(private http: Http) {
  }

  getQuotes(): Observable<any> {
    return this.http
      .get("/quotes")
      .map(response => response.json());
  }
}
