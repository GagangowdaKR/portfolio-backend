package com.gkr.portfolio_backend.dto;

public record MailPayload(String name, String email, String subject, String message) {

    /**
     * Dynamically generates a clean, structured text body.
     * * @return Formatted multi-line email string layout.
     */
    public String toEmailBody() {
        return """
               You have received a new message from your portfolio website!
               
               👤 Name: %s
               📧 Email: %s
               
               💬 Message:
               %s
               """.formatted(name, email, message);
    }

    /**
     * Formats a standardized subject header for your inbox sorting.
     */
    public String toEmailSubject() {
        return "🚀 Portfolio Alert: " + subject;
    }
}