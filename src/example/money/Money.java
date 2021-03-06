package example.money;

import java.math.BigDecimal;

public interface Money {

    Money plus(Money addend);

    Money minus(Money subtrahend);

    Money toNegative();

    Money convertToCurrency(Currency currency);

    Currency getCurrency();

    BigDecimal getAmount();

    // ...

    Money ZERO = NoMoney.INSTANCE;

    static Money of(double rawAmount, String currencyCode) {
        return of(BigDecimal.valueOf(rawAmount), Currency.get(currencyCode));
    }

    static Money of(BigDecimal amount, Currency currency) {
        return amount.compareTo(BigDecimal.ZERO) == 0 ?
               ZERO :
               new MoneyInCurrency(amount, currency);
    }
}
