package ru.medgrand.OperatorService.Presentation.Contracts.Requests;

import lombok.Data;

@Data
public class CreateRequest {

    private Long Id;
    private long user_id;
    private long type_id;
    private String description;

}
