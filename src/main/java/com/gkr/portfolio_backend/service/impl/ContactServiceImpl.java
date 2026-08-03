package com.gkr.portfolio_backend.service.impl;

import com.gkr.portfolio_backend.dto.MailPayload;
import com.gkr.portfolio_backend.dto.NotificationRequest;
import com.gkr.portfolio_backend.dto.NotificationType;
import com.gkr.portfolio_backend.model.Contact;
import com.gkr.portfolio_backend.repository.ContactRepository;
import com.gkr.portfolio_backend.service.ContactService;
import com.gkr.portfolio_backend.util.MailUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
@Slf4j
public class ContactServiceImpl implements ContactService {

    @Autowired
    private ContactRepository contactRepository;

    @Value("${spring.mail.username}")
    private String emailFrom;

    @Value("${gkr.util.mail}")
    private String selfEmail;

    @Value("${gkr.notification.service.url}")
    private String notificationServiceUrl;

    private final RestTemplate restTemplate = new RestTemplate();

    @Override
    public Contact save(Contact contact) {
        return contactRepository.save(contact);
    }

    @Override
    @Transactional
    public Contact saveAndNotify(Contact contact) {

        /** 1. Save the Contacts Details In DB */
        contact.setIsContactRequest(Boolean.TRUE);
        contact.setSubject(Contact.Subject.CONTACT_REQUEST.toString());
        contact.setPhone(contact.getPhone());
        Contact savedContact = save(contact);
        log.info("Saved contact with id {}, name {}", savedContact.getId(), savedContact.getName().toUpperCase());

        MailPayload mailPayload = createMailPayload(contact);

        /** 2. Send Contact request mail to SELF */
        try {
            NotificationRequest selfMailRequest = new NotificationRequest(
                    NotificationType.EMAIL,
                    selfEmail,
                    mailPayload.toSelfEmailSubject(),
                    mailPayload.toSelfEmailBody()
            );
            restTemplate.postForEntity(notificationServiceUrl, selfMailRequest, String.class);
            log.info("Contact Information are mailed to Gagan.");
        } catch (Exception e) {
            System.err.println("Mailing execution failure via MailUtils record binding: " + e.getMessage());
        }

        /** 3. Send confirmation mail to visitors */
        /**
         *
         * Currently Tried in Render free tier which is not allowing SMTP port for production
         * (Local SMTP mails are sending only in render it is failing)
         *
         * End up with the Resend which will provides API Key
         * which is used to send the mail to specified "TO" address only
         * (For small case use cases like self notifications it suits. Not for the dynamic mails)
         */
//        try {
//            NotificationRequest visitorMailRequest = new NotificationRequest(
//                    NotificationType.EMAIL,
//                    contact.getEmail(),
//                    mailPayload.toConfirmEmailSubject(),
//                    mailPayload.toConfirmEmailBody()
//            );
//            restTemplate.postForEntity(notificationServiceUrl, visitorMailRequest, String.class);
//            log.info("Confirm email has been sent to {}.", contact.getName());
//        } catch (Exception e) {
//            System.err.println("Mailing execution failure via MailUtils record binding: " + e.getMessage());
//        }
        return savedContact;
    }

    private MailPayload createMailPayload(Contact contact) {
        return new MailPayload(
                contact.getName(),
                contact.getEmail(),
                Contact.Subject.CONTACT_REQUEST,
                contact.getProfession(),
                contact.getMessage()
        );
    }

    @Override
    public List<Contact> getAllMessages() {
        return contactRepository.findAll();
    }
}