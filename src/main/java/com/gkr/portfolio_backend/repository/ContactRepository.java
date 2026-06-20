package com.gkr.portfolio_backend.repository;

import com.gkr.portfolio_backend.model.Contact;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ContactRepository extends MongoRepository<Contact, String> {
}
