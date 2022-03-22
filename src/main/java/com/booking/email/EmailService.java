package com.booking.email;

import com.booking.users.UserRepository;
import com.sendgrid.*;
import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.Content;
import com.sendgrid.helpers.mail.objects.Email;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class EmailService {

    @Autowired
    UserRepository userRepository;

    public EmailService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public String sendEmail(String email) throws IOException {
        if (userRepository.findByEmailID(email) != null) {
            Email from = new Email("aditigupta1115@gmail.com");
            String subject = "Reset Password";
            Email to = new Email(email);
            Content content = new Content("text/plain", "Reset password Link: ");
            Mail mail = new Mail(from, subject, to, content);

            SendGrid sg = new SendGrid("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");
            Request request = new Request();
            try {
                request.setMethod(Method.POST);
                request.setEndpoint("mail/send");
                request.setBody(mail.build());
                Response response = sg.api(request);
                System.out.println(response.getStatusCode());
                System.out.println(response.getBody());
                System.out.println(response.getHeaders());
            } catch (
                    IOException ex) {
                System.out.println("In exception");
                ex.printStackTrace();
                throw ex;
            }
          return " Mail sent";
        }
        return "Not registered email ID";
    }
}

