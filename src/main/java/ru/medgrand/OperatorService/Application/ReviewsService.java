package ru.medgrand.OperatorService.Application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.medgrand.OperatorService.Infrastructure.ReviewsRepository;
import ru.medgrand.OperatorService.Model.Review;
import ru.medgrand.OperatorService.Model.User;

import java.util.List;
import java.util.Optional;
import java.util.stream.StreamSupport;

@Service
public class ReviewsService {

    private final ReviewsRepository reviewsRepository;

    @Autowired
    ReviewsService(ReviewsRepository reviewsRepository){
        this.reviewsRepository = reviewsRepository;
    }

    public List<Review> getAllReviews(){

        return StreamSupport
                .stream(
                        this.reviewsRepository.findAll().spliterator(), false
                )
                .toList();

    }

    public List<Review> getAllReviewsByUser(User user){
        return this.reviewsRepository.findByUser(user);
    }

    public Optional<Review> getReviewById(Long id){
        return this.reviewsRepository.findById(id);
    }

    public List<Review> getAllReviewsByUpperStarts(long stars){
        return this.getAllReviews()
                .stream()
                .filter(review -> review.getStars() >= stars)
                .toList();
    }

    public Optional<Review> createReview(Review review){
        this.reviewsRepository.save(review);
        return Optional.of(review);
    }

    public Optional<Review> updateReview(Review review){
         if(!this.reviewsRepository.existsById(review.getId())){
             return Optional.empty();
         }

         this.reviewsRepository.save(review);
         return Optional.of(review);
    }

    public Optional<Review> deleteReview(Review review){
        if(!this.reviewsRepository.existsById(review.getId())){
            return Optional.empty();
        }

        this.reviewsRepository.delete(review);
        return Optional.of(review);
    }

    public Optional<Review> deleteReviewById(Long id){
        if(!this.reviewsRepository.existsById(id)){
            return Optional.empty();
        }

        Review deleteReview = this.getReviewById(id).get();
        this.reviewsRepository.deleteById(id);
        return Optional.of(deleteReview);
    }

}
