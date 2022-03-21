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

    @JsonProperty
    @NotBlank(message = "Email must be provided")
    @Column(nullable = false)
    @ApiModelProperty(name = "email", value = "Age group of the user", required = true, example = "12", position = 4)
    private String email;

    @JsonProperty
    @NotBlank(message = "Phone number must be provided")
    @Column(nullable = false)
    @ApiModelProperty(name = "phone_number", value = "Phone number of the user", required = true, example = "2345678921", position = 5)
    private String phone_number;

    @JsonProperty
    @NotBlank(message = "Age group must be provided")
    @Column(nullable = false)
    @ApiModelProperty(name = "age_group", value = "Age group of the user", required = true, example = "12", position = 6)
    private String age_group;

    @JsonProperty
    @NotBlank(message = "Role must be provided")
    @Column(nullable = false)
    @ApiModelProperty(name = "role", value = "Role of the user", required = true, example = "admin", position = 7)
    private String role;

    public User() {
    }

    public User(String username, String password, String name, String email, String phone_number, String age_group, String role) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.email = email;
        this.phone_number = phone_number;
        this.age_group = age_group;
        this.role = role;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getAge_group() {
        return age_group;
    }

    public void setAge_group(String age_group) {
        this.age_group = age_group;
    }
}
