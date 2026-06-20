package com.gkr.portfolio_backend.repository;

import com.gkr.portfolio_backend.model.Project;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProjectRepository extends MongoRepository<Project, String> {
}
