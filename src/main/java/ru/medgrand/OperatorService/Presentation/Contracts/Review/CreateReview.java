package ru.medgrand.OperatorService.Presentation.Contracts.Review;

import lombok.Data;

@Data
public class CreateReview {

    private Long stars;
    private String review;
    private long user_id;

}
