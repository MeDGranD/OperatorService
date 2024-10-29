package ru.medgrand.OperatorService.Application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.medgrand.OperatorService.Infrastructure.RequestsRepository;
import ru.medgrand.OperatorService.Model.Request;
import ru.medgrand.OperatorService.Model.User;

import java.util.List;
import java.util.Optional;
import java.util.stream.StreamSupport;

@Service
public class RequestsService {

    private final RequestsRepository requestsRepository;

    @Autowired
    RequestsService(RequestsRepository requestsRepository){
        this.requestsRepository = requestsRepository;
    }

    List<Request> getAllRequests(){
        return StreamSupport
                .stream(
                        this.requestsRepository.findAll().spliterator(), false
                )
                .toList();
    }

    List<Request> getAllRequestsByUser(User user){
        return this.requestsRepository.findByUser(user);
    }

    Optional<Request> getRequestById(Long id){
        return this.requestsRepository.findById(id);
    }

    List<Request> getAllRequestsByType(String type){
        return this.getAllRequests()
                .stream()
                .filter(request -> request.getType().getType().equals(type))
                .toList();
    }

    Optional<Request> createRequest(Request request){
        this.requestsRepository.save(request);
        return Optional.of(request);
    }

    Optional<Request> updateRequest(Request request){

        if(!this.requestsRepository.existsById(request.getId())){
            return Optional.empty();
        }

        this.requestsRepository.save(request);
        return Optional.of(request);

    }

    Optional<Request> deleteRequest(Request request){
        if(!this.requestsRepository.existsById(request.getId())){
            return Optional.empty();
        }

        this.requestsRepository.delete(request);
        return Optional.of(request);
    }

    Optional<Request> deleteRequestById(long id){
        if(!this.requestsRepository.existsById(id)){
            return Optional.empty();
        }

        Request deleteRequest = this.getRequestById(id).get();
        this.requestsRepository.deleteById(id);
        return Optional.of(deleteRequest);
    }

}
