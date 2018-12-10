package example.payroll;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@SuppressWarnings("ALL")

public class Person {
    private List<Employment> employments;

    public Optional<Employment> getEmployment(Employer employer, LocalDate date) {
        return employments.stream()
                          .filter(e -> e.isActiveOn(date))
                          .filter(e -> e.isEmployedBy(employer))
                          .findFirst();
    }
}
