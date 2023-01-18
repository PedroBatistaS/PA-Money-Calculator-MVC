package view;

import view.ExchangeRateLoaderFromWeb;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import model.Currency;
import model.ExchangeRate;
import static application.Application.components;
import static application.Application.currencySet;
import view.ExchangeRateLoader;


public final class RateSelected implements ExchangeRateLoader{    
    private Currency from;
    private Currency to;
    private Double amount;
    private final ExchangeRate exchangeRate;

    public RateSelected() {
        this.from = currencyFrom();
        this.to = currencyTo();
        this.amount = amount();
        this.exchangeRate = exchangeRateLoader(this.from, this.to);
    }
    
    public Double getAmount() {
            return amount;
    }
    
    @Override
    public ExchangeRate exchangeRateLoader(Currency from, Currency to) {
        ExchangeRateLoaderFromWeb  erw = new ExchangeRateLoaderFromWeb();
        return erw.exchangeRateLoader(from, to);
    }

    private Double amount() {
        try {
            return Double.valueOf(((JTextField) components().get("OriginalMoneyTextField")).getText());
        }catch (Exception e){
            return Double.valueOf(String.valueOf(0));
        }
    }

    private Currency currencyTo() {
        try {
            return currencySet().findCurrency(((JComboBox) components().get("ExchangeToCombo")).getSelectedItem().toString());
        }catch (Exception e){
            return null;
        }    }

    private Currency currencyFrom() {
        try{
            return  currencySet().findCurrency(((JComboBox) components().get("OriginalCombo")).getSelectedItem().toString());
        }catch (Exception e){
            return null;
        }
    }

    public ExchangeRate exchangeRateSelected() {
        return this.exchangeRate;
    }
    

}
