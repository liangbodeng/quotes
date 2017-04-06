import { QuotesWebPage } from './app.po';

describe('quotes-web App', () => {
  let page: QuotesWebPage;

  beforeEach(() => {
    page = new QuotesWebPage();
  });

  it('should display message saying app works', () => {
    page.navigateTo();
    expect(page.getParagraphText()).toEqual('qts works!');
  });
});
