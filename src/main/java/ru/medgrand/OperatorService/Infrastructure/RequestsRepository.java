package ru.medgrand.OperatorService.Infrastructure;

import org.springframework.data.repository.CrudRepository;
import ru.medgrand.OperatorService.Model.Request;
import ru.medgrand.OperatorService.Model.User;

import java.util.List;

public interface RequestsRepository extends CrudRepository<Request, Long> {

    List<Request> findByUser(User user);

}
