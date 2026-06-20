package com.gkr.portfolio_backend.controller;

import com.gkr.portfolio_backend.dto.ApiResponse;
import com.gkr.portfolio_backend.model.Skill;
import com.gkr.portfolio_backend.service.SkillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/skills")
@CrossOrigin(origins = "*")
public class SkillController {

    @Autowired
    private SkillService skillService;

    @GetMapping
    public ResponseEntity<ApiResponse<List<Skill>>> getAllSkills() {
        List<Skill> skills = skillService.getAllSkills();
        return ResponseEntity.ok(ApiResponse.success("Skills retrieved successfully", skills));
    }

    @GetMapping("/{id}/icon")
    public ResponseEntity<byte[]> getSkillIcon(@PathVariable String id) {
        Skill skill = skillService.getSkillById(id);
        if (skill.getSkillIcon() == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(skill.getIconFileType()))
                .body(skill.getSkillIcon());
    }

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Skill> createSkill(
            @RequestParam("name") String name,
            @RequestParam("category") String category,
            @RequestParam("displayOrder") Integer displayOrder,
            @RequestParam("icon") MultipartFile icon) throws IOException {

        return ResponseEntity.ok(skillService.saveSkill(name, category, displayOrder, icon));
    }
}