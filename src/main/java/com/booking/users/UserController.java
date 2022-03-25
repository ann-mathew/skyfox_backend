package com.booking.users;

import com.booking.users.view.models.ChangePasswordRequest;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.booking.handlers.models.ErrorResponse;
import com.booking.users.Exceptions.UserAlreadyExistException;
import com.booking.users.view.models.SignUpRequest;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
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

        if (!user.getPassword().equals(changePasswordRequest.getOldPassword())) {
            response.put("status", false);
            response.put("msg", "Incorrect Old Password");
            return response;
        }

        if (userPrincipalService.checkWithPasswordHistory(user.getUsername(), changePasswordRequest.getNewPassword())) {
            response.put("status", false);
            response.put("msg", "New password should not match with last 3 passwords");
            return response;
        }

        userPrincipalService.updateUserPassword(changePasswordRequest.getNewPassword(), user);

        response.put("status", true);
        response.put("msg", "Password Updated Successfully");

        return response;
    }

    @PostMapping(path = "/signup", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Create a user")
    @ResponseStatus(code = HttpStatus.CREATED)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Created user successfully"),
            @ApiResponse(code = 404, message = "Record not found", response = ErrorResponse.class),
            @ApiResponse(code = 400, message = "Server cannot process request due to client error", response = ErrorResponse.class),
            @ApiResponse(code = 500, message = "Something failed in the server", response = ErrorResponse.class),
            @ApiResponse(code = 600, message = "User already exists", response = UserAlreadyExistException.class)
    })
    String signup(@Valid @RequestBody SignUpRequest signUpRequest, HttpServletResponse response) {
        try{
            userPrincipalService.create(signUpRequest);
            return "User Saved Successfully";
        } catch (UserAlreadyExistException userAlreadyExistException) {
            response.setStatus(600);
            return userAlreadyExistException.message();
        }
    }
}
