package com.gkr.portfolio_backend.service.impl;
import com.gkr.portfolio_backend.model.Experience;
import com.gkr.portfolio_backend.repository.ExperienceRepository;
import com.gkr.portfolio_backend.service.ExperienceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class ExperienceServiceImpl implements ExperienceService {

    @Autowired
    private ExperienceRepository experienceRepository;

    @Override
    public List<Experience> getAllExperiences() {
        return experienceRepository.findAll();
    }

    @Override
    public Experience getExperienceById(String id) {
        return experienceRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Experience record not found with id: " + id));
    }

    @Override
    public Experience saveExperience(String role, String companyName, String duration,
                                     List<String> descriptionPoints, MultipartFile logoFile) throws IOException {
        Experience exp = new Experience();
        exp.setRole(role);
        exp.setCompanyName(companyName);
        exp.setDuration(duration);
        exp.setDescriptionPoints(descriptionPoints);

        if (logoFile != null && !logoFile.isEmpty()) {
            exp.setCompanyLogo(logoFile.getBytes());
            exp.setLogoFileType(logoFile.getContentType());
        }

        return experienceRepository.save(exp);
    }
}