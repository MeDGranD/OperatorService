package ru.medgrand.OperatorService.Presentation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.medgrand.OperatorService.Application.AgreementsService;
import ru.medgrand.OperatorService.Model.Agreement;
import ru.medgrand.OperatorService.Model.Tarif;
import ru.medgrand.OperatorService.Model.User;
import ru.medgrand.OperatorService.Presentation.Contracts.Agreements.CreateAgreement;
import ru.medgrand.OperatorService.Presentation.Contracts.Agreements.UpdateAgreement;

import java.time.LocalDateTime;
import java.util.List;

@RestController
public class AgreementController {

    private final AgreementsService agreementsService;

    @Autowired
    AgreementController(AgreementsService agreementsService){
        this.agreementsService = agreementsService;
    }

    @GetMapping("/agreements")
    public List<Agreement> getAllAgreements(
            @RequestParam(name="skip", required = false, defaultValue = "0") String skip,
            @RequestParam(name="limit", required = false, defaultValue = "2147483647") String limit,
            @RequestParam(name="userId", required = false, defaultValue = "nan") String user_id,
            @RequestParam(name="tarif", required = false, defaultValue = "nan") String tarif,
            @RequestParam(name="under", required = false, defaultValue = "nan") String underDate,
            @RequestParam(name="after", required = false, defaultValue = "nan") String afterDate
    ){

        List<Agreement> agreements;

        if(user_id.equals("nan")){
            agreements = this.agreementsService.getAllAgreements();
        }
        else{
            User user = new User();
            user.setId(Long.parseLong(user_id));
            agreements = this.agreementsService.getAllAgreementsByUser(user);
        }

        return agreements.stream()
                .filter(agreement -> {
                    return (tarif.equals("nan") || agreement.getTarif().getId().equals(Long.parseLong(tarif))) &&
                            (underDate.equals("nan") || agreement.getCreationDate().isBefore(LocalDateTime.parse(underDate))) &&
                            (afterDate.equals("nan") || agreement.getEndTime().isAfter(LocalDateTime.parse(afterDate)));
                })
                .skip(Long.parseLong(skip))
                .limit(Long.parseLong(limit))
                .toList();
    }

    @GetMapping("/agreements/{id}")
    public Agreement getAgreementById(@PathVariable(name="id") long id){
        return this.getAgreementById(id);
    }

    @PostMapping("/agreements")
    public Agreement createAgreement(@RequestBody CreateAgreement request){

        User user = new User();
        user.setId(request.getUser_id());

        Tarif tarif = new Tarif();
        tarif.setId(request.getTarif_id());

        Agreement agreement = new Agreement();
        agreement.setUser(user);
        agreement.setCreationDate(request.getCreationDate());
        agreement.setTarif(tarif);
        agreement.setEndTime(request.getEndTime());

        return this.agreementsService.createAgreement(agreement).orElse(null);

    }

    @PutMapping("/agreements/{id}")
    public Agreement updateAgreement(@PathVariable(name="id") long id, @RequestBody UpdateAgreement request){

        User user = new User();
        user.setId(request.getUser_id());

        Tarif tarif = new Tarif();
        tarif.setId(request.getTarif_id());

        Agreement agreement = new Agreement();
        agreement.setId(id);
        agreement.setUser(user);
        agreement.setCreationDate(request.getCreationDate());
        agreement.setTarif(tarif);
        agreement.setEndTime(request.getEndTime());

        return this.agreementsService.updateAgreement(agreement).orElse(null);

    }

    @DeleteMapping("/agreements/{id}")
    public Agreement deleteAgreement(@PathVariable(name="id") long id){
        return this.agreementsService.deleteAgreemmentById(id).orElse(null);
    }

}
