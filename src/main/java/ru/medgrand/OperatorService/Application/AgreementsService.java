package ru.medgrand.OperatorService.Application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.medgrand.OperatorService.Infrastructure.AgreementsRepository;
import ru.medgrand.OperatorService.Model.Agreement;
import ru.medgrand.OperatorService.Model.Tarif;
import ru.medgrand.OperatorService.Model.User;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.StreamSupport;

@Service
public class AgreementsService {

    private final AgreementsRepository agreementsRepository;

    @Autowired
    AgreementsService(AgreementsRepository agreementsRepository){
        this.agreementsRepository = agreementsRepository;
    }

    List<Agreement> getAllAgreements(){
        return StreamSupport
                .stream(
                        this.agreementsRepository.findAll().spliterator(), false
                )
                .toList();
    }

    List<Agreement> getAllAgreementsByUser(User user){
        return this.agreementsRepository.findByUser(user);
    }

    List<Agreement> getAllAgreementsByTarif(Tarif tarif){
        return this.getAllAgreements()
                .stream()
                .filter(agreement -> {
                    return Objects.equals(agreement.getTarif().getCost(), tarif.getCost()) &&
                            Objects.equals(agreement.getTarif().getId(), tarif.getId()) &&
                            Objects.equals(agreement.getTarif().getSms(), tarif.getSms()) &&
                            Objects.equals(agreement.getTarif().getMinutes(), tarif.getMinutes()) &&
                            Objects.equals(agreement.getTarif().getWi_fi(), tarif.getWi_fi()) &&
                            Objects.equals(agreement.getTarif().getM_internet(), tarif.getM_internet());
                })
                .toList();
    }

    List<Agreement> getAgreementsUnderCreationDate(LocalDateTime date){
        return this.getAllAgreements()
                .stream()
                .filter(agreement -> agreement.getCreationDate().isBefore(date))
                .toList();
    }

    List<Agreement> getAgreementsUpperDate(LocalDateTime date){
        return this.getAllAgreements()
                .stream()
                .filter(agreement -> agreement.getCreationDate().isAfter(date))
                .toList();
    }

    Optional<Agreement> getAgreementById(Long id){
        return this.agreementsRepository.findById(id);
    }

    Optional<Agreement> createAgreement(Agreement agreement){
        this.agreementsRepository.save(agreement);
        return Optional.of(agreement);
    }

    Optional<Agreement> updateAgreement(Agreement agreement){
        if(!this.agreementsRepository.existsById(agreement.getId())){
            return Optional.empty();
        }

        this.agreementsRepository.save(agreement);
        return Optional.of(agreement);
    }

    Optional<Agreement> deleteAgreemment(Agreement agreement){
        if(!this.agreementsRepository.existsById(agreement.getId())){
            return Optional.empty();
        }

        this.agreementsRepository.delete(agreement);
        return Optional.of(agreement);
    }

    Optional<Agreement> deleteAgreemmentById(long id){
        if(!this.agreementsRepository.existsById(id)){
            return Optional.empty();
        }

        Agreement deleteAgreement = this.getAgreementById(id).get();
        this.agreementsRepository.deleteById(id);
        return Optional.of(deleteAgreement);
    }

}
