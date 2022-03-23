package com.booking.users;

import com.booking.users.Exceptions.UserAlreadyExistException;
import com.booking.users.view.models.SignUpRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserPrincipalService implements UserDetailsService {
    private final UserRepository userRepository;

    @Autowired
    public UserPrincipalService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User savedUser = findUserByUsername(username);

        return new UserPrincipal(savedUser);
    }

    public User findUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }

    public void updateUserPassword(String newPassword, User user) {
        user.setPassword(newPassword);
        userRepository.save(user);
    }

    public void create(SignUpRequest signUpRequest) throws UserAlreadyExistException {
        Integer emailCount = userRepository.emailCount(signUpRequest.getEmail());
        if (emailCount >= 1) throw new UserAlreadyExistException("User Already Exists");
        userRepository.save(new User("NA", signUpRequest.getPassword(), signUpRequest.getName(),
                signUpRequest.getEmail(), signUpRequest.getPhone(), signUpRequest.getAgeGroup(), "Customer"));
    }
}
