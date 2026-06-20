package com.gkr.portfolio_backend.repository;

import com.gkr.portfolio_backend.model.Skill;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface SkillRepository extends MongoRepository<Skill, String> {

}
