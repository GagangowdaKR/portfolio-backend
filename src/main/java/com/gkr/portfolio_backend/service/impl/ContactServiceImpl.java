package com.gkr.portfolio_backend.service.impl;

import com.gkr.portfolio_backend.dto.MailPayload;
import com.gkr.portfolio_backend.model.Contact;
import com.gkr.portfolio_backend.repository.ContactRepository;
import com.gkr.portfolio_backend.service.ContactService;
import com.gkr.portfolio_backend.util.MailUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Slf4j
public class ContactServiceImpl implements ContactService {

    @Autowired
    private ContactRepository contactRepository;

    @Autowired
    private MailUtils mailUtils;

    @Value("${spring.mail.username}")
    private String emailFrom;

    @Value("${gkr.util.mail}")
    private String selfEmail;

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
        Contact savedContact = save(contact);
        log.info("Saved contact with id {}, name {}", savedContact.getId(), savedContact.getName().toUpperCase());

        MailPayload mailPayload = createMailPayload(contact);

        /** 2. Send Contact request mail to SELF */
        try {
            mailUtils.sendSimpleEmail(
                    emailFrom,
                    selfEmail,
                    mailPayload.toSelfEmailSubject(),
                    mailPayload.toSelfEmailBody()
            );
            log.info("Contact Information are mailed to Gagan.");
        } catch (Exception e) {
            System.err.println("Mailing execution failure via MailUtils record binding: " + e.getMessage());
        }

        /** 3. Send confirmation mail to visitors */
        try {
            mailUtils.sendHtmlEmail(
                    selfEmail,
                    contact.getEmail(),
                    mailPayload.toConfirmEmailSubject(),
                    mailPayload.toConfirmEmailBody()
            );
            log.info("Confirm email has been sent to {}.", contact.getName());
        } catch (Exception e) {
            System.err.println("Mailing execution failure via MailUtils record binding: " + e.getMessage());
        }
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