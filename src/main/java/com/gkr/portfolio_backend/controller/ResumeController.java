package com.gkr.portfolio_backend.controller;

import com.gkr.portfolio_backend.model.Resume;
import com.gkr.portfolio_backend.service.ResumeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;

@RestController
@RequestMapping("/resume")
@CrossOrigin(origins = "*")
public class ResumeController {

    @Autowired
    private ResumeService resumeService;

    // Upload endpoint (used by you via Postman/Dashboard to refresh your CV)
    @PostMapping("/upload")
    public ResponseEntity<Resume> uploadResumeFile(@RequestParam("file") MultipartFile file) throws IOException {
        return ResponseEntity.ok(resumeService.uploadResume(file));
    }

    // Download endpoint (called by the recruiter when clicking "Download CV")
    @GetMapping("/download")
    public ResponseEntity<byte[]> downloadResumeFile() {
        Resume resume = resumeService.getLatestResume();

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(resume.getFileType()))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resume.getFileName() + "\"")
                .body(resume.getFileData());
    }
}