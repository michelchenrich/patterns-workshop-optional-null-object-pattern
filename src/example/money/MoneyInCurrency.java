package example.money;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.function.BiFunction;

public class MoneyInCurrency implements Money {
    private BigDecimal amount;
    private Currency currency;

    MoneyInCurrency(BigDecimal amount, Currency currency) {
        this.amount = amount;
        this.currency = currency;
    }

    public Money plus(Money addend) {
        return applyConvertingToCurrency(BigDecimal::add, addend);
    }

    public Money minus(Money subtrahend) {
        return applyConvertingToCurrency(BigDecimal::subtract, subtrahend);
    }

    private Money applyConvertingToCurrency(BiFunction<BigDecimal, BigDecimal, BigDecimal> operation,
                                            Money other) {
        return applyWithinSameCurrency(operation, other.convertToCurrency(currency));
    }

    private Money applyWithinSameCurrency(BiFunction<BigDecimal, BigDecimal, BigDecimal> operation,
                                          Money otherInSameCurrency) {
        return Money.of(operation.apply(amount, otherInSameCurrency.getAmount()),
                        currency);
    }

    public Money toNegative() {
        return Money.of(amount.abs().negate(),
                        currency);
    }

    public Money convertToCurrency(Currency otherCurrency) {
        return Money.of(amount.multiply(currency.getConversionRateTo(otherCurrency)),
                        otherCurrency);
    }

    public Currency getCurrency() {
        return currency;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public String toString() {
        return amount.setScale(2, RoundingMode.HALF_UP).toString() + " " + currency.getCode();
    }

    public boolean equals(Object other) {
        return other instanceof Money &&
               equalsMoney((Money) other);
    }

    private boolean equalsMoney(Money other) {
        return this.getAmount().compareTo(other.getAmount()) == 0 &&
               this.getCurrency().getCode().equals(other.getCurrency().getCode());
    }
}
