package com.gkr.portfolio_backend.service;

import com.gkr.portfolio_backend.model.Skill;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.util.List;

public interface SkillService {
    List<Skill> getAllSkills();
    Skill getSkillById(String id);
    Skill saveSkill(String name, String category, Integer displayOrder, MultipartFile iconFile) throws IOException;
}