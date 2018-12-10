package example.money;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import org.junit.Before;
import org.junit.Test;

public class MoneyTest {
    @Before
    public void setUp() {
        Currency.setConversionRate("EUR", "USD", 2.0);
        Currency.setConversionRate("USD", "EUR", 0.5);
    }

    @Test
    public void printsAmountWithTwoDecimalsFollowedByCurrencyCode() {
        assertEquals("10.00 USD", Money.of(10.0, "USD").toString());
    }

    @Test
    public void equalsAnotherWithSameAmountAndCurrency() {
        assertEquals(Money.of(10.0, "USD"),
                     Money.of(10.0, "USD"));
        assertNotEquals(Money.of(10.0, "USD"),
                        Money.of(10.1, "USD"));
        assertNotEquals(Money.of(10.0, "USD"),
                        Money.of(10.0, "EUR"));
    }

    @Test
    public void addingOneEuroToOneDollarResultsInThreeDollars() {
        assertEquals(Money.of(3.0, "USD"),
                     Money.of(1.0, "USD")
                          .plus(Money.of(1.0, "EUR")));
    }

    @Test
    public void addingOneDollarToOneEuroResultsInOneAndAHalfEuro() {
        assertEquals(Money.of(1.5, "EUR"),
                     Money.of(1.0, "EUR")
                          .plus(Money.of(1.0, "USD")));
    }

    @Test
    public void oneEuroPlusZeroIsOneEuro() {
        assertEquals(Money.of(1.0, "EUR"),
                     Money.of(1.0, "EUR").plus(Money.ZERO));
    }

    @Test
    public void oneDollarMinusZeroIsOneDollar() {
        assertEquals(Money.of(1.0, "USD"),
                     Money.of(1.0, "USD").minus(Money.ZERO));
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
}
