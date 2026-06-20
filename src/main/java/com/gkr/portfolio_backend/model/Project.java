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
@Document(collection = "projects")
public class Project {
    @Id
    private String id;
    private String title;
    private String description;
    private List<String> techStack;
    private String githubLink;
    private String liveLink;
    private boolean featured;

    private byte[] projectImage;      // Stores raw project screenshot bytes
    private String imageFileType;     // Stores MIME type (e.g., "image/jpeg")
}