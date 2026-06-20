package com.gkr.portfolio_backend.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "experiences")
public class Experience {
    @Id
    private String id;
    private String role;
    private String companyName;
    private String duration;
    private List<String> descriptionPoints; // Handles markdown bullet highlights

    private byte[] companyLogo;       // Binary corporate logo image
    private String logoFileType;      // MIME tracking string (e.g., "image/png")
}