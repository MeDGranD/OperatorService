package ru.medgrand.OperatorService.Security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import ru.medgrand.OperatorService.Infrastructure.UserPasswordRepository;
import ru.medgrand.OperatorService.Infrastructure.UserRepository;
import ru.medgrand.OperatorService.Model.User;

@Service
public class UserSecurityService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserPasswordRepository userPasswordRepository;
    @Autowired
    BCryptPasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = this.userRepository.findByUsername(username);

        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }

        String password = this.userPasswordRepository.findById(user.getId()).get().getPassword();

        OperatorUserDetails userDetails = new OperatorUserDetails();
        userDetails.setUser(user);
        userDetails.setPassword(password);

        return userDetails;
    }
}
