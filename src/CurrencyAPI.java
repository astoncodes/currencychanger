package src;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class CurrencyAPI {
    private static final String API_URL = "https://open.er-api.com/v6/latest/USD";

    // Fetch live exchange rates
    public Map<String, Double> fetchRates() {
        Map<String, Double> rates = new HashMap<>();
        try {
            URL url = new URL(API_URL);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            int responseCode = connection.getResponseCode();
            if (responseCode == 200) {
                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                StringBuilder response = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) {
                    response.append(line);
                }
                reader.close();

                // Parse JSON response
                rates = parseRates(response.toString());
            } else {
                System.out.println("Failed to fetch rates. HTTP Response Code: " + responseCode);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rates;
    }

    // Parse JSON response to extract rates
    private Map<String, Double> parseRates(String jsonResponse) {
        Map<String, Double> rates = new HashMap<>();
        try {
            String ratesSection = jsonResponse.split("\"rates\":")[1].split("}")[0];
            String[] pairs = ratesSection.split(",");
            for (String pair : pairs) {
                String[] keyValue = pair.replace("\"", "").split(":");
                rates.put(keyValue[0], Double.parseDouble(keyValue[1]));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rates;
    }
}
