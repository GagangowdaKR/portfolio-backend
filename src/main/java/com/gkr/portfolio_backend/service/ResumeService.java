package com.gkr.portfolio_backend.service;

import com.gkr.portfolio_backend.model.Contact;
import com.gkr.portfolio_backend.model.Resume;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.IOException;

public interface ResumeService {
    Resume uploadResume(MultipartFile file) throws IOException;
    Resume getLatestResume();

    boolean provideResumeRequest(Contact contact);
}