package src;

import java.util.HashMap;
import java.util.Map;

public class ConversionService {
    private final CurrencyAPI currencyAPI;
    private Map<String, Double> rates;

    public ConversionService() {
        this.currencyAPI = new CurrencyAPI(); // Initialize API instance
        updateRates(); // Fetch live rates on startup
    }

    // Fetch and update rates from the API
    public void updateRates() {
        rates = currencyAPI.fetchRates();
        if (rates.isEmpty()) {
            throw new IllegalStateException("Failed to fetch live currency rates.");
        }
    }

    // Convert from one currency to another
    public double convert(String fromCurrency, String toCurrency, double amount) {
        if (!rates.containsKey(fromCurrency) || !rates.containsKey(toCurrency)) {
            throw new IllegalArgumentException("Invalid currency code.");
        }
        double fromRate = rates.get(fromCurrency);
        double toRate = rates.get(toCurrency);
        return (amount / fromRate) * toRate;
    }
}
