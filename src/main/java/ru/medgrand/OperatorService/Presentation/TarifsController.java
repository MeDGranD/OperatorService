package ru.medgrand.OperatorService.Presentation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.medgrand.OperatorService.Application.TarifsService;
import ru.medgrand.OperatorService.Model.Tarif;

import java.util.List;
import java.util.Optional;

@RestController
public class TarifsController {

    private final TarifsService tarifsService;

    @Autowired
    TarifsController(TarifsService tarifsService){
        this.tarifsService = tarifsService;
    }

    @GetMapping("/tarifs")
    public List<Tarif> getAllTarifs(
            @RequestParam(name="cost",
                    required=false,
                    defaultValue="2147483647") String underCost
    ){
        return this.tarifsService.getAllTarifsUnderCost(Long.parseLong(underCost));
    }

    @GetMapping("tarifs/{id}")
    public Tarif getTarifById(@PathVariable(name="id") long id){
        return this.tarifsService.getTarifById(id).orElse(null);
    }

}
