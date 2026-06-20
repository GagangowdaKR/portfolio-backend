package com.gkr.portfolio_backend.repository;

import com.gkr.portfolio_backend.model.CodingProfile;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CodingProfileRepository extends MongoRepository<CodingProfile, String> {

}
