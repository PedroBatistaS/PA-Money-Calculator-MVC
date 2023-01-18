

package view;

import javax.swing.JTextField;
import model.Exchange;
import model.ExchangeRate;
import model.Money;
import static application.Application.components;
import view.MoneyDisplay;


public class MoneyResultDisplay implements MoneyDisplay{

    private final ExchangeRate exchangeRate;
    private final Exchange exchange;

    public MoneyResultDisplay(Exchange exchange, ExchangeRate exchangeRate) {
        this.exchange = exchange;
        this.exchangeRate = exchangeRate;
    }

    @Override
    public void show() {
        ((JTextField) components().get("ResultTextField")).setText(new Money(exchange.getMoney().getAmount() * exchangeRate.getRate(),exchange.getCurrency()).toString());
    }
    
    

}
