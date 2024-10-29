package ru.medgrand.OperatorService.Infrastructure;

import org.springframework.data.repository.CrudRepository;
import ru.medgrand.OperatorService.Model.Tarif;

import java.util.List;

public interface TarifsRepository extends CrudRepository<Tarif, Long> {
}
