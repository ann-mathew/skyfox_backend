package com.booking.users;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
    User save(User user);


    @Query(value = "SELECT COUNT(EMAIL) FROM USERTABLE WHERE EMAIL=?1", nativeQuery = true)
    Integer emailCount(String email);
}
