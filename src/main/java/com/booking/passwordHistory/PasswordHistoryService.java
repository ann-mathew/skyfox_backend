package com.booking.passwordHistory;

import com.booking.passwordHistory.repository.PasswordHistory;
import com.booking.passwordHistory.repository.PasswordHistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;

@Service
public class PasswordHistoryService {

    private final PasswordHistoryRepository passwordHistoryRepository;

    @Autowired
    public PasswordHistoryService(PasswordHistoryRepository passwordHistoryRepository) {
        this.passwordHistoryRepository = passwordHistoryRepository;
    }

    public boolean checkWithPasswordHistory(String username, String newPassword) {
        List<PasswordHistory> passwordHistoryList = passwordHistoryRepository.findByUsername(username);
        Long numberOfPasswordMatch = passwordHistoryList
                .stream()
                .map(passwordHistory -> passwordHistory.getPassword())
                .filter(password -> password.equals(newPassword))
                .count();
        return numberOfPasswordMatch > 0;
    }

    public void addUserPassword(String username, String password) {
        PasswordHistory passwordHistory = new PasswordHistory(username, password, currentTimestamp());
        passwordHistoryRepository.save(passwordHistory);
    }

    private Timestamp currentTimestamp() {
        return Timestamp.from(Instant.now());
    }
}
