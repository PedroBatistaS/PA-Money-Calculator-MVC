package view;

import model.Currency;
import model.ExchangeRate;

public interface ExchangeRateLoader {
    
    ExchangeRate exchangeRateLoader(Currency from, Currency to);
    
}
