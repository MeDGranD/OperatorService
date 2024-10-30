package ru.medgrand.OperatorService.Infrastructure;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.medgrand.OperatorService.Model.UserPassword;

@Repository
public interface UserPasswordRepository extends CrudRepository<UserPassword, Long> {
}
