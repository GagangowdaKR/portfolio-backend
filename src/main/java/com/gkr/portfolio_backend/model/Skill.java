package com.gkr.portfolio_backend.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "skills")
public class Skill {
    @Id
    private String id;
    private String category;
    private String skillName;
    private byte[] skillIcon;    // Direct binary storage for images
    private String iconFileType; // MIME tracking string (e.g., "image/png")
    private Integer displayOrder;
}