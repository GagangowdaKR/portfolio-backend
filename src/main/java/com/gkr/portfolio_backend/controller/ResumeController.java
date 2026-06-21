package com.gkr.portfolio_backend.controller;

import com.gkr.portfolio_backend.model.Contact;
import com.gkr.portfolio_backend.model.Resume;
import com.gkr.portfolio_backend.records.ResponseMessage;
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
//@CrossOrigin(origins = "*")
public class ResumeController {

    @Autowired
    private ResumeService resumeService;

    @PostMapping("/request")
    public ResponseEntity<ResponseMessage> requestResume(@RequestBody Contact contact) {
        boolean access = resumeService.provideResumeRequest(contact);
        if (access) {
            return ResponseEntity.ok().body(new ResponseMessage("Resume request accepted"));
        }
        return ResponseEntity.badRequest().body(new ResponseMessage("Resume request rejected"));
    }

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