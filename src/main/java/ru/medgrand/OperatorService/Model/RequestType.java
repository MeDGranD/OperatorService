package ru.medgrand.OperatorService.Model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name="RequestTypes")
@Data
public class RequestType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long Id;

    @Column(name = "type")
    private String type;

}
