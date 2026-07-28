package com.gkr.portfolio_backend.util;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

/**
 * Core, production-grade Mailing Utility.
 * Designed to be completely modular and agnostic of business logic
 * for seamless migration to microservices or other standalone projects.
 */
@Component
public class MailUtils {

    @Autowired
    private JavaMailSender mailSender;

    /**
     * Dispatches a standard text email.
     *
     * @param fromAddress The sender email address field (configured user)
     * @param toAddress   The destination inbox address
     * @param subject     The subject line of the email
     * @param bodyText    The complete raw message body payload
     * @throws Exception  Bubbles up any SMTP network drops or authentication issues
     */
    public void sendSimpleEmail(String fromAddress, String toAddress, String subject, String bodyText) throws Exception {
        SimpleMailMessage message = new SimpleMailMessage();

        message.setFrom(fromAddress);
        message.setTo(toAddress);
        message.setSubject(subject);
        message.setText(bodyText);

        // Fires the email through the configured JavaMailSender pool
        mailSender.send(message);
    }


    public void sendHtmlEmail(String fromAddress, String toAddress, String subject, String htmlBody)
            throws MessagingException {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true, "UTF-8");

        helper.setFrom(fromAddress);
        helper.setTo(toAddress);
        helper.setSubject(subject);
        helper.setText(htmlBody, true); // true => treat as HTML

        mailSender.send(mimeMessage);
    }
}