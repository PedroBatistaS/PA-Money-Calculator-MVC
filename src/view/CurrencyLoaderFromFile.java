

package view;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.CurrencySet;
import model.Currency;
import view.CurrencyLoader;


public class CurrencyLoaderFromFile implements CurrencyLoader{
    
    private final String fileName;
    
    public CurrencyLoaderFromFile(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public CurrencySet loadCurrency() {
        CurrencySet currencySet = new CurrencySet();
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(new File(fileName)));
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] campos = linea.split(",");
                currencySet.add(new Currency(campos[0], campos[1], campos[2]));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return currencySet;
    }

}
