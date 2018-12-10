package example.payroll;

import java.util.Optional;
import java.util.UUID;

public interface PersonRepository {

    // ...

    Optional<Person> findByUuid(UUID uuid);

    // ...
}
