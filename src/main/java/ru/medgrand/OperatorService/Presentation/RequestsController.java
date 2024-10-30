package ru.medgrand.OperatorService.Presentation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.medgrand.OperatorService.Application.RequestsService;
import ru.medgrand.OperatorService.Model.Request;
import ru.medgrand.OperatorService.Model.RequestType;
import ru.medgrand.OperatorService.Model.User;
import ru.medgrand.OperatorService.Presentation.Contracts.Requests.CreateRequest;
import ru.medgrand.OperatorService.Presentation.Contracts.Requests.UpdateRequest;

import java.util.List;

@RestController
public class RequestsController {

    private final RequestsService requestsService;

    @Autowired
    RequestsController(RequestsService requestsService){
        this.requestsService = requestsService;
    }

    @GetMapping("/requests")
    public List<Request> getAllRequests(
            @RequestParam(name="skip", required = false, defaultValue = "0") String skip,
            @RequestParam(name="limit", required = false, defaultValue = "2147483647") String limit,
            @RequestParam(name="userId", required = false, defaultValue = "nan") String user_id,
            @RequestParam(name="type", required = false, defaultValue = "nan") String type
    ){

        return this.requestsService.getAllRequests().stream()
                .filter(request -> {
                    return (user_id.equals("nan") || request.getUser().getId().equals(Long.parseLong(user_id))) &&
                            (type.equals("nan") || request.getType().getType().equals(type));
                })
                .skip(Long.parseLong(skip))
                .limit(Long.parseLong(limit))
                .toList();

    }

    @GetMapping("/requests/{id}")
    public Request getRequestById(@PathVariable(name="id") long id){
        return this.requestsService.getRequestById(id).orElse(null);
    }

    @PostMapping("/requests")
    public Request createRequest(@RequestBody CreateRequest createRequest){

        User user = new User();
        user.setId(createRequest.getUser_id());

        RequestType type = new RequestType();
        type.setId(createRequest.getType_id());

        Request request = new Request();
        request.setUser(user);
        request.setType(type);
        request.setDescription(createRequest.getDescription());

        return this.requestsService.createRequest(request).orElse(null);
    }

    @PutMapping("/requests/{id}")
    public Request createRequest(@PathVariable(name="id") long id, @RequestBody UpdateRequest updateRequest){

        User user = new User();
        user.setId(updateRequest.getUser_id());

        RequestType type = new RequestType();
        type.setId(updateRequest.getType_id());

        Request request = new Request();
        request.setId(id);
        request.setUser(user);
        request.setType(type);
        request.setDescription(updateRequest.getDescription());

        return this.requestsService.updateRequest(request).orElse(null);
    }

    @DeleteMapping("/requests/{id}")
    public Request deleteRequest(@PathVariable(name="id") long id){
        return this.requestsService.deleteRequestById(id).orElse(null);
    }

}
