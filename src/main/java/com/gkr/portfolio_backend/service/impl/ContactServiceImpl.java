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
    private String configuredEmail;

    @Override
    public Contact save(Contact contact) {
        return contactRepository.save(contact);
    }

    @Override
    public Contact saveAndNotify(Contact contact) {
        // 1. Permanently back up form into MongoDB Atlas
        Contact savedContact = contactRepository.save(contact);

        // 2. Bind parameters tightly into your clean Java Record contract
        MailPayload mailPayload = new MailPayload(
                contact.getName(),
                contact.getEmail(),
                contact.getSubject(),
                contact.getMessage()
        );

        // 3. Delegate execution directly to the Mail utility using the record bindings
        try {
            mailUtils.sendSimpleEmail(
                    configuredEmail,
                    configuredEmail,
                    mailPayload.toEmailSubject(), // Evaluates the record subject formatting
                    mailPayload.toEmailBody()     // Evaluates the record text block parsing
            );
        } catch (Exception e) {
            System.err.println("Mailing execution failure via MailUtils record binding: " + e.getMessage());
        }

        return savedContact;
    }

    @Override
    public List<Contact> getAllMessages() {
        return contactRepository.findAll();
    }
}