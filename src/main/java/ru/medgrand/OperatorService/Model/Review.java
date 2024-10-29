package ru.medgrand.OperatorService.Model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name="Reviews")
@Data
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @ManyToOne
    private User user;

    @Column(name="stars")
    private Long stars;

    @Column(name="review")
    private String review;

}
