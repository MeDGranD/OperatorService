package ru.medgrand.OperatorService.Infrastructure;

import org.springframework.data.repository.CrudRepository;
import ru.medgrand.OperatorService.Model.User;

public interface UserRepository extends CrudRepository<User, Long> {

    User findByUsername(String username);

}
