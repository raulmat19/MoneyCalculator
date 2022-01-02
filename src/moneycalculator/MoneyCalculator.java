package moneycalculator;

import Model.Currency;
import Persistence.CurrencyLoader;
import Persistence.Persistence_Archive.CurrencyLoaderArchive;

import java.util.List;

public class MoneyCalculator {

    public static void main(String[] args) {
        
        CurrencyLoader currencyLoader = new CurrencyLoaderArchive("C:\\Users\\raulp\\Documents\\NetBeansProjects\\MoneyCalculator\\currency.txt");
        // CurrencyLoader currencyLoader = new CurrencyLoaderWebService("C:\\Users\\raulp\\Documents\\NetBeansProjects\\MoneyCalculator\\currency.txt");
        
        List<Currency> listCurrencies;
        
        listCurrencies = currencyLoader.loadAllCurrencies();
        
        // Interfaz: Dialogo de configuracion (Dialog), y panel de resultados (Display)
        // getMoney(), getCurrencyTo() (dialog)
        // display(Money)
        // MVC, que es?
    }
    
}
