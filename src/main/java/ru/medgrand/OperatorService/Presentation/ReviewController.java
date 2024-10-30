package ru.medgrand.OperatorService.Presentation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.medgrand.OperatorService.Application.ReviewsService;
import ru.medgrand.OperatorService.Model.Review;
import ru.medgrand.OperatorService.Model.User;
import ru.medgrand.OperatorService.Presentation.Contracts.Review.CreateReview;
import ru.medgrand.OperatorService.Presentation.Contracts.Review.UpdateReview;

import java.util.List;

@RestController
public class ReviewController {

    private final ReviewsService reviewsService;

    @Autowired
    ReviewController(ReviewsService reviewsService){
        this.reviewsService = reviewsService;
    }

    @GetMapping("/reviews")
    public List<Review> getAllReviews(
            @RequestParam(name="stars",
                    required = false,
                    defaultValue = "0") String stars
    ){
        return this.reviewsService.getAllReviewsByUpperStarts(Long.parseLong(stars));
    }

    @GetMapping("/reviews/{id}")
    public Review getReviewById(@PathVariable(name="id") long id){
        return this.reviewsService.getReviewById(id).orElse(null);
    }

    @GetMapping("/users/{id}/reviews")
    public List<Review> getAllReviewsByUser(@PathVariable(name="id") long id){
        User user = new User();
        user.setId(id);
        return this.reviewsService.getAllReviewsByUser(user);
    }

    @PostMapping("/reviews")
    public Review createReview(@RequestBody CreateReview request){
        User user = new User();
        user.setId(request.getUser_id());

        Review review = new Review();
        review.setUser(user);
        review.setStars(request.getStars());
        review.setReview(request.getReview());

        return this.reviewsService.createReview(review).orElse(null);

    }

    @PutMapping("/reviews/{id}")
    public Review updateReview(@PathVariable(name="id") long id, @RequestBody UpdateReview request){
        User user = new User();
        user.setId(request.getUser_id());

        Review review = new Review();
        review.setUser(user);
        review.setStars(request.getStars());
        review.setReview(request.getReview());
        review.setId(id);

        return this.reviewsService.updateReview(review).orElse(null);
    }

    @DeleteMapping("/reviews/{id}")
    public Review deleteReviewById(@PathVariable(name = "id") long id){
        return this.reviewsService.deleteReviewById(id).orElse(null);
    }

}
