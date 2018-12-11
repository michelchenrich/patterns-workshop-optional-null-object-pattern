package example.payrollNoOptional;

import java.util.Optional;
import java.util.UUID;

public interface PersonRepository {

    // ...

    Person findByUuid(UUID uuid);

    // ...
}
