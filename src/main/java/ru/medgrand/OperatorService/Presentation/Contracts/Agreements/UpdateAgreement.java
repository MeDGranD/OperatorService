package ru.medgrand.OperatorService.Presentation.Contracts.Agreements;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class UpdateAgreement {

    private Long Id;
    private long user_id;
    private long tarif_id;
    private LocalDateTime creationDate;
    private LocalDateTime endTime;

}
