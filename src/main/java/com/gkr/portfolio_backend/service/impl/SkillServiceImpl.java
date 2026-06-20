package com.gkr.portfolio_backend.service.impl;

import com.gkr.portfolio_backend.model.Skill;
import com.gkr.portfolio_backend.repository.SkillRepository;
import com.gkr.portfolio_backend.service.SkillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class SkillServiceImpl implements SkillService {

    @Autowired
    private SkillRepository skillRepository;

    @Override
    public List<Skill> getAllSkills() {
        return skillRepository.findAll();
    }

    @Override
    public Skill getSkillById(String id) {
        return skillRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Skill not found with id: " + id));
    }

    @Override
    public Skill saveSkill(String name, String category, Integer displayOrder, MultipartFile iconFile) throws IOException {
        Skill skill = new Skill();
        skill.setSkillName(name);
        skill.setCategory(category);
        skill.setDisplayOrder(displayOrder);

        if (iconFile != null && !iconFile.isEmpty()) {
            skill.setSkillIcon(iconFile.getBytes());
            skill.setIconFileType(iconFile.getContentType());
        }

        return skillRepository.save(skill);
    }
}