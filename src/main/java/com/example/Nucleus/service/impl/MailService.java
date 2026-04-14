package com.example.Nucleus.service.impl;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class MailService {

    @Autowired
    private JavaMailSender mailSender;

    public void sendMail(String to, String subject, String body) throws MessagingException {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);

        helper.setTo(to);
        helper.setSubject(subject);

        String htmlContent = "<div style='font-family: Arial, sans-serif;'>"
                + "<h2 style='color: #4CAF50;'>" + subject + "</h2>"
                + "<p>" + body + "</p>"
                + "<hr>"
                + "<p style='font-size:12px;color:gray;'>This is an automated welcome email from Nucleus.</p>"
                + "</div>";
        helper.setText(htmlContent, true);

        mailSender.send(message);
    }
}
