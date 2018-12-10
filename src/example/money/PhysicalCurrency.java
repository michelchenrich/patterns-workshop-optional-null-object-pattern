package example.money;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashMap;
import java.util.Map;

class PhysicalCurrency implements Currency {
    private String code;
    private Map<String, BigDecimal> conversionRates = new HashMap<>();

    PhysicalCurrency(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public BigDecimal getConversionRateTo(Currency other) {
        return conversionRates.getOrDefault(other.getCode(), BigDecimal.ZERO);
    }

    public void setConversionRateTo(Currency other, BigDecimal rate) {
        conversionRates.put(other.getCode(), rate);
    }
}
