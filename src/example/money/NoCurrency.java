package example.money;

import java.math.BigDecimal;

enum NoCurrency implements Currency {
    INSTANCE;

    public BigDecimal getConversionRateTo(Currency ignored) {
        return BigDecimal.ONE;
    }

    public void setConversionRateTo(Currency ignoredCurrency, BigDecimal ignoredRate) {
    }

    public String getCode() {
        return "";
    }
}
