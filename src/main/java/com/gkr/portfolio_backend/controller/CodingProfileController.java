package com.gkr.portfolio_backend.controller;

import com.gkr.portfolio_backend.dto.ApiResponse;
import com.gkr.portfolio_backend.model.CodingProfile;
import com.gkr.portfolio_backend.service.CodingProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/coding")
@CrossOrigin(origins = "*")
public class CodingProfileController {

    @Autowired
    private CodingProfileService codingProfileService;

    @GetMapping
    public ResponseEntity<ApiResponse<List<CodingProfile>>> getAllProfiles() {
        List<CodingProfile> skills = codingProfileService.getAllProfiles();
        return ResponseEntity.ok(ApiResponse.success("Coding profiles retrieved successfully", skills));
    }

    @GetMapping("/{id}/icon")
    public ResponseEntity<byte[]> getProfileIcon(@PathVariable String id) {
        CodingProfile profile = codingProfileService.getProfileById(id);
        if (profile.getPlatformIcon() == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(profile.getIconFileType()))
                .body(profile.getPlatformIcon());
    }

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<CodingProfile> createProfile(
            @RequestParam("platformName") String platformName,
            @RequestParam("profileLink") String profileLink,
            @RequestParam("questionsSolved") Integer questionsSolved,
            @RequestParam("globalRanking") String globalRanking,
            @RequestParam("icon") MultipartFile icon) throws IOException {

        return ResponseEntity.ok(codingProfileService.saveProfile(platformName, profileLink, questionsSolved, globalRanking, icon));
    }
}