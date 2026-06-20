package com.gkr.portfolio_backend.controller;


import com.gkr.portfolio_backend.dto.ApiResponse;
import com.gkr.portfolio_backend.model.Experience;
import com.gkr.portfolio_backend.service.ExperienceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/experiences")
@CrossOrigin(origins = "*")
public class ExperienceController {

    @Autowired
    private ExperienceService experienceService;

    @GetMapping
    public ResponseEntity<ApiResponse<List<Experience>>> getAllExperiences() {
        List<Experience> experiences = experienceService.getAllExperiences();
        return ResponseEntity.ok(ApiResponse.success("Experience retrieved successfully", experiences));
    }

    @GetMapping("/{id}/logo")
    public ResponseEntity<byte[]> getCompanyLogo(@PathVariable String id) {
        Experience exp = experienceService.getExperienceById(id);
        if (exp.getCompanyLogo() == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(exp.getLogoFileType()))
                .body(exp.getCompanyLogo());
    }

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Experience> createExperience(
            @RequestParam("role") String role,
            @RequestParam("companyName") String companyName,
            @RequestParam("duration") String duration,
            @RequestParam("descriptionPoints") List<String> descriptionPoints,
            @RequestParam(value = "logo", required = false) MultipartFile logo) throws IOException {

        return ResponseEntity.ok(experienceService.saveExperience(role, companyName, duration, descriptionPoints, logo));
    }
}