package example.money;

import java.util.HashMap;
import java.util.Map;

class CurrencyMap {
    private static Map<String, Currency> currencies = new HashMap<>();

    static Currency get(String currencyCode) {
        return currencies.computeIfAbsent(currencyCode, RealCurrency::new);
    }
}
