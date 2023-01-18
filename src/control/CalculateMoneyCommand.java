package control;

import view.RateSelected;
import model.Exchange;
import model.ExchangeRate;
import model.Money;
import view.ExchangeRateLoaderFromWeb;
import view.MoneyResultDisplay;


public class CalculateMoneyCommand implements Command{

    @Override
    public void execute() {
        RateSelected userExchange = new RateSelected();
        ExchangeRate exchangeRate = userExchange.exchangeRateSelected();
        Exchange exchange = new Exchange(new Money(userExchange.getAmount(), exchangeRate.getFrom()), exchangeRate.getTo());
        new MoneyResultDisplay(exchange, exchangeRate).show();
    }

}
