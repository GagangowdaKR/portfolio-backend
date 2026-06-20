package com.gkr.portfolio_backend.service;

import com.gkr.portfolio_backend.model.CodingProfile;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.util.List;

public interface CodingProfileService {
    List<CodingProfile> getAllProfiles();
    CodingProfile getProfileById(String id);
    CodingProfile saveProfile(String platformName, String profileLink, Integer questionsSolved, String globalRanking, MultipartFile iconFile) throws IOException;
}