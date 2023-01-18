package model;

import java.text.DecimalFormat;


public class Money{
    
    private final double amount;
    private final Currency currency;

    public Money(double amount, Currency currency) {
        this.amount = amount;
        this.currency = currency;
    }

    public double getAmount() {
        return amount;
    }

    public Currency getCurrency() {
        return currency;
    }

    @Override
    public String toString() {
        DecimalFormat df = new DecimalFormat("#.##");
        return df.format(getAmount())+ " " + getCurrency(); 
    }
    
    

}
