package com.example.Nucleus.listener;

import com.example.Nucleus.event.UserSignupActivityEvent;
import com.example.Nucleus.service.impl.MailService;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
public class UserSignupActivityListener {

    @Autowired
    private MailService mailService;

    @EventListener
    @Async
    public void sendWelcomeMain(UserSignupActivityEvent event) throws MessagingException {
        String to = event.getUser().getEmail();
        String subject = "Welcome to Nucleus \uD83D\uDCAB";

        String body = "Hi " + event.getUser().getDisplayName() + ",<br><br>"
                + "You have successfully signed up just now to <b>Nucleus</b>.<br><br>"
                + "We’re excited to have you onboard! 🎉<br><br>"
                + "Start your task management journey today by creating your first workspace and organizing your work efficiently.<br><br>"
                + "<b>Let’s get started!</b><br><br>"
                + "Best Regards,<br>"
                + "Team Nucleus";

        mailService.sendMail(to, subject , body);
    }
}
