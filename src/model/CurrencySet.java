package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class CurrencySet{    
    private final HashMap<String,Currency> currencySet;

    public CurrencySet() {
        this.currencySet  = new HashMap<String,Currency>();;
    }
    
    public HashMap<String, Currency> currencyMap() {
        return currencySet;
    }
    
    public void add(Currency currency) {
        currencySet.put(currency.getCode(), currency);
    }
    
    public Currency findCurrency(String code) {
        return currencySet.get(code);
    }    
}
