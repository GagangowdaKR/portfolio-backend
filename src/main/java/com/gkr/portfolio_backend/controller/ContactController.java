package com.gkr.portfolio_backend.controller;

import com.gkr.portfolio_backend.model.Contact;
import com.gkr.portfolio_backend.service.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/contact")
public class ContactController {

    @Autowired
    private ContactService contactService;

    @PostMapping
    public ResponseEntity<Contact> submitContactForm(@RequestBody Contact contact) {
        return ResponseEntity.ok(contactService.saveAndNotify(contact));
    }

    @GetMapping
    public ResponseEntity<List<Contact>> viewAllMessages() {
        return ResponseEntity.ok(contactService.getAllMessages());
    }

}