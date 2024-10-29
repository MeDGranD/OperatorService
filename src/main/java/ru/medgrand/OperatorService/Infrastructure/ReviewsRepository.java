package ru.medgrand.OperatorService.Infrastructure;

import org.springframework.data.repository.CrudRepository;
import ru.medgrand.OperatorService.Model.Review;
import ru.medgrand.OperatorService.Model.User;

import java.util.List;

public interface ReviewsRepository extends CrudRepository<Review, Long> {

    List<Review> findByUser(User user);

}
