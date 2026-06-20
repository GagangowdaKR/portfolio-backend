package com.gkr.portfolio_backend.service.impl;

import com.gkr.portfolio_backend.model.Resume;
import com.gkr.portfolio_backend.repository.ResumeRepository;
import com.gkr.portfolio_backend.service.ResumeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class ResumeServiceImpl implements ResumeService {

    @Autowired
    private ResumeRepository resumeRepository;

    @Override
    public Resume uploadResume(MultipartFile file) throws IOException {
        resumeRepository.deleteAll();

        Resume resume = new Resume();
        resume.setFileName(file.getOriginalFilename());
        resume.setFileData(file.getBytes());
        resume.setFileType(file.getContentType()); // Expecting "application/pdf"
        resume.setUpdatedAt(LocalDateTime.now());

        return resumeRepository.save(resume);
    }

    @Override
    public Resume getLatestResume() {
        List<Resume> resumes = resumeRepository.findAll();
        if (resumes.isEmpty()) {
            throw new RuntimeException("No resume file uploaded yet.");
        }
        return resumes.get(0);
    }
}