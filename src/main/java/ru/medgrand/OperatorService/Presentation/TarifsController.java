package ru.medgrand.OperatorService.Presentation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import ru.medgrand.OperatorService.Application.TarifsService;

@RestController
public class TarifsController {

    private final TarifsService tarifsService;

    @Autowired
    TarifsController(TarifsService tarifsService){
        this.tarifsService = tarifsService;
    }



}
