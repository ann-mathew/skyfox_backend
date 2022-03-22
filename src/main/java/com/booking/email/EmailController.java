package com.booking.email;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;


import java.io.IOException;

@RestController
public class EmailController {
    @Autowired
    EmailService emailservice;

    @GetMapping("/reset/{email}")
    public String sendEmail(@PathVariable(value="email", required = true) String email) throws IOException {
        return emailservice.sendEmail(email);
    }

}