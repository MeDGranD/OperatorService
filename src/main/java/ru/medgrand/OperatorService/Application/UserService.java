package ru.medgrand.OperatorService.Application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.medgrand.OperatorService.Infrastructure.UserRepository;
import ru.medgrand.OperatorService.Model.User;

import java.util.List;
import java.util.Optional;
import java.util.stream.StreamSupport;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    public List<User> getAllUsers(){
        return StreamSupport
                .stream(
                        this.userRepository.findAll().spliterator(), false
                )
                .toList();
    }

    public Optional<User> getUserById(Long id){
        return this.userRepository.findById(id);
    }

    public Optional<User> getUserByUsername(String username){
        User user = this.userRepository.findByUsername(username);
        return user == null ? Optional.empty() : Optional.of(user);
    }

    public List<User> getAllUsersByRole(String role){
        return this.getAllUsers().stream()
                .filter(user -> user.getRole().getRole().equals(role))
                .toList();
    }

    public Optional<User> createUser(User user){
        this.userRepository.save(user);
        return Optional.of(user);
    }

    public Optional<User> updateUser(User user){

        if(!this.userRepository.existsById(user.getId())){
            return Optional.empty();
        }

        this.userRepository.save(user);
        return Optional.of(user);

    }

    public Optional<User> deleteUser(User user){

        if(!this.userRepository.existsById(user.getId())){
            return Optional.empty();
        }

        this.userRepository.delete(user);
        return Optional.of(user);

    }

    public Optional<User> deleteUserById(Long id){

        if(!this.userRepository.existsById(id)){
            return Optional.empty();
        }

        User returnUser = this.getUserById(id).get();
        this.userRepository.deleteById(id);
        return Optional.of(returnUser);

    }

}
