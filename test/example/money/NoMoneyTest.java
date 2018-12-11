package example.money;

import java.math.BigDecimal;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class NoMoneyTest {
    @Test
    public void zeroHasZeroAmount() {
        assertEquals(BigDecimal.ZERO,
                     Money.ZERO.getAmount());
    }

    @Test
    public void zeroHasNoCurrency() {
        assertEquals(Currency.NONE,
                     Money.ZERO.getCurrency());
    }

    @Test
    public void zeroToStringIsJust0() {
        assertEquals("0.00",
                     Money.ZERO.toString());
    }

    @Test
    public void zeroPlusOneDollarIsOneDollar() {
        assertEquals(Money.of(1.0, "USD"),
                     Money.ZERO.plus(Money.of(1.0, "USD")));
    }

    @Test
    public void zeroMinusOneDollarIsMinusOneDollar() {
        assertEquals(Money.of(-1.0, "USD"),
                     Money.ZERO.minus(Money.of(1.0, "USD")));
    }

    @Test
    public void zeroMinusMinusOneDollarIsMinusOneDollar() {
        assertEquals(Money.of(-1.0, "USD"),
                     Money.ZERO.minus(Money.of(-1.0, "USD")));
    }

    @Test
    public void zeroConvertsToZero() {
        assertEquals(Money.ZERO,
                     Money.ZERO.convertToCurrency(Currency.get("USD")));
    }

    @Test
    public void negativeZeroIsZero() {
        assertEquals(Money.ZERO,
                     Money.ZERO.toNegative());
    }
}
