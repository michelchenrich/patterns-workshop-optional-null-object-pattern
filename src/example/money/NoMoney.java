package example.money;

import java.math.BigDecimal;

enum NoMoney implements Money {
    INSTANCE;

    public Money plus(Money addend) {
        return addend;
    }

    public Money minus(Money subtrahend) {
        return subtrahend.toNegative();
    }

    public Money toNegative() {
        return this;
    }

    public Money convertToCurrency(Currency currency) {
        return this;
    }

    public Currency getCurrency() {
        return Currency.NONE;
    }

    public BigDecimal getAmount() {
        return BigDecimal.ZERO;
    }
}
