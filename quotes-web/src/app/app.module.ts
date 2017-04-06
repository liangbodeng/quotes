import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { HttpModule } from '@angular/http';

import { AppComponent } from './app.component';
import { NavComponent } from './nav/nav.component';
import { QuoteListComponent } from './quote-list/quote-list.component';
import { QuoteService } from './rest/quote.service';
import 'rxjs/add/operator/map';

@NgModule({
  declarations: [
    AppComponent,
    NavComponent,
    QuoteListComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    HttpModule
  ],
  providers: [
    QuoteService
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
