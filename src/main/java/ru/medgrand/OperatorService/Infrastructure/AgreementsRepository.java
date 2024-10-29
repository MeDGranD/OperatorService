package ru.medgrand.OperatorService.Infrastructure;

import org.springframework.data.repository.CrudRepository;
import ru.medgrand.OperatorService.Model.Agreement;
import ru.medgrand.OperatorService.Model.User;

import java.util.List;

public interface AgreementsRepository extends CrudRepository<Agreement, Long> {

    List<Agreement> findByUser(User user);

}
