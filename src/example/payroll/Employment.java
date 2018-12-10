package example.payroll;

import example.money.Money;
import java.time.LocalDate;

public class Employment {
    public boolean isActiveOn(LocalDate date) {
        return false;
    }

    public boolean isEmployedBy(Employer employer) {
        return false;
    }

    public Money getAnnualSalary() {
        return null;
    }
}
