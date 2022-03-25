package com.booking;

import com.booking.passwordHistory.repository.PasswordHistory;
import com.booking.passwordHistory.repository.PasswordHistoryRepository;
import com.booking.users.User;
import com.booking.users.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.sql.Timestamp;
import java.time.Instant;

@Configuration
public class DataSeeder {

    @Bean
    CommandLineRunner initDatabase(UserRepository repository, PasswordHistoryRepository passwordHistoryRepository) {
        return args -> {
            if (repository.findByUsername("seed-user-1").isEmpty()) {
                repository.save(new User("seed-user-1", "foobar", "name","abcd@gmail.com", "2454634623", "12","admin"));
            }
            if (repository.findByUsername("seed-user-2").isEmpty()) {
                repository.save(new User("seed-user-2", "foobar",  "name","abcd@gmail.com", "2454634623", "12","admin"));
            }
            if (passwordHistoryRepository.findByUsername("seed-user-1").isEmpty()) {
                passwordHistoryRepository.save(new PasswordHistory("seed-user-1", "foobar", Timestamp.from(Instant.now())));
            }
            if (passwordHistoryRepository.findByUsername("seed-user-2").isEmpty()) {
                passwordHistoryRepository.save(new PasswordHistory("seed-user-2", "foobar", Timestamp.from(Instant.now())));
            }
        };
    }
}
