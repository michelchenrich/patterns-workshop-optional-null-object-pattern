package example.money;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

class RealCurrency implements Currency {
    private String code;
    private Map<String, BigDecimal> conversionRates = new HashMap<>();

    RealCurrency(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public BigDecimal getConversionRateTo(Currency other) {
        return conversionRates.getOrDefault(other.getCode(), BigDecimal.ONE);
    }

    public void setConversionRateTo(Currency other, BigDecimal rate) {
        conversionRates.put(other.getCode(), rate);
    }
}
