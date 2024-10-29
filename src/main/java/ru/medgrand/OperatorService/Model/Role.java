package ru.medgrand.OperatorService.Model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name="Roles")
@Data
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long Id;

    @Column(name="role")
    private String role;

}
