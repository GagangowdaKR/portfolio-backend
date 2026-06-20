package com.gkr.portfolio_backend.service;

import com.gkr.portfolio_backend.model.Experience;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.util.List;

public interface ExperienceService {
    List<Experience> getAllExperiences();
    Experience getExperienceById(String id);
    Experience saveExperience(String role, String companyName, String duration,
                              List<String> descriptionPoints, MultipartFile logoFile) throws IOException;
}