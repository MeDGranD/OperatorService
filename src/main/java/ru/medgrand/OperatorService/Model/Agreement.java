package ru.medgrand.OperatorService.Model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Table(name="Agreements")
@Data
public class Agreement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @ManyToOne
    private User user;

    @ManyToOne
    private Tarif tarif;

    @Column(name="creation_date")
    private LocalDateTime creationDate;

    @Column(name="end_date")
    private LocalDateTime endTime;

}
