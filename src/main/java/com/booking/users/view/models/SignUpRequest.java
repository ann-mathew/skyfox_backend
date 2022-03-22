package com.booking.users.view.models;


import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;

public class SignUpRequest {

    @JsonProperty
    @ApiModelProperty(name = "name", value = "Name", required = true, position = 1)
    private String name;

    @JsonProperty
    @ApiModelProperty(name = "password", value = "Password", required = true, position = 2)
    private String password;

    @JsonProperty
    @ApiModelProperty(name = "email", value = "Email", required = true, position = 3)
    private String email;

    @JsonProperty
    @ApiModelProperty(name = "phone", value = "Phone", required = true, position = 4)
    private String phone;

    @JsonProperty
    @ApiModelProperty(name = "ageGroup", value = "Age", required = true, position = 5)
    private String ageGroup;

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public String getAgeGroup() {
        return ageGroup;
    }


    public SignUpRequest(String name, String password, String email, String phone, String ageGroup) {
        this.name = name;
        this.password = password;
        this.email = email;
        this.phone = phone;
        this.ageGroup = ageGroup;
    }

    public SignUpRequest() {
    }
}

