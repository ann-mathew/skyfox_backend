package com.booking.users;

import com.booking.passwordHistory.PasswordHistoryService;
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
    private final PasswordHistoryService passwordHistoryService;

    @Autowired
    public UserPrincipalService(UserRepository userRepository, PasswordHistoryService passwordHistoryService) {
        this.userRepository = userRepository;
        this.passwordHistoryService = passwordHistoryService;
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
        passwordHistoryService.addUserPassword(user.getUsername(), user.getPassword());
        addUser(user);
    }

    public void create(SignUpRequest signUpRequest) throws UserAlreadyExistException {
        Integer emailCount = userRepository.emailCount(signUpRequest.getEmail());
        if (emailCount >= 1) throw new UserAlreadyExistException("User Already Exists");
        addUser(new User("NA", signUpRequest.getPassword(), signUpRequest.getName(),
                signUpRequest.getEmail(), signUpRequest.getPhone(), signUpRequest.getAgeGroup(), "Customer"));
    }

    public boolean checkWithPasswordHistory(String username, String newPassword) {
        return passwordHistoryService.checkWithPasswordHistory(username, newPassword);
    }

    private User addUser(User user) {
        return userRepository.save(user);
    }
}
