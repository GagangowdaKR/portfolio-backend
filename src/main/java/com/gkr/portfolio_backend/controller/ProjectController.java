package com.gkr.portfolio_backend.controller;

import com.gkr.portfolio_backend.dto.ApiResponse;
import com.gkr.portfolio_backend.model.Project;
import com.gkr.portfolio_backend.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/projects")
public class ProjectController {

    @Autowired
    private ProjectService projectService;

    @GetMapping
    public ResponseEntity<ApiResponse<List<Project>>> getAllProjects() {
        List<Project> projects = projectService.getAllProjects();
        return ResponseEntity.ok(ApiResponse.success("Projects retrieved successfully", projects));
    }

    @GetMapping("/{id}/image")
    public ResponseEntity<byte[]> getProjectImage(@PathVariable String id) {
        Project project = projectService.getProjectById(id);
        if (project.getProjectImage() == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(project.getImageFileType()))
                .body(project.getProjectImage());
    }

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Project> createProject(
            @RequestParam("title") String title,
            @RequestParam("description") String description,
            @RequestParam("techStack") List<String> techStack,
            @RequestParam("githubLink") String githubLink,
            @RequestParam("liveLink") String liveLink,
            @RequestParam("featured") boolean featured,
            @RequestParam(value = "image", required = false) MultipartFile image) throws IOException {

        return ResponseEntity.ok(projectService.saveProject(title, description, techStack, githubLink, liveLink, featured, image));
    }
}