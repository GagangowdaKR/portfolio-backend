package com.gkr.portfolio_backend.service;

import com.gkr.portfolio_backend.model.Project;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.util.List;

public interface ProjectService {
    List<Project> getAllProjects();
    Project getProjectById(String id);
    Project saveProject(String title, String description, List<String> techStack,
                        String githubLink, String liveLink, boolean featured,
                        MultipartFile imageFile) throws IOException;
}