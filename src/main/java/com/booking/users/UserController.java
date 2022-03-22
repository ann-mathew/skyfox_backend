package com.booking.users;

import com.booking.users.view.models.ChangePasswordRequest;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.HashMap;
import java.util.Map;

@Api(tags = "Users")
@RestController
public class UserController {

    private final UserPrincipalService userPrincipalService;

    @Autowired
    public UserController(UserPrincipalService userPrincipalService) {
        this.userPrincipalService = userPrincipalService;
    }

    @GetMapping("/login")
    Map<String, Object> login(Principal principal) {
        String username = principal.getName();
        Map<String, Object> userDetails = new HashMap<>();
        userDetails.put("username", username);
        return userDetails;
    }

    @PutMapping("/changePassword")
    Map<String, Object> changePassword(Principal principal, @RequestBody ChangePasswordRequest changePasswordRequest) {
        String username = principal.getName();
        User user = userPrincipalService.findUserByUsername(username);
        Map<String, Object> response = new HashMap<>();

        if(!user.getPassword().equals(changePasswordRequest.getOldPassword())) {
            response.put("status", false);
            response.put("msg", "Incorrect Old Password");
            return response;
        }

        userPrincipalService.updateUserPassword(changePasswordRequest.getNewPassword(), user);

        response.put("status", true);
        response.put("msg", "Password Updated Successfully");

        return response;
    }
}
