package ru.medgrand.OperatorService.Presentation.Contracts.Review;

import lombok.Data;

@Data
public class UpdateReview {

    private Long stars;
    private String review;
    private long user_id;

}
