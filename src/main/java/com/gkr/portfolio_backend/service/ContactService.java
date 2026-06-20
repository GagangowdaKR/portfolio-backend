package com.gkr.portfolio_backend.service;

import com.gkr.portfolio_backend.model.Contact;

import java.util.List;

public interface ContactService {

    Contact save(Contact contact);

    Contact saveAndNotify(Contact contact);

    List<Contact> getAllMessages();
}