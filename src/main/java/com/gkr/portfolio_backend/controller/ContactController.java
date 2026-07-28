package com.gkr.portfolio_backend.controller;

import com.gkr.portfolio_backend.dto.ApiResponse;
import com.gkr.portfolio_backend.model.Contact;
import com.gkr.portfolio_backend.service.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/contact")
public class ContactController {

    @Autowired
    private ContactService contactService;

    @PostMapping("/request")
    public ResponseEntity<ApiResponse> submitContactForm(@RequestBody Contact contact) {
        contactService.saveAndNotify(contact);
        return ResponseEntity.ok(
                ApiResponse.builder()
                        .message("Quick Connect Request is Created !!")
                        .data(contact)
                        .timestamp(LocalDateTime.now())
                        .build()
        );
    }

    @GetMapping
    public ResponseEntity<List<Contact>> viewAllMessages() {
        return ResponseEntity.ok(contactService.getAllMessages());
    }

}