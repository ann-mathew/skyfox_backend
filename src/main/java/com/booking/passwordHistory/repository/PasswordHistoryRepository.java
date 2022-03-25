package com.booking.passwordHistory.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PasswordHistoryRepository extends JpaRepository<PasswordHistory, Long> {

    PasswordHistory save(PasswordHistory passwordHistory);

    @Query(value = "SELECT * FROM PASSWORD_HISTORY WHERE username=?1 ORDER BY date_created DESC LIMIT 3", nativeQuery = true)
    List<PasswordHistory> findByUsername(String username);
}
