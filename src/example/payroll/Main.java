package example.payroll;

import example.money.Money;
import java.net.Socket;
import java.time.LocalDate;
import static java.util.Arrays.asList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.function.Predicate;

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
            public Optional<Person> findByUuid(UUID uuid) {
                return Optional.empty();
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
        return personRepository.findByUuid(personUuid)
                               .flatMap(p -> p.getEmployment(employer, date))
                               .map(Employment::getAnnualSalary)
                               .orElse(Money.ZERO);
    }

    private void handleEvent(Event event) {
        // ... heavy processing ...

        event.getResponseQueue()
             .flatMap(this::connectToQueue)
             .ifPresent(this::sendResponse);
    }

    private void sendResponse(Socket responseQueue) {
        // ... send response to connected queue ...
    }

    private static <T> Predicate<T> not(Predicate<T> predicate) {
        return predicate.negate();
    }

    private Optional<Socket> connectToQueue(String queueName) {
        return null;
    }

    private void doSomethingWithNull(Map<String, String> map,
                                     String key) {

        Optional<String> optionalValue = Optional.ofNullable(map.get(key));
        if (optionalValue.isPresent())
            doSomethingWithValue(optionalValue.get());
    }

    private void doSomethingWithValue(String s) {

    }
}
