package Controller;

import Controller.MainFrame;
import Model.Currency;
import Model.ExchangeRate;
import View.Persistance.CurrencyLoader;
import View.Persistance.ExchangeRateLoader;
import View.Persistance.Files.CurrencyLoaderArchive;
import View.Persistance.Files.ExchangeRateArchive;

import java.util.List;

public class MoneyCalculator {

    public static void main(String[] args) {
        
        CurrencyLoader currencyLoader = new CurrencyLoaderArchive("currency.txt");
        // CurrencyLoader currencyLoader = new CurrencyLoaderWebService("C:\\Users\\raulp\\Documents\\NetBeansProjects\\MoneyCalculator\\currency.txt");
        List<Currency> listCurrencies = currencyLoader.loadAllCurrencies();
        ExchangeRateLoader exchangeRateLoader = new ExchangeRateArchive("exchangeRates.txt");
        MainFrame gui = new MainFrame(listCurrencies, exchangeRateLoader);
        
        for(int i = 0; i < listCurrencies.size() - 1; i++) {
            System.out.println("Main: " + listCurrencies.get(i) + " " + i);
            System.out.println("Main: " + listCurrencies.get(i+1) + " " + i);
            ExchangeRate ex = exchangeRateLoader.loadAExchangeRate(listCurrencies.get(i), listCurrencies.get(i+1));
            System.out.println(ex.toString());
        
        }
        
        
    }

}
        
        // Interfaz: Dialogo de configuracion (Dialog), y panel de resultados (Display)
        // getMoney(), getCurrencyTo() (dialog)
        // display(Money)
        // MVC, que es?
    
    

