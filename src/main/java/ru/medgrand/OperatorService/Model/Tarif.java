package ru.medgrand.OperatorService.Model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name="Tarifs")
@Data
public class Tarif {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long Id;

    @Column(name="cost")
    private Long cost;

    @Column(name="mobile internet")
    private Long m_internet;

    @Column(name="wi-fi")
    private Long wi_fi;

    @Column(name="sms")
    private Long sms;

    @Column(name="minutes")
    private Long minutes;

}
