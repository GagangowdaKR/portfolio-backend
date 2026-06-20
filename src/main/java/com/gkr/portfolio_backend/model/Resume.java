package com.gkr.portfolio_backend.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "resumes")
public class Resume {
    @Id
    private String id;
    private String fileName;
    private byte[] fileData;       // Stores the raw PDF bytes
    private String fileType;       // Always "application/pdf"
    private LocalDateTime updatedAt = LocalDateTime.now();
}