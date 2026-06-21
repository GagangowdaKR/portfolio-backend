package com.gkr.portfolio_backend.service.impl;

import com.gkr.portfolio_backend.model.Contact;
import com.gkr.portfolio_backend.model.Resume;
import com.gkr.portfolio_backend.repository.ResumeRepository;
import com.gkr.portfolio_backend.service.ContactService;
import com.gkr.portfolio_backend.service.ResumeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Service
@Slf4j
public class ResumeServiceImpl implements ResumeService {

    private final ResumeRepository resumeRepository;
    private final ContactService contactService;
    private static Integer RESUME_DOWNLOAD_COUNT = 0;

    public ResumeServiceImpl(ResumeRepository resumeRepository,
                             ContactService contactService) {
        this.resumeRepository = resumeRepository;
        this.contactService = contactService;
    }

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
            log.info("No files were found in database, Resumes List count : {}", resumes.size());
        }

        log.info("Resume downloaded {}", ++RESUME_DOWNLOAD_COUNT);

        return resumes.get(0);
    }

    @Override
    public boolean provideResumeRequest(Contact contact) {
        if (Objects.isNull(contact)) {
            return false;
        }
        if (Objects.isNull(contact.getName())) {
            return false;
        }
        if (Objects.isNull(contact.getProfession())){
            return false;
        }
        contact.setIsResumeRequest(Boolean.TRUE);
        Contact savedContact = contactService.save(contact);
        log.info("Resume request by : {} - {}", savedContact.getName(), savedContact.getProfession());
        return true;
    }
}