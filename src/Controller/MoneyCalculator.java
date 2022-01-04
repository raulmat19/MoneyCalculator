package Controller;

import Model.Currency;
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
    }
}

