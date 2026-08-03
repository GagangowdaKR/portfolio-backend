package com.gkr.portfolio_backend.dto;

public record NotificationRequest(
        NotificationType type,      // "EMAIL"
        String recipient, // destination email address
        String subject,   // mail subject
        String body       // HTML or plain text body
) {
}

