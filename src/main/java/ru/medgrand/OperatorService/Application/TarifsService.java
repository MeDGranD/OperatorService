package ru.medgrand.OperatorService.Application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.medgrand.OperatorService.Infrastructure.TarifsRepository;
import ru.medgrand.OperatorService.Model.Tarif;

import java.util.List;
import java.util.Optional;
import java.util.stream.StreamSupport;

@Service
public class TarifsService {

    private final TarifsRepository tarifsRepository;

    @Autowired
    TarifsService(TarifsRepository tarifsRepository){
        this.tarifsRepository = tarifsRepository;
    }

    Optional<Tarif> getTarifById(Long id){
        return this.tarifsRepository.findById(id);
    }

    List<Tarif> getAllTarifs(){
        return StreamSupport
                .stream(
                        this.tarifsRepository.findAll().spliterator(), false
                )
                .toList();
    }

    List<Tarif> getAllTarifsUnderCost(Long cost){

        List<Tarif> tarifs = this.getAllTarifs();

        return tarifs.stream()
                .filter(tarif -> tarif.getCost() < cost)
                .toList();

    }

}
