import { browser, element, by } from 'protractor';

export class QuotesWebPage {
  navigateTo() {
    return browser.get('/');
  }

  getParagraphText() {
    return element(by.css('qts-root h1')).getText();
  }
}
