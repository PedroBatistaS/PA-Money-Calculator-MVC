package view;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONException;
import org.json.JSONObject;

import model.*;
import view.ExchangeRateLoader;

public class ExchangeRateLoaderFromWeb implements ExchangeRateLoader{
    
    private static String readAll(Reader rd) throws IOException {
        StringBuilder sb = new StringBuilder();
        int cp;
        while ((cp = rd.read()) != -1) {
          sb.append((char) cp);
        }
        return sb.toString();
    }
    
    public static JSONObject readJsonFromUrl(String url) throws IOException, JSONException {
        InputStream is = new URL(url).openStream();
        try {
            BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
            String jsonText = readAll(rd);
            JSONObject json = new JSONObject(jsonText);
            return json;
        } finally {
            is.close();
        }
    }
    


    @Override
    public ExchangeRate exchangeRateLoader(Currency from, Currency to) {
        String url = "https://cdn.jsdelivr.net/gh/fawazahmed0/currency-api@1/latest/currencies/" + 
                      from.getCode().toLowerCase() + "/" + to.getCode().toLowerCase() + ".json";
        JSONObject json;
        try {
            json = readJsonFromUrl(url);
            return new ExchangeRate(from, to, json.getBigDecimal(to.getCode().toLowerCase()).floatValue());
        } catch (IOException | JSONException ex) {
            Logger.getLogger(ExchangeRateLoaderFromWeb.class.getName()).log(Level.SEVERE, null, ex);
        } 
        return null;
    }
    
}
