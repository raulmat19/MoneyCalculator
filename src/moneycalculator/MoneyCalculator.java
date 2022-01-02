package moneycalculator;

import Model.Currency;
import Model.ExchangeRate;
import Persistence.CurrencyLoader;
import Persistence.ExchangeRateLoader;
import Persistence.Persistence_Archive.CurrencyLoaderArchive;
import Persistence.Persistence_Archive.ExchangeRateArchive;

import java.util.List;

public class MoneyCalculator {

    public static void main(String[] args) {
        
        CurrencyLoader currencyLoader = new CurrencyLoaderArchive("currency.txt");
        // CurrencyLoader currencyLoader = new CurrencyLoaderWebService("C:\\Users\\raulp\\Documents\\NetBeansProjects\\MoneyCalculator\\currency.txt");
        List<Currency> listCurrencies = currencyLoader.loadAllCurrencies();
        ExchangeRateLoader exchangeRateFile = new ExchangeRateArchive("exchangeRates.txt");
        
        for(int i = 0; i < listCurrencies.size() - 1; i++) {
            System.out.println("Main: " + listCurrencies.get(i) + " " + i);
            System.out.println("Main: " + listCurrencies.get(i+1) + " " + i);
            ExchangeRate ex = exchangeRateFile.loadAExchangeRate(listCurrencies.get(i), listCurrencies.get(i+1));
            System.out.println(ex.toString());
        
        }
        
        
    }

}
        
        // Interfaz: Dialogo de configuracion (Dialog), y panel de resultados (Display)
        // getMoney(), getCurrencyTo() (dialog)
        // display(Money)
        // MVC, que es?
    
    

