package com.gkr.portfolio_backend.repository;

import com.gkr.portfolio_backend.model.Experience;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ExperienceRepository extends MongoRepository<Experience, String> {
}
