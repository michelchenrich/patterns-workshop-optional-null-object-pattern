package example.payrollNoOptional;

import example.money.Money;
import java.time.LocalDate;
import static java.util.Arrays.asList;
import java.util.List;
import java.util.UUID;

@SuppressWarnings("ALL")
public class Main {

    private Employer subsidiary;
    private LocalDate date;
    private PersonRepository personRepository;

    public static void main(String... arguments) {
        new Main().run();
    }

    private void run() {
        personRepository = new PersonRepository() {
            public Person findByUuid(UUID uuid) {
                return null;
            }
        };
        subsidiary = new Employer();
        date = LocalDate.now();

        List<UUID> personUuidList = asList(UUID.randomUUID(),
                                           UUID.randomUUID(),
                                           UUID.randomUUID());

        getTotalAnnualPayroll(personUuidList, subsidiary, date);
    }

    private void getTotalAnnualPayroll(List<UUID> personUuidList,
                                       Employer employer,
                                       LocalDate date) {
        personUuidList.stream()
                      .map(personUuid -> getAnnualSalary(personUuid, employer, date))
                      .reduce(Money::plus);
    }

    private Money getAnnualSalary(UUID personUuid,
                                  Employer employer,
                                  LocalDate date) {
        Person person = personRepository.findByUuid(personUuid);
        if (person != null) {
            Employment employment = person.getEmployment(employer, date);
            if (employment != null) {
                return employment.getAnnualSalary();
            } else {
                return Money.ZERO;
            }
        } else {
            return Money.ZERO;
        }
    }
}
