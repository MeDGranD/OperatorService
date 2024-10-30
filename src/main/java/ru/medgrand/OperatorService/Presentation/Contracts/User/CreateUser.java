package ru.medgrand.OperatorService.Presentation.Contracts.User;

import lombok.Data;

@Data
public class CreateUser {

    private String username;
    private long role_id;
}
