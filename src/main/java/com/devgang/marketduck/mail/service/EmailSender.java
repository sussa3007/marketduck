package com.devgang.marketduck.mail.service;

import com.devgang.marketduck.mail.EmailSendable;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.MailSendException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailSender {

    private final JavaMailSender mailSender;

    private final EmailSendable emailSendable;

    public void sendEmail(String[] to, String subject, String message, String templateName) throws MailSendException,
            InterruptedException {
        emailSendable.send(to, subject, message, templateName);
    }
}