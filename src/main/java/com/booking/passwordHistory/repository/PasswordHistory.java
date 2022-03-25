package com.booking.passwordHistory.repository;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;

@Entity
@Table(name = "password_history")
public class PasswordHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "User name must be provided")
    @Column(nullable = false)
    private String username;

    @NotBlank(message = "Password name must be provided")
    @Column(nullable = false)
    private String password;

    @NotNull(message = "Date must be provided")
    @Column(nullable = false)
    private Timestamp date_created;

    public PasswordHistory() {
    }

    public PasswordHistory(String username, String password, Timestamp date_created) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.date_created = date_created;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Timestamp getDate_created() {
        return date_created;
    }

    public void setDate_created(Timestamp date_created) {
        this.date_created = date_created;
    }
}
