package example.money;

import java.math.BigDecimal;

public interface Currency {
    Currency NONE = NoCurrency.INSTANCE;

    static Currency get(String currencyCode) {
        return CurrencyMap.get(currencyCode);
    }

    static void setConversionRate(String fromCurrencyCode, String toCurrencyCode, double rate) {
        get(fromCurrencyCode).setConversionRateTo(get(toCurrencyCode), rate);
    }

    BigDecimal getConversionRateTo(Currency other);

    default void setConversionRateTo(Currency other, double rate) {
        setConversionRateTo(other, BigDecimal.valueOf(rate));
    }

    void setConversionRateTo(Currency other, BigDecimal rate);

    String getCode();
}
