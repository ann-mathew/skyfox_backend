package com.booking.users;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "usertable")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonProperty
    @NotBlank(message = "User name must be provided")
    @Column(nullable = false, unique = true)
    @ApiModelProperty(name = "username", value = "Name of user (must be unique)", required = true, example = "user_name", position = 1)
    private String username;

    @JsonProperty
    @NotBlank(message = "Password name must be provided")
    @Column(nullable = false)
    @ApiModelProperty(name = "password", value = "Password of the user", required = true, example = "password", position = 2)
    private String password;

    @JsonProperty
    @NotBlank(message = "Name must be provided")
    @Column(nullable = false)
    @ApiModelProperty(name = "name", value = "Name of the user", required = true, example = "name", position = 3)
    private String name;

    public User() {
    }

    public User(String username, String password, String name) {
        this.username = username;
        this.password = password;
        this.name = name;
    }

    public Long getId() {
        return id;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
