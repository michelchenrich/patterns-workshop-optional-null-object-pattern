package example.payrollNoOptional;

import java.time.LocalDate;
import java.util.List;

@SuppressWarnings("ALL")

public class Person {
    private List<Employment> employments;

    public Employment getEmployment(Employer employer, LocalDate date) {
        return employments.stream()
                          .filter(e -> e.isActiveOn(date))
                          .filter(e -> e.isEmployedBy(employer))
                          .findFirst()
                          .orElse(null); // HAHAH
    }
}
