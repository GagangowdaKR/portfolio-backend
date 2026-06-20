package com.gkr.portfolio_backend.service.impl;

import com.gkr.portfolio_backend.model.Project;
import com.gkr.portfolio_backend.repository.ProjectRepository;
import com.gkr.portfolio_backend.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class ProjectServiceImpl implements ProjectService {

    @Autowired
    private ProjectRepository projectRepository;

    @Override
    public List<Project> getAllProjects() {
        return projectRepository.findAll();
    }

    @Override
    public Project getProjectById(String id) {
        return projectRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Project not found with id: " + id));
    }

    @Override
    public Project saveProject(String title, String description, List<String> techStack,
                               String githubLink, String liveLink, boolean featured,
                               MultipartFile imageFile) throws IOException {
        Project project = new Project();
        project.setTitle(title);
        project.setDescription(description);
        project.setTechStack(techStack);
        project.setGithubLink(githubLink);
        project.setLiveLink(liveLink);
        project.setFeatured(featured);

        if (imageFile != null && !imageFile.isEmpty()) {
            project.setProjectImage(imageFile.getBytes());
            project.setImageFileType(imageFile.getContentType());
        }

        return projectRepository.save(project);
    }
}