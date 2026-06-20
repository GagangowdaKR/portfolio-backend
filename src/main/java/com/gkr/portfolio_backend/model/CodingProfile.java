package com.gkr.portfolio_backend.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "coding_profiles")
public class CodingProfile {
    @Id
    private String id;
    private String platformName;
    private String profileLink;
    private byte[] platformIcon;
    private String iconFileType;
    private Integer questionsSolved;
    private String globalRanking;
}