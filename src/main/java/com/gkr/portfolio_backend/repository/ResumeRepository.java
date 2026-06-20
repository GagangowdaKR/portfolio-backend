package com.gkr.portfolio_backend.repository;

import com.gkr.portfolio_backend.model.Resume;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ResumeRepository extends MongoRepository<Resume, String> {
}
