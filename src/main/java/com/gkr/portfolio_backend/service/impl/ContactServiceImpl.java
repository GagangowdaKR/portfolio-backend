package com.gkr.portfolio_backend.service.impl;

import com.gkr.portfolio_backend.dto.MailPayload;
import com.gkr.portfolio_backend.model.Contact;
import com.gkr.portfolio_backend.repository.ContactRepository;
import com.gkr.portfolio_backend.service.ContactService;
import com.gkr.portfolio_backend.util.MailUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
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
    public Contact saveAndNotify(Contact contact) {

        /** 1. Save the Contacts Details In DB */
        contact.setIsContactRequest(Boolean.TRUE);
        Contact savedContact = save(contact);

        MailPayload mailPayload = createMailPayload(contact);

        /** 2. Send Contact request mail to SELF */
        try {
            mailUtils.sendSimpleEmail(
                    emailFrom,
                    selfEmail,
                    mailPayload.toSelfEmailSubject(),
                    mailPayload.toSelfEmailBody()
            );
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
        } catch (Exception e) {
            System.err.println("Mailing execution failure via MailUtils record binding: " + e.getMessage());
        }
        return savedContact;
    }

    private MailPayload createMailPayload(Contact contact) {
        return new MailPayload(
                contact.getName(),
                contact.getEmail(),
                contact.getSubject(),
                contact.getProfession(),
                contact.getMessage()
        );
    }

    @Override
    public List<Contact> getAllMessages() {
        return contactRepository.findAll();
    }
}