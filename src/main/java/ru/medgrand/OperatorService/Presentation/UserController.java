package ru.medgrand.OperatorService.Presentation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.medgrand.OperatorService.Application.UserService;
import ru.medgrand.OperatorService.Model.Role;
import ru.medgrand.OperatorService.Model.User;
import ru.medgrand.OperatorService.Presentation.Contracts.User.CreateUser;
import ru.medgrand.OperatorService.Presentation.Contracts.User.UpdateUser;

import java.util.List;

@RestController
public class UserController {

    private final UserService userService;

    @Autowired
    UserController(UserService userService){
        this.userService = userService;
    }

    @GetMapping("/users")
    public List<User> getAllUsers(
            @RequestParam(name="role", required = false, defaultValue = "nan") String role,
            @RequestParam(name="skip", required = false, defaultValue = "0") String skip,
            @RequestParam(name="limit", required = false, defaultValue = "2147483647") String limit
    ){

        List<User> returnList;
        if(!role.equals("nan")){
            returnList = this.userService.getAllUsersByRole(role);
        }
        else {
            returnList = this.userService.getAllUsers();
        }

        return returnList.stream()
                .skip(Long.parseLong(skip))
                .limit(Long.parseLong(limit))
                .toList();
    }

    @GetMapping("/users/{id}")
    public User getUserById(@PathVariable(name="id") long id){
        return this.userService.getUserById(id).orElse(null);
    }

    @GetMapping("/users/")
    public User getUserByUsername(@RequestParam(name="username") String username){
        return this.userService.getUserByUsername(username).orElse(null);
    }

    @PostMapping("/users")
    public User createUser(@RequestBody CreateUser request){
        Role role = new Role();
        role.setId(request.getRole_id());

        User user = new User();
        user.setUsername(request.getUsername());
        user.setRole(role);

        return this.userService.createUser(user).orElse(null);
    }

    @PutMapping("/users/{id}")
    public User updateUser(@PathVariable(name="id") long id, @RequestBody UpdateUser request){

        Role role = new Role();
        role.setId(request.getRole_id());

        User user = new User();
        user.setUsername(request.getUsername());
        user.setRole(role);
        user.setId(id);

        return this.userService.updateUser(user).orElse(null);

    }

    @DeleteMapping("/users/{id}")
    public User deleteUser(@PathVariable(name="id") long id){
        return this.userService.deleteUserById(id).orElse(null);
    }

}
