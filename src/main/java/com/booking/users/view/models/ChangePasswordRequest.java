package com.booking.users.view.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("Change Password Request")
public class ChangePasswordRequest {

    @JsonProperty
    @ApiModelProperty(name = "oldPassword", position = 1)
    private String oldPassword;

    @JsonProperty
    @ApiModelProperty(name = "newPassword", position = 2)
    private String newPassword;

    public String getOldPassword() {
        return oldPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }
}
