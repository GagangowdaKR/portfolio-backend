package com.gkr.portfolio_backend.service.impl;

import com.gkr.portfolio_backend.model.CodingProfile;
import com.gkr.portfolio_backend.repository.CodingProfileRepository;
import com.gkr.portfolio_backend.service.CodingProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class CodingProfileServiceImpl implements CodingProfileService {

    @Autowired
    private CodingProfileRepository codingProfileRepository;

    @Override
    public List<CodingProfile> getAllProfiles() {
        return codingProfileRepository.findAll();
    }

    @Override
    public CodingProfile getProfileById(String id) {
        return codingProfileRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Coding profile not found with id: " + id));
    }

    @Override
    public CodingProfile saveProfile(String platformName, String profileLink, Integer questionsSolved, String globalRanking, MultipartFile iconFile) throws IOException {
        CodingProfile profile = new CodingProfile();
        profile.setPlatformName(platformName);
        profile.setProfileLink(profileLink);
        profile.setQuestionsSolved(questionsSolved);
        profile.setGlobalRanking(globalRanking);

        if (iconFile != null && !iconFile.isEmpty()) {
            profile.setPlatformIcon(iconFile.getBytes());
            profile.setIconFileType(iconFile.getContentType());
        }

        return codingProfileRepository.save(profile);
    }
}