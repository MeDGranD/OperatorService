package ru.medgrand.OperatorService.Model;


import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name="UserPassword")
@Data
public class UserPassword {

    @Id
    @Column(name="user_id")
    private Long u_id;

    @Column(name="password")
    private String password;

    @OneToOne
    @MapsId
    @JoinColumn(name="user_id")
    private User user;

}
